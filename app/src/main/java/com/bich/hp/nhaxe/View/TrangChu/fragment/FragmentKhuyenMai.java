package com.bich.hp.nhaxe.View.TrangChu.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bich.hp.nhaxe.R;

import java.util.List;


/**
 * Created by hp on 2/14/2017.
 */

@TargetApi(Build.VERSION_CODES.M)
public class FragmentKhuyenMai extends Fragment  {





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_khuyenmai, container, false);



        return view;


    }



}



