package com.example.administrator.mynew.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.smartnewscontent.WeiChat_jingsuan;

public class Weichat_friend_contentActivity extends Activity {

    private WebView webView;

    public static void actionStart(Context context,WeiChat_jingsuan weiChat_jingsuan){
        Intent intent=new Intent(context,Weichat_friend_contentActivity.class);
        intent.putExtra("weichat_address",weiChat_jingsuan.getUrl());
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weichat_friend_content);

        webView=(WebView)findViewById(R.id.weichat_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String weichat_address=getIntent().getStringExtra("weichat_address");
        webView.loadUrl(weichat_address);
    }
    public void onBack(View view){
        finish();
    }
}
