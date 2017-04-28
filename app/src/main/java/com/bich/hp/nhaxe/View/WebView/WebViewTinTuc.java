package com.bich.hp.nhaxe.View.WebView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bich.hp.nhaxe.R;

public class WebViewTinTuc extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_tin_tuc);
        webView=(WebView)findViewById(R.id.webview);
        Intent it=getIntent();
        String duonglink=it.getStringExtra("link");
        webView.loadUrl(duonglink);
        webView.setWebViewClient(new WebViewClient());
    }

}
