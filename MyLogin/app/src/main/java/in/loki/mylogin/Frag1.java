package in.loki.mylogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Frag1 extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter1 myAdapter;
    private List<Users> mUser;

    private DatabaseReference reference;

    private SharedPreferences sharedPreferences;
    private String e_name;

    public Frag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag1,container,false);

//        Toast.makeText(getContext(),"Logged in as: "+e_name, Toast.LENGTH_SHORT).show();

        recyclerView = view.findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myAdapter = new MyAdapter1(mUser,getContext());
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = new ArrayList<Users>();

        sharedPreferences = getContext().getSharedPreferences(
                getContext().getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
        e_name = sharedPreferences.getString("Uname","Not logged in");

        reference = FirebaseDatabase.getInstance().getReference().child("Requests").
                child(e_name).child("Active");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUser.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Users req = ds.getValue(Users.class);
                    if(req.getAck()==1)
                        mUser.add(req);
                }
                myAdapter = new MyAdapter1(mUser, getContext());
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}