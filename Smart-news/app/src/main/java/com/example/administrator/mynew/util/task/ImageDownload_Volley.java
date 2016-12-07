package com.example.administrator.mynew.util.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.news_bean.NewsObject;
import com.example.administrator.mynew.bean.smartnewscontent.WeiChat_jingsuan;

/**
 * Created by Administrator on 2016/11/18.
 */

public class ImageDownload_Volley {

    private static ImageView imageView;

    private static NewsObject newsObject;
    private static WeiChat_jingsuan weiChat_jingsuan;
    private static Bitmap bitmap=null;

    public  ImageDownload_Volley(Context context, String url, ImageView image,  NewsObject newsobject,  WeiChat_jingsuan weichat_jingsuan) {
        imageView = image;
        newsObject = newsobject;
        weiChat_jingsuan = weichat_jingsuan;
//        RequestQueue mQueue = Volley.newRequestQueue(context);

//        BitmapCache bitm = new BitmapCache();
//        ImageLoader imageLoader = new ImageLoader(mQueue, bitm);
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.leftline_photos, R.drawable.leftline_photos);
//        imageLoader.get(url, listener);

//        Log.d("####","这个是"+bitmap);
//        NetworkImageView networkImageView;
//        networkImageView.setImageUrl(url, listener);
//        String aaa=container.getRequestUrl();
//
//
//        bitmap=container.getBitmap();
//        bitmap=((BitmapDrawable)((ImageView)imageView).getDrawable()).getBitmap();
//        Log.d("####","这个是bitmap"+bitmap);
//        if(weiChat_jingsuan==null){
//            newsObject.setBitmap(bitmap);
//        }else if(newsObject==null){
//            weiChat_jingsuan.setBitmap(bitmap);
//        }


        ImageRequest request=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                if(weiChat_jingsuan==null){
                    newsObject.setBitmap(bitmap);
                }
                if(newsObject==null){
                    weiChat_jingsuan.setBitmap(bitmap);
                }

            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                imageView.setBackgroundResource(R.drawable.leftline_photos);
            }
        });
        RequestQueue queue= Volley.newRequestQueue(context.getApplicationContext());
        queue.add(request);


    }
}



//class BitmapCache implements ImageLoader.ImageCache {
//    private LruCache<String, Bitmap> cache;
//
//    public BitmapCache() {
//
//        cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                return bitmap.getRowBytes() * bitmap.getHeight();
//            }
//        };
//    }
//
//
//    @Override
//    public Bitmap getBitmap(String url) {
//
//        return cache.get(url);
//    }
//
//    @Override
//    public void putBitmap(String url, Bitmap bitmap) {
//        cache.put(url, bitmap);
//    }
//
//}
