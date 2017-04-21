package com.bich.hp.nhaxe.View.TrangChu;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bich.hp.nhaxe.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.bich.hp.nhaxe.View.TrangChu.MainActivity.SERVER_NAME;

public class BaoGiaVanChuyen extends AppCompatActivity {
    PDFView pdfview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_gia_van_chuyen);
        pdfview=(PDFView)findViewById(R.id.pdfview);
        // pdfview.fromAsset("giavanchuyen.pdf").load();
        new docpdf().execute(SERVER_NAME+"/baogia.pdf");
    }
    public class docpdf extends AsyncTask<String,Void,InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                if(httpURLConnection.getResponseCode()==200){
                    inputStream= new BufferedInputStream(httpURLConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;

            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfview.fromStream(inputStream).load();
        }
    }

}
