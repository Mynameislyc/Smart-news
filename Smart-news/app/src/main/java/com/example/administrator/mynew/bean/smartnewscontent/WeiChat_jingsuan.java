package com.example.administrator.mynew.bean.smartnewscontent;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/11/8.
 */
public class WeiChat_jingsuan {//smart_news_content
    private String firstImg;
    private String id;
    private String source;
    private String url;
    private String mark;
    private String title;

    private int WEICHAT_ITEM_TYPE;
    private Bitmap bitmap=null;

    public int getWEICHAT_ITEM_TYPE() {
        return WEICHAT_ITEM_TYPE;
    }

    public void setWEICHAT_ITEM_TYPE(int WEICHAT_ITEM_TYPE) {
        this.WEICHAT_ITEM_TYPE = WEICHAT_ITEM_TYPE;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
