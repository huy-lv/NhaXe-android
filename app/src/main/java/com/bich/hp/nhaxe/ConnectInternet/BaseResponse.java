package com.bich.hp.nhaxe.ConnectInternet;

import com.google.gson.annotations.SerializedName;



public class BaseResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
