package com.bich.hp.nhaxe.View.TrangChu.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.View.TrangChu.BaoGiaVanChuyen;
import com.bich.hp.nhaxe.View.TrangChu.DongGoiHangHoa;

/**
 * Created by hp on 2/14/2017.
 */

public class FragmentVanChuyenHangHoa extends Fragment {
    Button btnBaoGia, btncallvc;
    private LinearLayout container;
    private FragmentActivity myContext;
    Button btndonggoi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_vanchuyenhanghoa, container, false);
        btnBaoGia = (Button) view.findViewById(R.id.btnBaoGia);
        btncallvc = (Button) view.findViewById(R.id.btncallvc);
        btndonggoi = (Button) view.findViewById(R.id.btndonggoi);
        btndonggoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), DongGoiHangHoa.class);
                startActivity(it);
            }
        });

        btncallvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + 19008192));
                startActivity(callIntent);
            }
        });
        btnBaoGia = (Button) view.findViewById(R.id.btnBaoGia);
        btnBaoGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), BaoGiaVanChuyen.class);
                startActivity(it);
            }
        });
        return view;
    }


}