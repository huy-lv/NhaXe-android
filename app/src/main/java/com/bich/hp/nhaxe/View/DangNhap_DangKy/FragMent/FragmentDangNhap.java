package com.bich.hp.nhaxe.View.DangNhap_DangKy.FragMent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bich.hp.nhaxe.ConnectInternet.LoginResponse;
import com.bich.hp.nhaxe.ConnectInternet.RetrofitClient;
import com.bich.hp.nhaxe.Model.DangNhap_DangKy.ModelDangNhap;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;
import com.bich.hp.nhaxe.View.TrangChu.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FragmentDangNhap extends Fragment implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{
    public static int SIGN_IN_GOOGLE_PLUS = 111;
    Button btnDangNhapFacebook,btnDangNhapGoogle,btnDangNhap;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    ProgressDialog progressDialog;
    ModelDangNhap modelDangNhap;
    EditText edsdt,edMatKhau;
    ProgressDialog pd;
    TextView tvQuenMatKhau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap,container,false);

        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(),this);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setIndeterminate(true);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //btnDangNhapFacebook = (Button) view.findViewById(R.id.btnDangnhapfacebook);
       // btnDangNhapGoogle = (Button) view.findViewById(R.id.btnDangnhapgoogle);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        edsdt = (EditText) view.findViewById(R.id.edDiaChiEmailDangNhap);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDangNhap);

        btnDangNhapFacebook.setOnClickListener(this);
        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        tvQuenMatKhau =(TextView)view.findViewById(R.id.tvQuenMatKhau);
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

           /* case R.id.btnDangnhapfacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile,email"));
                break;

            case R.id.btnDangnhapgoogle:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGooglePlus,SIGN_IN_GOOGLE_PLUS);
                showProcessDialog();
                break;   */

            case R.id.btnDangNhap:
                final String tendangnhap = edsdt.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                pd.show();
                RetrofitClient.getAPIInterface().dangnhap(tendangnhap,matkhau).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        pd.dismiss();
                        if(response.body().getError()==0) {//thanh cong
                            Log.e("cxz","user:"+response.body().getData().get(0));
                            Utils.setCurrentUser(response.body().getData().get(0));
                            modelDangNhap = new ModelDangNhap();
                            Gson gson = new Gson();
                            modelDangNhap.CapNhatCachedDangNhap(getActivity(),gson.toJson(Utils.getCurrentUser()));
                            Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
                            startActivity(iTrangChu);
                        }else {
                            Utils.showInfoDialog(getActivity(),"Tên đăng nhập và mật khẩu không đúng !");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Utils.showInfoDialog(getActivity(),t.getMessage());
                    }
                });
        }

    }

    private void showProcessDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode == SIGN_IN_GOOGLE_PLUS){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
                startActivity(iTrangChu);
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        progressDialog.cancel();
    }
}