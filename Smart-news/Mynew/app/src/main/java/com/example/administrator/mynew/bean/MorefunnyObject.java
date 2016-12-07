package com.example.administrator.mynew.bean;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MorefunnyObject {

    private String morefunnytitle;
    private String morefunnycontent;
    private int imageID;
    private int TYPE;

    public MorefunnyObject(int TYPE,String morefunnytitle, String morefunnycontent, int imageID) {
        this.TYPE=TYPE;
        this.morefunnytitle = morefunnytitle;
        this.morefunnycontent = morefunnycontent;
        this.imageID = imageID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public int getImageID() {
        return imageID;
    }

    public String getMorefunnycontent() {
        return morefunnycontent;
    }

    public String getMorefunnytitle() {
        return morefunnytitle;
    }

}
