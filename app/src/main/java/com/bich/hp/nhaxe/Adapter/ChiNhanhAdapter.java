package com.bich.hp.nhaxe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import com.bumptech.glide.Glide;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;

import android.widget.TextView;

import com.bich.hp.nhaxe.Model.ChiNhanhNhaXe.ObjChiNhanhNhaXe;
import com.bich.hp.nhaxe.R;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.bich.hp.nhaxe.View.TrangChu.MainActivity.SERVER_NAME;

/**
 * Created by hp on taxibg/14/2017.
 */

public class ChiNhanhAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<ObjChiNhanhNhaXe> data= Collections.emptyList();
    ObjChiNhanhNhaXe current;
    int currentPos=0;


    public ChiNhanhAdapter(Context context, List<ObjChiNhanhNhaXe> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.lvitemchinhanhnhaxe, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MyHolder myHolder= (MyHolder) holder;
        ObjChiNhanhNhaXe current=data.get(position);
        myHolder.tvTenCN.setText(current.TENCN);
        myHolder.tvDiaChi.setText(current.DIACHICN);
        myHolder.tvEmail.setText( current.EMAILCN);
        myHolder.tvSDT.setText( current.SDTCN);



        Glide.with(context).load(SERVER_NAME+"/chinhanhnhaxe/" + current.HINHANH)
                .placeholder(R.drawable.ic_error_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(myHolder.img);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        public TextView tvTenCN;
        public TextView tvSDT;
        public TextView tvEmail;
        public TextView tvDiaChi;
        ImageView img;


        public MyHolder(View itemView) {
            super(itemView);

            img= (ImageView) itemView.findViewById(R.id.imgCN);
            tvTenCN = (TextView) itemView.findViewById(R.id.tvTenCN);
            tvDiaChi = (TextView) itemView.findViewById(R.id.tvDiaChi);
            tvSDT = (TextView) itemView.findViewById(R.id.tvSDT);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        }

    }

}
