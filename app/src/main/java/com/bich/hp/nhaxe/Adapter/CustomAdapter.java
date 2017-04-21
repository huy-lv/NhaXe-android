package com.bich.hp.nhaxe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bich.hp.nhaxe.Model.ObjectClass.TinTuc;
import com.bich.hp.nhaxe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp on taxibg/13/2017.
 */

public class CustomAdapter extends ArrayAdapter<TinTuc> {


    public CustomAdapter(Context context, int resource, List<TinTuc> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.listtintuc, null);
        }
        TinTuc p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txttitle = (TextView) view.findViewById(R.id.tvtitle);
            txttitle.setText(p.title);
            ImageView imageView=(ImageView)view.findViewById(R.id.img);
            Picasso.with(getContext()).load(p.image).into(imageView);

        }
        return view;
    }

}
