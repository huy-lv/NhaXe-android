package com.bich.hp.nhaxe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bich.hp.nhaxe.Model.Lo_Trinh;
import com.bich.hp.nhaxe.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 11-Apr-17.
 */

public class LoTrinhAdapter extends RecyclerView.Adapter<LoTrinhAdapter.LotrinhVH> {

    ArrayList<Lo_Trinh> lotrinhs;
    Context c;

    public LoTrinhAdapter(Context cc, ArrayList<Lo_Trinh> l){
        c = cc;
        lotrinhs = l;
    }

    @Override
    public LotrinhVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LotrinhVH holder, int position) {
        Lo_Trinh l = lotrinhs.get(position);
        holder.tvDiemDi1.setText(l.getDiemdi());
        holder.tvDiemDen1.setText(l.getDiemden());
//        holder.tvNgay.setText(l.);
    }

    @Override
    public int getItemCount() {
        return lotrinhs.size();
    }

    class LotrinhVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tvDiemDi1) TextView tvDiemDi1;
        @BindView(R.id.tvDiemDen1) TextView tvDiemDen1;
        @BindView(R.id.tvTENLOTRINH) TextView tvTENLOTRINH;
        @BindView(R.id.tvNgay) TextView tvNgay;
        @BindView(R.id.tvGia1) TextView tvGia1;
        @BindView(R.id.btnTienHanhDatVe) Button btnTienHanhDatVe;
        public LotrinhVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
