package com.example.administrator.mynew.util.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.administrator.mynew.bean.news_bean.NewsObject;
import com.example.administrator.mynew.bean.news_bean.NewsObject_head;
import com.example.administrator.mynew.bean.smartnewscontent.WeiChat_jingsuan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ImageDownload extends AsyncTask<String,Integer,Bitmap> {

    private int TYPE;
    private byte[] picByte=null;
    private ImageView testimage;
    private NewsObject newsObject;
    private NewsObject_head newsObject_head;

    private WeiChat_jingsuan weiChat_jingsuan;
    private ImageView weichat_imageview;
    private ImageView imageView;

    public ImageDownload(Context context,ImageView testimage,NewsObject newsObject){
        this.testimage=testimage;
        this.newsObject=newsObject;
        TYPE=1;
    }


    public ImageDownload(ImageView weichat_imageview,WeiChat_jingsuan weiChat_jingsuan)
    {
        this.weiChat_jingsuan=weiChat_jingsuan;
        this.weichat_imageview=weichat_imageview;
        TYPE=2;

    }
    public ImageDownload(ImageView imageView,NewsObject_head newsObjectHead){
        this.imageView=imageView;
        this.newsObject_head=newsObjectHead;
        TYPE=3;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap=null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(10000);

            if (conn.getResponseCode() == 200) {
                InputStream fis =  conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int length = -1;
                while ((length = fis.read(bytes)) != -1) {
                    bos.write(bytes, 0, length);
                }
                picByte = bos.toByteArray();
                bos.close();
                fis.close();
                if (picByte != null) {
                    bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);

                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
//        Log.d("####","下载完成"+strings[0]);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(TYPE==1) {
            newsObject.setBitmap(bitmap);
            testimage.setImageBitmap(bitmap);
        }else if(TYPE==2){
            weichat_imageview.setImageBitmap(bitmap);
            weiChat_jingsuan.setBitmap(bitmap);
        }else if(TYPE==3){
            imageView.setImageBitmap(bitmap);
            newsObject_head.setBitmap(bitmap);
        }
    }
}
