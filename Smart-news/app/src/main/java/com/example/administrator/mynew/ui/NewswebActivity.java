package com.example.administrator.mynew.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.news_bean.NewsObject;
import com.example.administrator.mynew.bean.news_bean.NewsObject_head;

public class NewswebActivity extends Activity {

    private WebView webView;

    public static void actionStart(Context context,NewsObject newsObject){
        Intent intent=new Intent(context,NewswebActivity.class);
        intent.putExtra("newsaddress",newsObject.getUrl());
        context.startActivity(intent);
    }
    public static void actionStart2(Context context,NewsObject_head newsObject_head){
        Intent intent=new Intent(context,NewswebActivity.class);
        intent.putExtra("newsaddress",newsObject_head.getUrl());
        context.startActivity(intent);
    }
    public static void actionStart3(Context context,String str){
        Intent intent=new Intent(context,NewswebActivity.class);
        intent.putExtra("newsaddress",str);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsweb);

        webView=(WebView)findViewById(R.id.news_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String newsAddress=getIntent().getStringExtra("newsaddress");
        webView.loadUrl(newsAddress);
    }
    public void onBack(View view){
        finish();
    }


}
