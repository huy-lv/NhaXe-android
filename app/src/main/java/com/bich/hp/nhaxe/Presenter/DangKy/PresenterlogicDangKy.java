package com.bich.hp.nhaxe.Presenter.DangKy;

import com.bich.hp.nhaxe.Model.DangNhap_DangKy.ModelDangKy;
import com.bich.hp.nhaxe.Model.ObjectClass.KhachHang;
import com.bich.hp.nhaxe.View.DangNhap_DangKy.ViewDangKy;


public class PresenterlogicDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;
    public PresenterlogicDangKy(ViewDangKy viewDangKy){
        this.viewDangKy=viewDangKy;
        modelDangKy=new ModelDangKy();
    }
    @Override
    public void ThucHienDangKy(KhachHang khachHang) {
        boolean kiemtra = modelDangKy.DangKyThanhVien(khachHang);
        if(kiemtra){
            viewDangKy.DangKyThangCong();
        }else{
            viewDangKy.DangKyThatBai();
        }
    }
    }

