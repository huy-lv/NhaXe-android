package com.bich.hp.nhaxe.ConnectInternet;

import com.bich.hp.nhaxe.Model.Ghe;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huylv on 20-Apr-17.
 */

public class GheResponse {
    @SerializedName("ghe")
    ArrayList<Ghe> ghes;

    public ArrayList<Ghe> getGhes() {
        return ghes;
    }
}
