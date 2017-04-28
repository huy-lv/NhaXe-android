package com.bich.hp.nhaxe.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bich.hp.nhaxe.CustomView.DialogXacnhan;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huylv on 12-Apr-17.
 */

public class NhapThongTinActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edTenKHDV)
    EditText edTenKHDV;
    @BindView(R.id.edsdt)
    EditText edsdt;
    @BindView(R.id.edDiaChiEmailDK)
    EditText edDiaChiEmailDK;
    @BindView(R.id.btnTiepTucNhapThongTin)
    Button btnDangKy;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.input_edTenKHDV)
    TextInputLayout inputEdTenKHDV;
    @BindView(R.id.input_edsdt)
    TextInputLayout inputEdsdt;
    @BindView(R.id.input_edDiaChiEmailDK)
    TextInputLayout inputEdDiaChiEmailDK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhapthongtin);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(Utils.getCurrentUser().getName()!=null) edTenKHDV.setText(Utils.getCurrentUser().getName());
        if(Utils.getCurrentUser().getEmail()!=null) edDiaChiEmailDK.setText(Utils.getCurrentUser().getEmail());
        if(Utils.getCurrentUser().getPhone()!=null) edsdt.setText(Utils.getCurrentUser().getPhone());
    }

    @OnClick(R.id.btnTiepTucNhapThongTin)
    void tieptuc() {
        hideKeyboard();

        String email = edDiaChiEmailDK.getText().toString();
        String name = edTenKHDV.getText().toString();
        String sdt = edsdt.getText().toString();
        if (!validateEmail(email)) {
            inputEdDiaChiEmailDK.setError("Email khong hop le!");
        } else if (name.length()<7) {
            inputEdTenKHDV.setError("Ten khong hop le!");
        } else if(sdt.length()<10 ){
            inputEdsdt.setError("So dien thoai khong hop le!");
        }else if(!checkBox.isChecked()){
            Toast.makeText(this,"Ban phai chap nhan chinh sach bao mat!",Toast.LENGTH_SHORT).show();
        }else{
            //done
            new DialogXacnhan(this).show();
        }
    }

    public boolean validateEmail(String email) {
        return email.contains("@") && email.length()>6;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void ketthuc(){
        finish();

    }
}
