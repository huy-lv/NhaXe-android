package com.bich.hp.nhaxe.View.TrangChu;


import com.bich.hp.nhaxe.Model.DangNhap_DangKy.ModelDangNhap;
import com.facebook.AccessToken;



public class PreSenterXuLyLogic implements IPreSenterXuLy {
    public PreSenterXuLyLogic(MainActivity mainActivity) {

    }

    @Override
    public AccessToken LayTokenDungFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();

        return accessToken;


    }
}
