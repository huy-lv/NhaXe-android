package com.bich.hp.nhaxe.Model.ObjectClass;

/**
 * Created by hp on 2/26/2017.
 */

public class KhachHang {
    int MaPhuongThuc;

    String TenDN;
    String MatKhauKH;

    String Email;
    String SoDT;

    public int getMaPhuongThuc() {
        return MaPhuongThuc;
    }

    public void setMaPhuongThuc(int maPhuongThuc) {
        MaPhuongThuc = maPhuongThuc;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String tenDN) {
        TenDN = tenDN;
    }

    public String getMatKhauKH() {
        return MatKhauKH;
    }

    public void setMatKhauKH(String matKhauKH) {
        MatKhauKH = matKhauKH;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }
}