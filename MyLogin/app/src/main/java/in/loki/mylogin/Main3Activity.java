package in.loki.mylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String e_name;

    private String id;

    private TextView d_ename,d_id,d_phone,d_purpose;
    private Button d_accept, d_reject;
    private ImageView d_image;

    private DatabaseReference reference_read,reference_write;

    private Users u;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sharedPreferences = getSharedPreferences(
                getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
        e_name = sharedPreferences.getString("Uname","Not logged in");

        Intent mPostKey = getIntent();
        id = mPostKey.getStringExtra("E_POST_KEY");

        d_ename=findViewById(R.id.disp_name);
        d_id=findViewById(R.id.disp_id);
        d_phone = findViewById(R.id.disp_phone);
        d_purpose = findViewById(R.id.disp_purpose);
        d_image = findViewById(R.id.imageView);

        d_id.setText(id);

        reference_read = FirebaseDatabase.getInstance().getReference().child("Requests").
                child(e_name).child("Active").child(id);
        reference_write = FirebaseDatabase.getInstance().getReference().child("Requests").
                child(e_name).child("Active").child(id);
        d_ename.setText(e_name);


    }

    @Override
    protected void onStart() {
        super.onStart();

        reference_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    u = dataSnapshot.getValue(Users.class);
                    if (u.getAck() == 1) {
                        Glide.with(getBaseContext())
                                .load(u.downloadUri)
                                .centerCrop()
                                .placeholder(R.drawable.face)
                                .crossFade()
                                .into(d_image);
                        d_ename.setText(u.getName());
                        d_phone.setText(u.getPhone());
                        d_purpose.setText(u.getPurpose());
                        d_id.setText(u.getId());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void accept(View view) {
        u.setAck(0);
        updateAck();
        Toast.makeText(this, "Accepted", Toast.LENGTH_SHORT).show();
    }

    public void reject(View view) {
        u.setAck(2);
        updateAck();
        Toast.makeText(this, "Rejected", Toast.LENGTH_SHORT).show();
    }

    public void updateAck(){
        reference_write.setValue(u);
    }
}
