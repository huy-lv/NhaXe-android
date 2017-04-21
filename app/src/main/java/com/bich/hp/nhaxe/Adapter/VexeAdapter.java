package com.bich.hp.nhaxe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bich.hp.nhaxe.Model.Vexe;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.View.VeDaDatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 14-Apr-17.
 */

public class VexeAdapter extends RecyclerView.Adapter<VexeAdapter.VexeVH> {
    Context c;
    ArrayList<Vexe> vexes;


    public VexeAdapter(Context context, ArrayList<Vexe> vexes) {
        c = context;
        this.vexes = vexes;
    }

    @Override
    public VexeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VexeVH(LayoutInflater.from(c).inflate(R.layout.item_vexe, null));
    }

    @Override
    public void onBindViewHolder(VexeVH holder, final int position) {
        final Vexe v = vexes.get(position);
        holder.vexe_giokhoihanh.setText(v.getGiokhoihanh() + " " + v.getNgaykhoihanh());
        holder.vexe_lotrinh.setText(String.valueOf(v.getDiemdi()+"->"+v.getDiemden()));
        holder.vexe_tenghe.setText(v.getTenghe());
        holder.vexe_mave.setText(String.valueOf(v.getMave()));
        holder.vexe_tenkhachhang.setText(v.getTenkhachhang());
        holder.vexe_huyve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((VeDaDatActivity)c).huyve(v);

            }
        });
    }


    @Override
    public int getItemCount() {
        return vexes.size();
    }

    public class VexeVH extends RecyclerView.ViewHolder {
        @BindView(R.id.vexe_mave)
        TextView vexe_mave;
        @BindView(R.id.vexe_lotrinh)
        TextView vexe_lotrinh;
        @BindView(R.id.vexe_tenghe)
        TextView vexe_tenghe;
        @BindView(R.id.vexe_tenkhachhang)
        TextView vexe_tenkhachhang;
        @BindView(R.id.vexe_giokhoihanh)
        TextView vexe_giokhoihanh;
        @BindView(R.id.vexe_huyve)
        Button vexe_huyve;

        public VexeVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}