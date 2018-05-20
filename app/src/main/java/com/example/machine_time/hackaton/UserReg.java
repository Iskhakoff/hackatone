package com.example.machine_time.hackaton;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserReg implements Serializable{


    @SerializedName("name")
    private String name;
    @SerializedName("login")
    private String login;
    @SerializedName("pass")
    private String pass;
    @SerializedName("build")
    private String build;
    @SerializedName("room")
    private String room;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;



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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
