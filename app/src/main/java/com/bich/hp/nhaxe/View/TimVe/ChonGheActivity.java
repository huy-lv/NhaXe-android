package com.bich.hp.nhaxe.View.TimVe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.bich.hp.nhaxe.Adapter.AdapterGhe;
import com.bich.hp.nhaxe.ConnectInternet.GheResponse;
import com.bich.hp.nhaxe.ConnectInternet.RetrofitClient;
import com.bich.hp.nhaxe.Model.Ghe;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;
import com.bich.hp.nhaxe.View.NhapThongTinActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ChonGheActivity extends AppCompatActivity {
    @BindView(R.id.rvChonGhe)
    RecyclerView rvChonGhe;
    ArrayList<Ghe> danhsachGhe;
    AdapterGhe adapterGhe;

    @BindView(R.id.btnChonGheTiepTuc)
    Button btnChonGheTiepTuc;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonghe);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chọn ghế");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        danhsachGhe = new ArrayList<>();
        adapterGhe = new AdapterGhe(this,danhsachGhe);
        rvChonGhe.setLayoutManager(new GridLayoutManager(this,4));
        rvChonGhe.setAdapter(adapterGhe);

        //load ghe
        RetrofitClient.getAPIInterface().getGheByMaTuyenXe(Utils.loTrinhDaChon.getMatuyenxe()).enqueue(new Callback<GheResponse>() {
            @Override
            public void onResponse(Call<GheResponse> call, Response<GheResponse> response) {
                danhsachGhe.clear();
                danhsachGhe.addAll(response.body().getGhes());
                adapterGhe.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GheResponse> call, Throwable t) {
                Utils.showInfoDialog(ChonGheActivity.this,t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnChonGheTiepTuc)
    void tieptuc(){
        if(adapterGhe.getSelectedList().size()>0) {
            Utils.gheDaChon.clear();
            for (Ghe g : danhsachGhe) {
                if (g.getTrangthai() == Utils.SELECTED) {
                    Utils.gheDaChon.add(g);
                }
            }
            startActivity(new Intent(this, NhapThongTinActivity.class));
        }else{
            Utils.showInfoDialog(this,"Vui Lòng Chọn Ghế!");
        }
    }
}
