package com.bich.hp.nhaxe.Model.ObjectClass;


public class KhachHang {
    int MaPhuongThuc;

    String TenDN;
    String MatKhauKH;

    String Email;
    String SoDT;

    String old_password;
    String new_password;
    String code;

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


    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public void setCode(String code) {
        this.code = code;
    }
}