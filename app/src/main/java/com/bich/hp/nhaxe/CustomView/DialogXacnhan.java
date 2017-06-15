package com.bich.hp.nhaxe.CustomView;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.bich.hp.nhaxe.Mail;
import com.bich.hp.nhaxe.Model.Ghe;
import com.bich.hp.nhaxe.Model.Lo_Trinh;
import com.bich.hp.nhaxe.Model.User;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;
import com.bich.hp.nhaxe.View.TrangChu.MainActivity;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

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
    int i1, i2;
    private AdapterGheDaChon adapter;
    private Lo_Trinh lotrinh;
    private User user;
    private ProgressDialog pd;

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
            String message = "Hoá Đơn Đặt Vé Xe: " +
                    "\nKhách Hàng: " + user.getName() +
                            ",\nEmail: " + user.getEmail() +
                            ",\nLộ Trình: " + lotrinh.getDiemdi() +
                            " -> " + lotrinh.getDiemden() +
                            "\nGiá Vé :"+
                            lotrinh.getGia()+
                            "\nVui lòng đến nhà xe trước giờ khởi hành 15p để lấy vé và thanh toán." +
                    "\n Cảm Ơn Quý Khách Đã Sử Dụng Dịch Vụ Của Chúng Tôi";
            Log.e("Cxz","done");
            sendSMS(user.getPhone(), message);

            //send email
            String[] recipients = { user.getEmail() };
            SendEmailAsyncTask email = new SendEmailAsyncTask();
            email.m = new Mail("bitransoft@gmail.com", "bitransoft9395!");
            email.m.set_from("bitransoft@gmail.com");
            email.m.setBody(message);
            email.m.set_to(recipients);
            email.m.set_subject("Hóa Đơn Đặt Vé Xe:");
            email.execute();

            Toast.makeText(c,"Đặt Vé Thành Công!",Toast.LENGTH_SHORT).show();
            dismiss();
            pd.dismiss();

            c.startActivity(new Intent(c, MainActivity.class));
            ((Activity)c).finish();
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
        Toast.makeText(getApplicationContext(), "Đặt Vé Thành Công!", Toast.LENGTH_LONG).show();

    }

    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail m;
        public SendEmailAsyncTask() {}

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (m.send()) {
                    Toast.makeText(getApplicationContext(),"Email sent.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Email failed to send.", Toast.LENGTH_LONG).show();
                }

                return true;
            } catch (AuthenticationFailedException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
                e.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Authentication failed.", Toast.LENGTH_LONG).show();
                return false;
            } catch (MessagingException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Email failed");
                e.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Email failed to send.", Toast.LENGTH_LONG).show();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Unexpected error occured.", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}