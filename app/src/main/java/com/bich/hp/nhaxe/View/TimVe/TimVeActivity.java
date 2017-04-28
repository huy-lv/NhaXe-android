package com.bich.hp.nhaxe.View.TimVe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.bich.hp.nhaxe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bich.hp.nhaxe.Utils.loTrinhDaChon;
import static com.bich.hp.nhaxe.Utils.ngayDaChon;

/**
 * Created by huylv on 11-Apr-17.
 */

public class TimVeActivity extends AppCompatActivity {
    @BindView(R.id.tvDiemDi1)    TextView tvDiemDi1;
    @BindView(R.id.tvDiemDen1) TextView tvDiemDen1;
    @BindView(R.id.tvTENLOTRINH) TextView tvTENLOTRINH;
    @BindView(R.id.tvNgay) TextView tvNgay;
    @BindView(R.id.tvGia1) TextView tvGia1;
    @BindView(R.id.btnTienHanhDatVe)    Button btnTienHanhDatVe;
    @BindView(R.id.tvThoiGian) TextView tvThoiGian;

    private String ngaydi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdanhsachlotrinh);
        ButterKnife.bind(this);

        tvDiemDi1.setText(loTrinhDaChon.getDiemdi());
        tvDiemDen1.setText(loTrinhDaChon.getDiemden());
        tvGia1.setText(loTrinhDaChon.getGia());
        tvNgay.setText(ngayDaChon);
        tvTENLOTRINH.setText(loTrinhDaChon.getTenlotrinh());
        tvThoiGian.setText(loTrinhDaChon.getThoigianbatdau());

    }

    @OnClick(R.id.btnTienHanhDatVe)
    void chonghengoi(){
        startActivity(new Intent(this,ChonGheActivity.class));
    }
}
