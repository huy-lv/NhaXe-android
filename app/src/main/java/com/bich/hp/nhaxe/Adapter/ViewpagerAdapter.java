package com.bich.hp.nhaxe.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bich.hp.nhaxe.View.TrangChu.fragment.FragmentChiNhanh;
import com.bich.hp.nhaxe.View.TrangChu.fragment.FragmentKhuyenMai;
import com.bich.hp.nhaxe.View.TrangChu.fragment.FragmentLichTrinhGiaVe;
import com.bich.hp.nhaxe.View.TrangChu.fragment.FragmentTaxi;
import com.bich.hp.nhaxe.View.TrangChu.fragment.FragmentTimVe;
import com.bich.hp.nhaxe.View.TrangChu.fragment.FragmentVanChuyenHangHoa;
import com.bich.hp.nhaxe.View.TrangChu.fragment.VeFunBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2/14/2017.
 */

public class ViewpagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList=new ArrayList<Fragment>();
    List<String> titlefragment=new ArrayList<String>();
    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentTimVe());
        fragmentList.add(new FragmentLichTrinhGiaVe());
        fragmentList.add(new FragmentVanChuyenHangHoa());
        fragmentList.add(new FragmentChiNhanh());

   //  fragmentList.add(new FragmentTinTuc());
       fragmentList.add(new FragmentKhuyenMai());
        fragmentList.add(new FragmentTaxi());
        fragmentList.add(new VeFunBus());
        titlefragment.add("Tìm Vé");
        titlefragment.add("Lịch Trình Giá Vé");
        titlefragment.add("Vận Chuyển Hàng Hóa");
        titlefragment.add("Chi Nhánh");

   //  titlefragment.add("Tin Tức");
       titlefragment.add("Khuyến Mãi");
        titlefragment.add("Taxi");
        titlefragment.add("Về FunBus");

    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titlefragment.get(position);
    }
}
