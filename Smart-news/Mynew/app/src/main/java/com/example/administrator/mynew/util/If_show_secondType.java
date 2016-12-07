package com.example.administrator.mynew.util;

import com.example.administrator.mynew.bean.news_bean.NewsObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/8.
 */
public class If_show_secondType {
    /*帮助新闻是否要显示第二种布局*/
    public static boolean if_show(NewsObject newsObject){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time=newsObject.getData();
        long yushu;
        Date date = null;
        try {
            date = format.parse(time);
            yushu=date.getMinutes()%3;
            if(yushu==0)
                return true;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
