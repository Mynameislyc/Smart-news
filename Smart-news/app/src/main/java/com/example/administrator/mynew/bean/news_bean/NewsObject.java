package com.example.administrator.mynew.bean.news_bean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/10/28.
 */
public class NewsObject {
   private String title;//标题
    private String data;//时间
    private String author;//作者
    private String thumbnail_pic_s;//image1
    private String thumbnail_pic_so2;//image2
    private String thumbnail_picso3;//image3
    private String url;//新闻链接
    private String uniquekey;//唯一标识
    private String type="";//类型一
    private String realtype="";//类型二
    private String category="";
    private int item_type;
    private Bitmap bitmap=null;
    private NewsObject_head newsObject_head[];

    public void setNewsObject_head(NewsObject_head[] newsObject_head) {
        this.newsObject_head = newsObject_head;
    }

    public NewsObject_head[] getNewsObject_head() {
        return newsObject_head;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String categery) {
        this.category = categery;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_so2() {
        return thumbnail_pic_so2;
    }

    public void setThumbnail_pic_so2(String thumbnail_pic_so2) {
        this.thumbnail_pic_so2 = thumbnail_pic_so2;
    }

    public String getThumbnail_picso3() {
        return thumbnail_picso3;
    }

    public void setThumbnail_picso3(String thumbnail_picso3) {
        this.thumbnail_picso3 = thumbnail_picso3;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }
}
