package in.loki.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Selection extends AppCompatActivity {

    private logmein logme;

    private TextView User;
    private Button log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        logme = new logmein(getApplicationContext());
        User = (TextView) findViewById(R.id.user);

        User.setText(logme.readLoginUname());

    }

    public void logout(View view) {
        logme.writeLoginStatus(Boolean.FALSE,"No User");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
