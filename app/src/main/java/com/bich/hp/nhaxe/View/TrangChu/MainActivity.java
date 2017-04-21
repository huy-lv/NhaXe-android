package com.bich.hp.nhaxe.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bich.hp.nhaxe.Adapter.ViewpagerAdapter;
import com.bich.hp.nhaxe.Model.DangNhap_DangKy.ModelDangNhap;
import com.bich.hp.nhaxe.Model.User;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;
import com.bich.hp.nhaxe.View.DangNhap_DangKy.DangNhapActivity;
import com.bich.hp.nhaxe.View.MapsActivity;
import com.bich.hp.nhaxe.View.VeDaDatActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    //    public static final String SERVER_NAME="http://192.168.30.138/WebQLNX/dist/";
    public static final String SERVER_NAME = "https://quanlynhaxe-huylv177.c9users.io";
    TabLayout tabLayout;
    ViewPager viewPager;
    PreSenterXuLyLogic xulylogic;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;
    MenuItem itemDangNhap, menuITDangXuat;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        xulylogic = new PreSenterXuLyLogic(this);
        modelDangNhap = new ModelDangNhap();
        modelDangNhap.checkDangNhap(this);

        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, this);

        xulylogic.LayTokenDungFacebook();
        Button btn = (Button) findViewById(R.id.btnsearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(it);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
        itemDangNhap = menu.findItem(R.id.itDangNhap);
        menuITDangXuat = menu.findItem(R.id.itDangXuat);

        //facebook
//        accessToken = xulylogic.LayTokenDungFacebook();
//        googleSignInResult = modelDangNhap.LayThongDangNhapGoogle(mGoogleApiClient);
//        if (accessToken != null) {
//            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//                @Override
//                public void onCompleted(JSONObject object, GraphResponse response) {
//                    try {
//                        if(object==null) tennguoidung = "Network error!";
//                        else{
//                            tennguoidung = object.getString("name");
//                            String email = object.getString("email");
//                            Utils.getCurrentUser().setEmail(email);
//                        }
//                        itemDangNhap.setTitle(tennguoidung);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        Bundle parameter = new Bundle();
//        parameter.putString("fields", "name,email");
//
//        graphRequest.setParameters(parameter);
//        graphRequest.executeAsync();
//    }


        if (googleSignInResult != null) {
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
            Log.d("goo", googleSignInResult.getSignInAccount().getDisplayName());
        }

        if (Utils.LOGGEDIN) {
            itemDangNhap.setTitle(Utils.getCurrentUser().getName());
            menuITDangXuat.setVisible(true);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                if (!Utils.LOGGEDIN) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.itDonHangCuaToi:
                if (Utils.LOGGEDIN){
                    startActivity(new Intent(this, VeDaDatActivity.class));
                }else{
                    Utils.showInfoDialog(this,"Ban chua dang nhap!");
                }
                break;

            case R.id.itDangXuat:
                modelDangNhap.dangXuat(this);
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);

                }

//                if (!modelDangNhap.LayCachedDangNhap(this).equals("")) {
//                    modelDangNhap.CapNhatCachedDangNhap(this, "");
//                    this.menu.clear();
//                    this.onCreateOptionsMenu(this.menu);
//                }


        }

        return true;
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
