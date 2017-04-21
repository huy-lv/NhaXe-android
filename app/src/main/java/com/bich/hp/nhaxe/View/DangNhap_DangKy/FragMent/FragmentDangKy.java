package com.bich.hp.nhaxe.View.DangNhap_DangKy.FragMent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.bich.hp.nhaxe.Model.ObjectClass.KhachHang;
import com.bich.hp.nhaxe.Presenter.DangKy.PresenterlogicDangKy;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.View.DangNhap_DangKy.ViewDangKy;


public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener {
    PresenterlogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edsdt, edMatKhau, edNhapLaiMatKhau, edDiaChiEmail, edTenkhdv;
    SwitchCompat sEmailDocQuyen;

    TextInputLayout input_edsdt;

    TextInputLayout input_edMatKhau;
    TextInputLayout input_edNhapLaiMatKhau;
    TextInputLayout input_edTendvkh;
    TextInputLayout input_edDiaChiEmail;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);

        btnDangKy = (Button) view.findViewById(R.id.btnTiepTucNhapThongTin);

        edsdt = (EditText) view.findViewById(R.id.edsdt);

        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = (EditText) view.findViewById(R.id.edNhapLaiMatKhauDK);

        edDiaChiEmail = (EditText) view.findViewById(R.id.edDiaChiEmailDK);

        edTenkhdv = (EditText) view.findViewById(R.id.edTenKHDV);

        sEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.sEmailDocQuyen);


        input_edsdt = (TextInputLayout) view.findViewById(R.id.input_edsdt);
        input_edMatKhau = (TextInputLayout) view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = (TextInputLayout) view.findViewById(R.id.input_edDiaChiEmailDK);
        input_edTendvkh = (TextInputLayout) view.findViewById(R.id.input_edTenKHDV);

        presenterLogicDangKy = new PresenterlogicDangKy(this);

        btnDangKy.setOnClickListener(this);
        edsdt.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        edTenkhdv.setOnFocusChangeListener(this);


        return view;
    }

    @Override
    public void DangKyThangCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.btnTiepTucNhapThongTin:
                btnDangKy();
                break;
        }
    }

    String emaildocquyen = "";

    private void btnDangKy() {
        String sdt = edsdt.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMatKhau.getText().toString();

        String tenkhdv = edTenkhdv.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if (kiemtrathongtin) {
            KhachHang khachHang = new KhachHang();
            khachHang.setSoDT(sdt);
            khachHang.setMaThanhToan(1);
            khachHang.setEmail(email);
            khachHang.setMatKhauKH(matkhau);


            khachHang.setTenKHDN(tenkhdv);
            khachHang.setEmailDocQuyen(emaildocquyen);
            presenterLogicDangKy.ThucHienDangKy(khachHang);
        } else {
            Log.d("kiemtra", "Dang ky that bai1 ");
        }


    }



    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edsdt:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edsdt.setErrorEnabled(true);
                        input_edsdt.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraphone = Patterns.PHONE.matcher(chuoi).matches();
                        if(!kiemtraphone){
                            input_edsdt.setErrorEnabled(true);
                            input_edsdt.setError("Đây không phải là số điện thoại !");
                            kiemtrathongtin = false;
                        }else{
                            input_edsdt.setErrorEnabled(false);
                            input_edsdt.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                ;break;

            case R.id.edDiaChiEmailDK:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        }else{
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                ;break;


            case R.id.edMatKhauDK:
                ;break;

            case R.id.edNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    }else{
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                ;break;


            case R.id.edTenKHDV:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edTendvkh.setErrorEnabled(true);
                        input_edTendvkh.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;

                        }else{
                            input_edTendvkh.setErrorEnabled(false);
                            input_edTendvkh.setError("");
                            kiemtrathongtin = true;
                        }

                }
                ;break;


        }
    }


}
