package com.bich.hp.nhaxe.ConnectInternet;

import com.bich.hp.nhaxe.Model.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class LoginResponse  {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<User> data;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getData() {
        return data;
    }
}
