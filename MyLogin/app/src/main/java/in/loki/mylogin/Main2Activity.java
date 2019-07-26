package in.loki.mylogin;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //
        //
        FirebaseMessaging.getInstance().subscribeToTopic("Ajith_Kumar");
        //
        //

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
}