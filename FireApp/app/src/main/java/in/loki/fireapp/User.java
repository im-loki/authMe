package in.loki.fireapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email,id;

    public User() {
        /* Default constructor required for calls to DataSnapshot.getValue(User.class) */
    }

    public User(String username, String email,String id) {
        this.username = username;
        this.email = email;
        this.id=id;
    }

}
