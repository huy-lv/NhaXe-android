package com.bich.hp.nhaxe.Model;

import com.google.gson.annotations.SerializedName;



public class Vexe {
    @SerializedName("MAVE")
    int mave;
    @SerializedName("MALOTRINH")
    int malotrinh;
    @SerializedName("DIEMDI")
    String diemdi;
    @SerializedName("DIEMDEN")
    String diemden;
    @SerializedName("SDT")
    String sdt;
    @SerializedName("TONGTIEN")
    String tongtien;
    @SerializedName("TENKHACHHANG")
    String tenkhachhang;
    @SerializedName("GIOKHOIHANH")
    String giokhoihanh;
    @SerializedName("NGAYKHOIHANH")
    String ngaykhoihanh;
    @SerializedName("MAGHE")
    String maghe;
    @SerializedName("TENGHE")
    String tenghe;

    public int getMave() {
        return mave;
    }

    public int getMalotrinh() {
        return malotrinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getTongtien() {
        return tongtien;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public String getGiokhoihanh() {
        return giokhoihanh;
    }

    public String getNgaykhoihanh() {
        return ngaykhoihanh;
    }

    public String getMaghe() {
        return maghe;
    }

    public String getTenghe() {
        return tenghe;
    }

    public String getDiemdi() {
        return diemdi;
    }

    public String getDiemden() {
        return diemden;
    }
}