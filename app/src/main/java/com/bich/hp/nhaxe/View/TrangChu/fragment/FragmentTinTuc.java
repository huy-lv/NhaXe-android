package com.bich.hp.nhaxe.View.TrangChu.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;


import com.bich.hp.nhaxe.Adapter.NewsAdapter;
import com.bich.hp.nhaxe.Model.ObjectClass.NewsModel;

import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.View.TrangChu.NewsDetailActivity;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;



public class FragmentTinTuc extends Fragment {
   private   ListView lvNews;
   private NewsAdapter adapter;
    private final  String vnexpressRSS="http://vnexpress.net/rss/oto-xe-may.rss";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tintuc, container, false);
        lvNews=(ListView)view.findViewById(R.id.lvNews);

        lvNews.setOnItemClickListener(onItemClick);
         new LoadRSS().execute(vnexpressRSS);


        return view;

    }
    private AdapterView.OnItemClickListener onItemClick=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent detail=new Intent(getActivity(), NewsDetailActivity.class);
            detail.putExtra("LINK",adapter.getItem(position).getLink());
            startActivity(detail);
        }


    };
     class  LoadRSS extends AsyncTask<String,Void,ArrayList<NewsModel>>{
         ProgressDialog dialog;

         @Override
         protected void onPreExecute() {
             dialog=new ProgressDialog(getActivity());
             dialog.setMessage("Loading...!");
             dialog.setCancelable(false);
             dialog.show();
         }

         @Override
         protected ArrayList<NewsModel> doInBackground(String... params) {
             String url=params[0];
             ArrayList<NewsModel>result= new ArrayList<>();
             try {
                 Document doc= Jsoup.connect(url).get();
                 Elements elements = doc.select("item");
                 for (Element item :elements){
                 String title=item.select("title").text();
                     String link=item.select("link").text();
                     String description=item.select("description").text();

                     Document docImage=Jsoup.parse(description);
                   String imageURL=  docImage.select("img").get(0).attr("src");
                     result.add(new NewsModel(title,link,imageURL));
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return result;
         }

         @Override
         protected void onPostExecute(ArrayList<NewsModel> newsModels) {
             dialog.dismiss();
             adapter=new NewsAdapter(getContext(),0,newsModels,LayoutInflater.from(getActivity()));
             lvNews.setAdapter(adapter);
         }




     }




}
