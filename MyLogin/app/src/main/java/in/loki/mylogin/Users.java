package in.loki.mylogin;

import java.util.Date;

public class Users {
    public String name;
    public String purpose;
    public int ack=1;
    public String id;
    public String downloadUri;
    public String phone;
    public Date date;

    public Users(){

    }

    public Users(String name, String purpose, int ack, String id, String downloadUri, String phone, Date date) {
        this.name = name;
        this.purpose = purpose;
        this.ack = ack;
        this.id = id;
        this.downloadUri = downloadUri;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getAck() {
        return ack;
    }

    public void setAck(int ack) {
        this.ack = ack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
