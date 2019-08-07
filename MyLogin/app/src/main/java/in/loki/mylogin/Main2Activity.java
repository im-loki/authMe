package in.loki.mylogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;

    private SharedPreferences sharedPreferences;
    private DatabaseReference reference;

    private final String CHANNEL_ID = "personal";
    private final int NOTIFICATION_ID = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sharedPreferences = this.getSharedPreferences(
                this.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
        String e_name = sharedPreferences.getString("Uname","Not logged in");

        Toast.makeText(this, "Logged in as: "+e_name, Toast.LENGTH_SHORT).show();

//        reference = FirebaseDatabase.getInstance().getReference().child("Requests").
//                child(e_name).child("Active");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                int count = 0;
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    Users req = ds.getValue(Users.class);
//                    if(req.getAck()==1) {
//                        count += 1;
//                    }
//                }
//                if(count>0)
//                    sendNotification();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        Log.d(TAG, "OnCreate");
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.view_pager);
        setUpViewPage(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setUpViewPage(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Frag1(), "Active");
        adapter.addFragment(new Frag2(), "History");
        viewPager.setAdapter(adapter);
    }

//    public void sendNotification() {
//        createNotificationChannel();
//        Intent landingIntent = new Intent(this, Main2Activity.class);
//        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent landingPendingIndent = PendingIntent.getActivity(this, 0,
//                landingIntent,PendingIntent.FLAG_ONE_SHOT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
//        builder.setContentTitle("ITC-Auth");
//        builder.setContentText("Pending Active Requests");
//        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        builder.setAutoCancel(true);
//        builder.setContentIntent(landingPendingIndent);
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
//
//
//    }
//
//    private void createNotificationChannel(){
//
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            CharSequence name = "personal";
//            String des = "Include all";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//
//            NotificationChannel notificationChannel =
//                    new NotificationChannel(CHANNEL_ID, name, importance);
//
//            notificationChannel.setDescription(des);
//
//            NotificationManager notificationManager =
//                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//    }
}