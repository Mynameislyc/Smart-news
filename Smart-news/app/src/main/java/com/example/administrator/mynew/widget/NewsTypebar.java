package com.example.administrator.mynew.widget;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mynew.R;

/**
 * Created by Administrator on 2016/11/7.
 */
public class NewsTypebar {
    public  static TextView top;//头条
    public  static TextView shehui;//社会
    public  static TextView guonei;//国内
    public  static TextView guoji;//国际
    public  static TextView yule;//娱乐
    public  static TextView tiyu;//体育
    public  static TextView junshi;//军事
    public  static TextView keji;//科技
    public  static TextView caijing;//财经
    public  static TextView shishang;//时尚
    public static void initTextView(View view){
        top=(TextView)view.findViewById(R.id.top);
        shehui=(TextView)view.findViewById(R.id.shehui);
        guonei=(TextView)view.findViewById(R.id.guonei);
        guoji=(TextView)view.findViewById(R.id.guoji);
        yule=(TextView)view.findViewById(R.id.yule);
        tiyu=(TextView)view.findViewById(R.id.tiyu);
        junshi=(TextView)view.findViewById(R.id.junshi);
        keji=(TextView)view.findViewById(R.id.keji);
        caijing=(TextView)view.findViewById(R.id.caijing);
        shishang=(TextView)view.findViewById(R.id.shishang);
    }
    public static void setTextViewClickListener(View.OnClickListener clickListener){
        top.setOnClickListener(clickListener);
        shehui.setOnClickListener(clickListener);
        guonei.setOnClickListener(clickListener);
        guoji.setOnClickListener(clickListener);
        yule.setOnClickListener(clickListener);
        tiyu.setOnClickListener(clickListener);
        junshi.setOnClickListener(clickListener);
        keji.setOnClickListener(clickListener);
        caijing.setOnClickListener(clickListener);
        shishang.setOnClickListener(clickListener);
    }
    public static void initTypebarText_Color(){
        top.setTextColor(Color.parseColor("#000000"));
        shehui.setTextColor(Color.parseColor("#000000"));
        guonei.setTextColor(Color.parseColor("#000000"));
        guoji.setTextColor(Color.parseColor("#000000"));
        yule.setTextColor(Color.parseColor("#000000"));
        tiyu.setTextColor(Color.parseColor("#000000"));
        junshi.setTextColor(Color.parseColor("#000000"));
        keji.setTextColor(Color.parseColor("#000000"));
        caijing.setTextColor(Color.parseColor("#000000"));
        shishang.setTextColor(Color.parseColor("#000000"));
    }
}
