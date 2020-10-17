package in.loki.camera;

import android.util.Log;

import java.util.Date;

public class Request_ack {

    public String name;
    public String purpose;
    public int ack=1;
    public String id;
    public String downloadUri;
    public String phone;
    public Date date;

    public Request_ack() {
        /* Default constructor required for calls to DataSnapshot.getValue(User.class) */
    }

    public Request_ack(String name, String purpose,String id,String phone,String downloadUri,Date date) {
        this.name = name;
        this.purpose = purpose;
        this.id=id;
        this.phone=phone;
        this.downloadUri = downloadUri;
        this.date=date;
        Log.i("helloworld", "Request_ack: "+ date);
    }

    public int getAck(){
        return ack;
    }

}
