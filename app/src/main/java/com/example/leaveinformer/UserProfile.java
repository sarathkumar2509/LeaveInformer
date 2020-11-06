package com.example.leaveinformer;

public class UserProfile {
    public  String secName;
    public  String secDept;
    public  String secRoll;
    public  String secFromto;
    public  String Days;
    public  String Reason;

    public UserProfile(String secName, String secDept, String secRoll, String secFromto, String days, String reason) {
        this.secName = secName;
        this.secDept = secDept;
        this.secRoll = secRoll;
        this.secFromto = secFromto;
        Days = days;
        Reason = reason;
    }
}
