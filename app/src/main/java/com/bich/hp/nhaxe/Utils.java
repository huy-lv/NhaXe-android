package com.bich.hp.nhaxe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.bich.hp.nhaxe.Model.Ghe;
import com.bich.hp.nhaxe.Model.Lo_Trinh;
import com.bich.hp.nhaxe.Model.User;

import java.util.ArrayList;

/**
 * Created by huylv on 11-Apr-17.
 */

public class Utils {
    public static final String SP_USER = "SP_USER";
    public static final int EMPTY = 0;
    public static final int OWNED = 2;
    public static final int SELECTED = 1;
    public static String INTENT_KEY_TUYENXE = "INTENT_KEY_TUYENXE";
    public static String INTENT_KEY_NGAYDI = "INTENT_KEY_NGAYDI";
    public static String ngayDaChon;
    public static boolean LOGGEDIN;
    public static ArrayList<Ghe> gheDaChon = new ArrayList<>();
    public static Lo_Trinh loTrinhDaChon;
    private static User user;

    public static User getCurrentUser() {
        if(user==null) user = new User();
        return user;
    }

    public static void setCurrentUser(User currentUser) {
        Utils.getCurrentUser().setEmail(currentUser.getEmail());
        Utils.getCurrentUser().setName(currentUser.getName());
        Utils.getCurrentUser().setPhone(currentUser.getPhone());
    }

    public static void showInfoDialog(Context c, String message){
        (new AlertDialog.Builder(c)).setMessage(message).setPositiveButton("OK",null).create().show();
    }

    public static void showConfirmDialog(Context c, String message, DialogInterface.OnClickListener onClickYes){
        (new AlertDialog.Builder(c)).setMessage(message).setPositiveButton("YES",onClickYes).setNegativeButton("NO",null).create().show();
    }

    public static void showProgessDialog(Context c){
        ProgressDialog pd = new ProgressDialog(c);
        pd.setIndeterminate(true);
        pd.setMessage("Loading...");
        pd.show();
    }

    public static void getClearCurrentUser() {
        user = null;
    }
}
