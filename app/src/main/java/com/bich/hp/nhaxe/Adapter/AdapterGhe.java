package com.bich.hp.nhaxe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bich.hp.nhaxe.CustomView.DialogChonGhe;
import com.bich.hp.nhaxe.Model.Ghe;
import com.bich.hp.nhaxe.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bich.hp.nhaxe.Utils.EMPTY;
import static com.bich.hp.nhaxe.Utils.OWNED;
import static com.bich.hp.nhaxe.Utils.SELECTED;

/**
 * Created by huylv on 12-Apr-17.
 */

public class AdapterGhe extends RecyclerView.Adapter<AdapterGhe.GheVH> {
    public ArrayList<Ghe> danhsachGhe;
    Context context;

    public AdapterGhe(Context c, ArrayList<Ghe> gheArrayList) {
        danhsachGhe = gheArrayList;
        context = c;
    }


    @Override
    public GheVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GheVH(LayoutInflater.from(context).inflate(R.layout.item_ghe, null));
    }

    //edit1
    @Override
    public void onBindViewHolder(GheVH holder, final int position) {
        final Ghe g = danhsachGhe.get(position);
        switch (g.getTrangthai()) {
            case EMPTY:
                holder.item_ghe_iv.setImageResource(R.drawable.seat_layout_tab_nor_avl);
                break;
            case OWNED:
                holder.item_ghe_iv.setImageResource(R.drawable.seat_layout_tab_nor_bkd);
                break;
            case SELECTED:
                holder.item_ghe_iv.setImageResource(R.drawable.seat_layout_tab_nor_std);
                break;
        }
        holder.item_ghe_tenghe.setText(String.valueOf(g.getTenghe()));
        holder.item_ghe_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (g.getTrangthai() == EMPTY) {
                    final DialogChonGhe dialog = new DialogChonGhe(context, g.getTenKhachHang());
                    dialog.setClick(new DialogChonGhe.OnClickOk() {
                        @Override
                        public void OnClickOk(String s) {
                            danhsachGhe.get(position).setTenKhachHang(s);
                            danhsachGhe.get(position).setTrangthai(SELECTED);
                            dialog.dismiss();
                            notifyItemChanged(position);
                        }
                    });
                    dialog.show();
                } else if (g.getTrangthai() == SELECTED) {
                    danhsachGhe.get(position).setTrangthai(EMPTY);
                    danhsachGhe.get(position).setTenKhachHang(null);
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhsachGhe.size();
    }

    public ArrayList<Ghe> getSelectedList() {
        ArrayList<Ghe> ccc = new ArrayList<>();
        for (Ghe g : danhsachGhe) if (g.getTrangthai() == SELECTED) ccc.add(g);
        return ccc;
    }

    class GheVH extends RecyclerView.ViewHolder {
        @BindView(R.id.item_ghe_iv)        ImageView item_ghe_iv;
        @BindView(R.id.item_ghe_tenghe)        TextView item_ghe_tenghe;
        @BindView(R.id.item_ghe_ll)        LinearLayout item_ghe_ll;

        public GheVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
