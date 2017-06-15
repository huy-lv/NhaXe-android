package com.bich.hp.nhaxe.Model;

import com.google.gson.annotations.SerializedName;



public class Lo_Trinh{
    @SerializedName("DIEMDI")
    String diemdi;
    @SerializedName("DIEMDEN")
    String diemden;
    @SerializedName("GIA")
    String gia;
    @SerializedName("TENLOTRINH")
    String tenlotrinh;
    @SerializedName("MATUYENXE")
    String matuyenxe;
    @SerializedName("MALOTRINH")
    int malotrinh;
    @SerializedName("THOIGIANBATDAU")
    String thoigianbatdau;

    public int getMalotrinh() {
        return malotrinh;
    }


    public String getThoigianbatdau() {
        return thoigianbatdau;
    }



    public String getMatuyenxe() {
        return matuyenxe;
    }

    public String getDiemdi() {
        return diemdi;
    }

    public String getDiemden() {
        return diemden;
    }

    public String getGia() {
        return gia;
    }

    public String getTenlotrinh() {
        return tenlotrinh;
    }
}