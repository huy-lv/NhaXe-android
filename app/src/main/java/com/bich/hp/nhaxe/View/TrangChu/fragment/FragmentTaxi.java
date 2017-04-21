package com.bich.hp.nhaxe.View.TrangChu.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bich.hp.nhaxe.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by hp on 2/14/2017.
 */

public class FragmentTaxi extends Fragment {
    Button btnCall;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_taxi,container,false);
        btnCall=(Button)view.findViewById(R.id.btncall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           goitaxi();
            }
        });
        return view;

    }
    private  void goitaxi(){
       // Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("19008192"));

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+ 19008193));
        startActivity(callIntent);
    }
}
