package com.bich.hp.nhaxe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bich.hp.nhaxe.Model.Ghe;
import com.bich.hp.nhaxe.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class AdapterGheDaChon extends RecyclerView.Adapter<AdapterGheDaChon.GheDaChonVH> {
    Context context;
    ArrayList<Ghe> ghedachon;

    public AdapterGheDaChon(Context c, ArrayList<Ghe> gg){
        context = c;
        ghedachon = gg;
    }

    @Override
    public GheDaChonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GheDaChonVH(LayoutInflater.from(context).inflate(R.layout.item_ghe_da_chon,null));
    }

    @Override
    public void onBindViewHolder(GheDaChonVH holder, int position) {
        holder.tv_ghedachon_tenghe.setText(String.valueOf(ghedachon.get(position).getTenghe()));
        holder.tv_ghedachon_ten.setText(ghedachon.get(position).getTenKhachHang());
    }

    @Override
    public int getItemCount() {
        return ghedachon.size();
    }

    class GheDaChonVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_ghedachon_tenghe)        TextView tv_ghedachon_tenghe;
        @BindView(R.id.tv_ghedachon_ten)        TextView tv_ghedachon_ten;
        public GheDaChonVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
