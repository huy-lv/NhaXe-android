package com.bich.hp.nhaxe.ConnectInternet;

import com.bich.hp.nhaxe.Model.Vexe;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huylv on 14-Apr-17.
 */

public class VexeResponse {
    @SerializedName("vexe")
    ArrayList<Vexe> vexes;

    public ArrayList<Vexe> getVexes() {
        return vexes;
    }
}
