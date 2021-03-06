package com.bich.hp.nhaxe.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.bich.hp.nhaxe.Model.User;
import com.bich.hp.nhaxe.Utils;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.gson.Gson;



public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFacebookHienTai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;

    }

    public String LayCachedDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv","");

        return tennv;
    }

//    public void CapNhatCachedDangNhap(Context context,String tenv){
//        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = cachedDangNhap.edit();
//        editor.putString("tennv",tenv);
//
//        editor.commit();
//    }

    public void CapNhatCachedDangNhap(Context context,String user){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Utils.SP_USER,user);
        editor.apply();
    }

    public void checkDangNhap(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String user = sp.getString(Utils.SP_USER,null);
        if(user!=null){
            Utils.LOGGEDIN = true;
            User s = (new Gson()).fromJson(user,User.class);
            Utils.setCurrentUser(s);
        }else{
            Utils.LOGGEDIN = false;
        }
    }

//    public boolean KiemTraDangNhap(Context context,String tendangnhap, String matkhau){
//        boolean kiemtra = false;
//        String duongdan = MainActivity.SERVER_NAME+ "/nhaxe.php";
//        List<HashMap<String,String>> attrs = new ArrayList<>();
//
//        HashMap<String,String> hsHam = new HashMap<>();
//        hsHam.put("ham","KiemTraDangNhap");
//
//        HashMap<String,String> hsTenDangNhap = new HashMap<>();
//        hsTenDangNhap.put("tendangnhap",tendangnhap);
//
//        HashMap<String,String> hsMatKhau = new HashMap<>();
//        hsMatKhau.put("matkhau",matkhau);
//
//        attrs.add(hsHam);
//        attrs.add(hsTenDangNhap);
//        attrs.add(hsMatKhau);
//
//        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
//        downloadJSON.execute();
//
//        try {
//            String dulieu = downloadJSON.get();
//            JSONObject jsonObject = new JSONObject(dulieu);
//            String jsonKetQua = jsonObject.getString("ketqua");
//            if(jsonKetQua.equals("true")){
//                kiemtra = true;
//                String tennv = jsonObject.getString("tennv");
//
//                CapNhatCachedDangNhap(context,tennv);
//
//            }else{
//                kiemtra = false;
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return kiemtra;
//    }

    public GoogleApiClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context),failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongDangNhapGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            return opr.get();
        }else{
            return null;
        }
    }

    public void HuyTokenTracker(){
        accessTokenTracker.stopTracking();
    }

    public void dangXuat(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.apply();
    }
}
