package in.loki.mylogin;

import android.content.Context;
import android.content.SharedPreferences;

public class logmein {
    private SharedPreferences sharedPreferences;
    private Context context;

    public logmein(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(
                context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(Boolean status,String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_perference),status);
        editor.putString("Uname",name);
        editor.commit();
    }


    public Boolean readLoginStatus(){
        boolean status=false;
        status = sharedPreferences.getBoolean(
                context.getResources().getString(R.string.login_status_perference),status);
        return status;
    }

    public String readLoginUname() {
        return sharedPreferences.getString("Uname","Not logged in");
    }


}
