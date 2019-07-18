package in.loki.fireapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private Button mSendData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        mSendData = (Button) findViewById(R.id.button2);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    private void submitPost(){
        writeNewUser("lokeshwar120","lokeshwar1@gmail.com");
    }

    private void writeNewUser(String name, String email) {

        String id = mDatabase.push().getKey();
        User user = new User(name, email,id);
        mDatabase.child(id).setValue(user);
        Toast.makeText(MainActivity.this,"sent",Toast.LENGTH_LONG).show();
    }
}
