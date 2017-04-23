package com.bich.hp.nhaxe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 14-Apr-17.
 */

public class User{
    @SerializedName("EMAIL")
    String email;
    @SerializedName("SDT")
    String phone;
    @SerializedName("MAPHUONGTHUC")
    int maphuongthuc;
    @SerializedName("TENDN")
    String name;

    public User() {
    }

    public int getMaphuongthuc() {
        return maphuongthuc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", maphuongthuc=" + maphuongthuc +
                ", name='" + name + '\'' +
                '}';
    }

    public void clear() {

    }
}

