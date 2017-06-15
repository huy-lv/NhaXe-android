package com.bich.hp.nhaxe.Model;

import com.google.gson.annotations.SerializedName;



public class Ghe {
    @SerializedName("BIENSOXE")
    String biensoxe;
    @SerializedName("TRANGTHAI")
    int trangthai;
    @SerializedName("MAGHE")
    String maghe;
    @SerializedName("TENGHE")
    String tenghe;
    @SerializedName("TENKH")
    String tenKhachHang;

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getBiensoxe() {
        return biensoxe;
    }

    public void setBiensoxe(String biensoxe) {
        this.biensoxe = biensoxe;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getMaghe() {
        return maghe;
    }

    public void setMaghe(String maghe) {
        this.maghe = maghe;
    }

    public String getTenghe() {
        return tenghe;
    }

    public void setTenghe(String tenghe) {
        this.tenghe = tenghe;
    }
}
