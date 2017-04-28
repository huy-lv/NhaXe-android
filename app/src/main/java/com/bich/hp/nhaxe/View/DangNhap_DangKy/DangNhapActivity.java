package com.bich.hp.nhaxe.View.DangNhap_DangKy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bich.hp.nhaxe.Adapter.ViewpageradapterDangNhap;
import com.bich.hp.nhaxe.R;

/**
 * Created by hp on 2/17/2017.
 */

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        tabLayout=(TabLayout)findViewById(R.id.tabDangNhap);
        viewPager=(ViewPager)findViewById(R.id.viewpagerDangNhap);
        toolbar=(Toolbar)findViewById(R.id.toolbarDangNhap);

         setSupportActionBar(toolbar);



        ViewpageradapterDangNhap viewpageradapterDangNhap=new ViewpageradapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewpageradapterDangNhap);
        viewpageradapterDangNhap.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }
}
