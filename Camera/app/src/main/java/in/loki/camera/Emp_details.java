package in.loki.camera;

public class Emp_details {
    private String ename;
    private String phone;
    private String cabin;
    private String email;

    public Emp_details(){

    }

    public Emp_details(String ename, String phone, String cabin, String email) {
        this.ename = ename;
        this.phone = phone;
        this.cabin = cabin;
        this.email = email;
    }

    public String getEname() {
        return ename;
    }

    public String getPhone() {
        return phone;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmail() {
        return email;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
