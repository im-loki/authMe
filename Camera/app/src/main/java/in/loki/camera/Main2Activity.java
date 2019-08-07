package in.loki.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mPostReference;

    private String Ename, Uid;
    private String TAG = "Main2Activity";

    private TextView tv;
    private ImageView relativeLayout;
    private int exit=0;

    private TextView name,cabin,phone,email;
    private CardView card_info;
    private DatabaseReference databaseReference;

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        relativeLayout = findViewById(R.id.ACK);
        tv = findViewById(R.id.textView);

        // Get post key from intent
        Intent mPostKey = getIntent();
        Ename = mPostKey.getStringExtra("E_POST_KEY");
        Uid = mPostKey.getStringExtra("R_POST_KEY");

        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        name = findViewById(R.id.e_name);
        cabin = findViewById(R.id.e_cabin);
        phone = findViewById(R.id.e_phone);
        email = findViewById(R.id.e_email);
        card_info = findViewById(R.id.info);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Details").child(Ename);

//        Toast.makeText(this, Uid, Toast.LENGTH_SHORT).show();
        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Requests").child(Ename).child("Active").child(Uid);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Add value event listener to the post
        // [START post_value_event_listener]
        if(mPostReference != null){
            mPostReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                            Request_ack ack = dataSnapshot.getValue(Request_ack.class);
                            if(ack.getAck() == 0){
                                Toast.makeText(Main2Activity.this, "Accepted", Toast.LENGTH_SHORT).show();
                                relativeLayout.setBackgroundColor(GREEN);
                                exit = 1;
                                tv.setText("Accepted");
                                fetchEmp();
                            }
                            else if(ack.getAck() == 2){
                                Toast.makeText(Main2Activity.this, "Rejected", Toast.LENGTH_SHORT).show();
                                relativeLayout.setBackgroundColor(RED);
                                exit = 1;
                                tv.setText("Rejected");
                            }
                        }
//                    Toast.makeText(Main2Activity.this, "Request acked", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void fetchEmp() {
        if(databaseReference!=null){
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        Emp_details em = dataSnapshot.getValue(Emp_details.class);
                        setter(em);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    public void setter(Emp_details em){

        name.setText(em.getEname());
        phone.setText(em.getPhone());
        email.setText(em.getEmail());
        cabin.setText(em.getCabin());
        card_info.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        if(exit==1) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
        else
            Toast.makeText(this, "Wait For Response", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

    }
}
