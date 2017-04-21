package com.bich.hp.nhaxe.Model.ObjectClass;

/**
 * Created by hp on 2/26/2017.
 */

public class KhachHang {
    int MaThanhToan;
    String     Cmnd;
    String TenKHDN;
    String MatKhauKH;

    String Email;
    String SoDT;
    String EmailDocQuyen;

    public int getMaThanhToan() {
        return MaThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        MaThanhToan = maThanhToan;
    }

    public String getCmnd() {
        return Cmnd;
    }

    public void setCmnd(String cmnd) {
        Cmnd = cmnd;
    }

    public String getTenKHDN() {
        return TenKHDN;
    }

    public void setTenKHDN(String tenKHDN) {
        TenKHDN = tenKHDN;
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

    public String getEmailDocQuyen() {
        return EmailDocQuyen;
    }

    public void setEmailDocQuyen(String emailDocQuyen) {
        EmailDocQuyen = emailDocQuyen;
    }
}