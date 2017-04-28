package com.bich.hp.nhaxe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bich.hp.nhaxe.Model.ObjLichTrinhGiaVe.ObjLTGV;
import com.bich.hp.nhaxe.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;


public class LTGVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ObjLTGV> data= Collections.emptyList();
    ObjLTGV current;
    int currentPos=0;
    private Context context;
    private LayoutInflater inflater;

    public LTGVAdapter(Context context, int list_item_lichgiave, List<ObjLTGV> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_item_lichgiave, parent,false);
        LTGVAdapter.MyHolder holder=new LTGVAdapter.MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        NumberFormat numberFormat=new DecimalFormat("###,###");

        MyHolder myHolder= (MyHolder) holder;
        ObjLTGV current=data.get(position);
        myHolder.tvDiemDi.setText(current.DIEMDI);
        myHolder.tvDiemDen.setText(current.DIEMDEN);
        myHolder.tvTenLoTrinh.setText( current.TENTUYENXE);
        myHolder.tvGia.setText( (String.valueOf(numberFormat.format(current.GIA))));
        myHolder.tvSoKm.setText(current.TONGKM);
        myHolder.tvSoTramDung.setText( (String.valueOf(current.SOTRAMDUNG)));
        myHolder.tvThoiGian.setText( current.THOIGIANLOTRINH);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        public TextView tvDiemDi;
        public TextView tvDiemDen;
        public TextView tvThoiGian;
        public TextView tvSoKm;
        public TextView tvSoTramDung;
        public TextView tvTenLoTrinh;
        public TextView tvGia;



        public MyHolder(View itemView) {
            super(itemView);
            tvDiemDi = (TextView) itemView.findViewById(R.id.tvDiemDi);
            tvDiemDen = (TextView) itemView.findViewById(R.id.tvDiemDen);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
            tvSoKm = (TextView) itemView.findViewById(R.id.tvTongKm);
            tvSoTramDung = (TextView) itemView.findViewById(R.id.tvSOTRAMDUNG);
            tvTenLoTrinh = (TextView) itemView.findViewById(R.id.tvTENLOTRINH);
            tvGia = (TextView) itemView.findViewById(R.id.tvGia);

    }

}

}
