package com.bich.hp.nhaxe.ConnectInternet;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class PhanCongResponse {
    @SerializedName("PHANCONG")
    ArrayList<PhanCong> phanCongs;

    public ArrayList<PhanCong> getPhanCongs() {
        return phanCongs;
    }

    public class PhanCong {
        @SerializedName("MACONGVIEC")
        String macongviec;
        @SerializedName("BIENSOXE")
        String biensoxe;
        @SerializedName("MATUYENXE")
        String matuyenxe;
        @SerializedName("MANV")
        String manv;
        @SerializedName("TENCONGVIEC")
        String tencongviec;
        @SerializedName("NGAYLAMVIEC")
        String ngaylamviec;

        public String getMacongviec() {
            return macongviec;
        }

        public String getBiensoxe() {
            return biensoxe;
        }

        public String getMatuyenxe() {
            return matuyenxe;
        }

        public String getManv() {
            return manv;
        }

        public String getTencongviec() {
            return tencongviec;
        }

        public String getNgaylamviec() {
            return ngaylamviec;
        }
    }

}
