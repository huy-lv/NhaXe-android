package com.bich.hp.nhaxe.ConnectInternet;

import com.bich.hp.nhaxe.Model.Lo_Trinh;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class LoTrinhResponse {
    @SerializedName("LOTRINH")
    ArrayList<Lo_Trinh> lotrinh;

    public ArrayList<Lo_Trinh> getLotrinh() {
        return lotrinh;
    }


}
