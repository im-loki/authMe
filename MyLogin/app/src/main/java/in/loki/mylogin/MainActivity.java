package in.loki.mylogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private logmein logme;

    private EditText name;
    private EditText pwd;
    private Button log_but;

    private String Uname,passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logme = new logmein(getApplicationContext());

        name = (EditText) findViewById(R.id.login);
        pwd = (EditText) findViewById(R.id.pwd);



        if(logme.readLoginStatus()){
            startActivity(new Intent(this, Main2Activity.class));
            finish();
        }

    }


    public void logger(View view) {
        Uname = name.getText().toString();
        passwd = pwd.getText().toString();

        startActivity(new Intent(this, Main2Activity.class));
        logme.writeLoginStatus(Boolean.TRUE, Uname);
        finish();
        Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_LONG).show();
    }
}
