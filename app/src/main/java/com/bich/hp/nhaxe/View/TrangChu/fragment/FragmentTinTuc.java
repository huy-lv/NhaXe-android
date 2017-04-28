package com.bich.hp.nhaxe.View.TrangChu.fragment;

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

import com.bich.hp.nhaxe.Adapter.CustomAdapter;
import com.bich.hp.nhaxe.Model.ObjectClass.TinTuc;
import com.bich.hp.nhaxe.Model.TinTuc.XMLDOMParser;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.View.WebView.WebViewTinTuc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * Created by hp on 2/14/2017.
 */

public class FragmentTinTuc extends Fragment {
 ListView listView;
    CustomAdapter customAdapter;
    ArrayList<TinTuc> mangtintuc;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tintuc, container, false);
        listView=(ListView)view.findViewById(R.id.listview);
        mangtintuc= new ArrayList<TinTuc>();
        getActivity(). runOnUiThread(new Runnable(){

            @Override
            public void run() {
               new Readdata().execute("http://vnexpress.net/rss/the-gioi.rss");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it =new Intent(getContext(), WebViewTinTuc.class);
                it.putExtra("link",mangtintuc.get(position).link);
                startActivity(it);
            }




        });
        return view;

    }

        public class Readdata extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {

            XMLDOMParser parser=new XMLDOMParser();
            Document document=parser.getDocument(s);
            NodeList   nodeList=document.getElementsByTagName("item");
            NodeList    nodeListdesreption=document.getElementsByTagName("description");
            String hinhanh="";
            String title="";
            String link="";
            for(int i=0;i<nodeList.getLength();i++){
                String cdata=nodeListdesreption.item(i+1).getTextContent();
                Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher=p.matcher(cdata);
                if(matcher.find()){
                    hinhanh=matcher.group(1);
                }
                Element element=(Element)nodeList.item(i);

                title+=parser.getValue(element,"title");
                link=parser.getValue(element,"link");
                mangtintuc.add(new TinTuc(title,link,hinhanh));
            }
            customAdapter=new CustomAdapter(getActivity(),android.R.layout.simple_list_item_1,mangtintuc);
            listView.setAdapter(customAdapter);
            super.onPostExecute(s);
        }

        private String docNoiDung_Tu_URL(String theUrl) {
            StringBuilder content = new StringBuilder();

            try {
                // create a url object
                URL url = new URL(theUrl);

                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();

                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;

                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }

    }
}