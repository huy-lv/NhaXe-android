package com.bich.hp.nhaxe.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bich.hp.nhaxe.Adapter.VexeAdapter;
import com.bich.hp.nhaxe.ConnectInternet.BaseResponse;
import com.bich.hp.nhaxe.ConnectInternet.RetrofitClient;
import com.bich.hp.nhaxe.ConnectInternet.VexeResponse;
import com.bich.hp.nhaxe.Model.Vexe;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VeDaDatActivity extends AppCompatActivity {

    @BindView(R.id.vedadat_sove)
    TextView vedadat_sove;
    @BindView(R.id.vedadat_rv)
    RecyclerView vedadat_rv;
    @BindView(R.id.vedadat_pb)
    ProgressBar vedadat_pb;

    ArrayList<Vexe> vexedadat;
    VexeAdapter vexeAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedadat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        vexedadat = new ArrayList<>();
        vexeAdapter = new VexeAdapter(VeDaDatActivity.this, vexedadat);
        vedadat_rv.setLayoutManager(new LinearLayoutManager(VeDaDatActivity.this));
        vedadat_rv.setAdapter(vexeAdapter);

        pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setMessage("Loading...");

        loadVexe();
    }

    public void loadVexe() {
        RetrofitClient.getAPIInterface().getVeXeByPhone(Utils.getCurrentUser().getPhone()).enqueue(new Callback<VexeResponse>() {
            @Override
            public void onResponse(Call<VexeResponse> call, Response<VexeResponse> response) {
                vexedadat.clear();
                vexedadat.addAll(response.body().getVexes());

                vedadat_sove.setText(String.valueOf(vexedadat.size()));
                vedadat_pb.setVisibility(View.INVISIBLE);
                vexeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<VexeResponse> call, Throwable t) {
                Utils.showInfoDialog(VeDaDatActivity.this, t.getMessage());
            }
        });

    }


    public void huyve(final Vexe v) {
        Utils.showConfirmDialog(this, "Bạn có chắc muốn hủy vé này?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                thuchienhuyve(v);
                pd.show();
            }
        });
    }

    void thuchienhuyve(Vexe v) {
        RetrofitClient.getAPIInterface().deleteVexeById(v.getMave()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getError() == 0) {
                    pd.dismiss();
                    Utils.showInfoDialog(VeDaDatActivity.this, "Hủy vé thành công!");
                    loadVexe();

                } else {
                    Utils.showInfoDialog(VeDaDatActivity.this, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Utils.showInfoDialog(VeDaDatActivity.this, t.getMessage());
            }
        });
    }

}
