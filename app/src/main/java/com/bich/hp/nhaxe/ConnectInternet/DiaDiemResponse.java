package com.bich.hp.nhaxe.ConnectInternet;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huylv on 11-Apr-17.
 */

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
