package com.example.machine_time.hackaton;


import com.google.gson.annotations.SerializedName;

public class Services {

    @SerializedName("type")
    private String type;
    @SerializedName("info")
    private String info;
    @SerializedName("date")
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @SerializedName("user_id")
    private String user_id;



}
