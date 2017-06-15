package com.bich.hp.nhaxe.ConnectInternet;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class DiaDiemResponse {
    @SerializedName("CACDIADIEM")
    ArrayList<Location> cacdiadiem;

    public ArrayList<Location> getCacdiadiem() {
        return cacdiadiem;
    }

    public class Location {
        @SerializedName("DIADIEM")
        String diadiem;

        public String getDiadiem() {
            return diadiem;
        }
    }
}
