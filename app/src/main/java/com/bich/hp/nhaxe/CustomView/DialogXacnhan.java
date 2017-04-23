package com.bich.hp.nhaxe.CustomView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bich.hp.nhaxe.Adapter.AdapterGheDaChon;
import com.bich.hp.nhaxe.ConnectInternet.BaseResponse;
import com.bich.hp.nhaxe.ConnectInternet.RetrofitClient;
import com.bich.hp.nhaxe.Model.Ghe;
import com.bich.hp.nhaxe.Model.Lo_Trinh;
import com.bich.hp.nhaxe.Model.User;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;
import com.bich.hp.nhaxe.View.TrangChu.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bich.hp.nhaxe.Utils.gheDaChon;
import static com.bich.hp.nhaxe.Utils.loTrinhDaChon;
import static com.bich.hp.nhaxe.Utils.ngayDaChon;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by huylv on 13-Apr-17.
 */

public class DialogXacnhan extends Dialog {
    Context c;
    @BindView(R.id.tv_Ten)
    TextView tv_Ten;
    @BindView(R.id.tv_Sdt)
    TextView tv_Sdt;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_lotrinh)
    TextView tv_lotrinh;
    @BindView(R.id.tv_thoigian)
    TextView tv_thoigian;
    @BindView(R.id.rv_danhsachve)
    RecyclerView rv_danhsachve;
    @BindView(R.id.bt_tt_taiquay)
    Button bt_tt_taiquay;
    @BindView(R.id.bt_tt_paypal)
    Button bt_tt_paypal;
    private AdapterGheDaChon adapter;
    private Lo_Trinh lotrinh;
    private User user;
    private ProgressDialog pd;
    int i1,i2;

    public DialogXacnhan(@NonNull Context context) {
        super(context);
        c = context;
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_xacnhan);
        ButterKnife.bind(this);
        lotrinh = Utils.loTrinhDaChon;
        if(Utils.LOGGEDIN) {
            user = Utils.getCurrentUser();

            tv_email.setText(user.getEmail());
            tv_Sdt.setText(user.getPhone());
            tv_Ten.setText(user.getName());
        }
        pd = new ProgressDialog(c);
        pd.setIndeterminate(true);
        pd.setMessage("Loading...");


        if (lotrinh != null && gheDaChon.size() > 0) {
            tv_lotrinh.setText(lotrinh.getDiemdi() + " -> " + lotrinh.getDiemden());
            tv_thoigian.setText(lotrinh.getThoigianbatdau() + " " + Utils.ngayDaChon);

            adapter = new AdapterGheDaChon(c, gheDaChon);
            rv_danhsachve.setLayoutManager(new LinearLayoutManager(c));
            rv_danhsachve.setAdapter(adapter);
        }
    }

    @OnClick(R.id.bt_tt_taiquay)
    void tt_tq() {
        pd.show();
        i1=0;i2=0;
        for (Ghe g : gheDaChon) {
            RetrofitClient.getAPIInterface().addVexe(lotrinh.getMalotrinh(),
                    user.getPhone(),
                    lotrinh.getGia(),
                    g.getTenKhachHang(),
                    lotrinh.getThoigianbatdau(),
                    Utils.ngayDaChon,
                    g.getMaghe(),
                    g.getTenghe()).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if(response.body().getError()==0){
                        checkDone();
                        i1++;
                    }else{
                        pd.dismiss();
                        dismiss();
                        Log.e("cxz","error1:"+response.body().getMessage());
                        Utils.showInfoDialog(c,response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    Log.e("cxz","error1_1:"+t.getMessage());
                    t.printStackTrace();
                    Utils.showInfoDialog(c,t.getMessage());
                }
            });
            RetrofitClient.getAPIInterface().setGheDaChon(g.getMaghe(),g.getTenKhachHang()).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if(response.body().getError()==0){
                        checkDone();
                        i2++;
                    }else{
                        pd.dismiss();
                        dismiss();
                        Log.e("cxz","error2:"+response.body().getMessage());
                        Utils.showInfoDialog(c,response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    Log.e("cxz","error1_2:"+t.getMessage());
                    t.printStackTrace();
                    Utils.showInfoDialog(c,t.getMessage());
                }
            });
        }

    }

    void checkDone(){
        if(i1 == Utils.gheDaChon.size()-1 && i2 == i1){
            Log.e("Cxz","done");
                    sendSMS(user.getPhone(), "Hoa don dat ve xe: Khach hang: " + user.getName() +
                            ",email: " + user.getEmail() +
                            ", lo trinh: " + lotrinh.getDiemdi() +
                            " -> " + lotrinh.getDiemden() +
                            " thanh toan hoa don tai 11/16 Pham Van Dong");
            Toast.makeText(c,"Dat ve thanh cong!",Toast.LENGTH_SHORT).show();
            dismiss();
            pd.dismiss();
            c.startActivity(new Intent(c, MainActivity.class));
            gheDaChon.clear();
            loTrinhDaChon= null;
            ngayDaChon=null;
        }
    }

    @OnClick(R.id.bt_tt_paypal)
    void tt_pp() {
        Toast.makeText(c, "Coming soon!", Toast.LENGTH_SHORT).show();
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,
                null,
                message,
                null,
                null);
        Toast.makeText(getApplicationContext(), "Dat ve thanh cong!", Toast.LENGTH_LONG).show();

    }
}