package in.loki.mylogin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private logmein logme;

    private EditText name;
    private EditText pwd;
    private Button log_but;

    private String Uname,passwd;

    private final String CHANNEL_ID = "personal";
    private final int NOTIFICATION_ID = 001;
    private static final String TAG = "MainActivity";
    private SharedPreferences sharedPreferences;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logme = new logmein(getApplicationContext());

        name = (EditText) findViewById(R.id.login);
        pwd = (EditText) findViewById(R.id.pwd);



        if(logme.readLoginStatus()){
            reader();
            startActivity(new Intent(this, Main2Activity.class));
            finish();
        }

    }

    public void reader(){
        sharedPreferences = this.getSharedPreferences(
                this.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
        String e_name = sharedPreferences.getString("Uname","Not logged in");
        reference = FirebaseDatabase.getInstance().getReference().child("Requests").
                child(e_name).child("Active");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Users req = ds.getValue(Users.class);
                    if(req.getAck()==1) {
                        count += 1;
                    }
                }
                if(count>0)
                    sendNotification();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendNotification() {
        createNotificationChannel();
        Intent landingIntent = new Intent(this, Main2Activity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPendingIndent = PendingIntent.getActivity(this, 0,
                landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("ITC-Auth");
        builder.setContentText("Pending Active Requests");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(landingPendingIndent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());


    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "personal";
            String des = "Include all";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel =
                    new NotificationChannel(CHANNEL_ID, name, importance);

            notificationChannel.setDescription(des);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void logger(View view) {
        Uname = name.getText().toString();
        passwd = pwd.getText().toString();
//here
        if(Uname.isEmpty() || passwd.isEmpty()){
            Toast.makeText(MainActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(this, Main2Activity.class));
            logme.writeLoginStatus(Boolean.TRUE, Uname);
            reader();
            finish();
            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
        }
        //here
    }
}
