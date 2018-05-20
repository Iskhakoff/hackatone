package com.example.machine_time.hackaton;

import com.google.gson.annotations.SerializedName;

public class StatusApi {

    @SerializedName("Status")
    int Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("id")
    int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Login")
    private String login;
    @SerializedName("Build")
    private String build;
    @SerializedName("Room")
    private String room;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Email")
    private String email;


    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
