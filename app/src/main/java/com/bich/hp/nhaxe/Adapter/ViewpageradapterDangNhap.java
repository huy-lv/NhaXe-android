package com.bich.hp.nhaxe.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bich.hp.nhaxe.View.DangNhap_DangKy.FragMent.FragmentDangKy;
import com.bich.hp.nhaxe.View.DangNhap_DangKy.FragMent.FragmentDangNhap;

/**
 * Created by hp on 2/17/2017.
 */

public class ViewpageradapterDangNhap extends FragmentPagerAdapter {
    public ViewpageradapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentDangNhap fragMentDangNhap=new FragmentDangNhap();
                return fragMentDangNhap;
            case 1:
                FragmentDangKy fragmentDangKy=new FragmentDangKy();
                return fragmentDangKy;
            default:return null;
        }

    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:

                return "Đăng Nhập";
            case 1:

                return "Đăng Ký";
            default:return null;
        }
    }
}
