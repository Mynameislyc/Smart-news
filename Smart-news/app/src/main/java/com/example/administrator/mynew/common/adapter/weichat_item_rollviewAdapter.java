package com.example.administrator.mynew.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.news_bean.NewsObject;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by Administrator on 2016/11/18.
 */

public class weichat_item_rollviewAdapter extends StaticPagerAdapter{

        private int[]imags={
                R.drawable.n11,
                R.drawable.n12,
                R.drawable.n13
        };

    private String title[]={"你妹呀","我我我我我我我奇特我","囧佛挡杀佛地方"};
    private Bitmap bitmap[];
    private String url[];

    private Context context;
    private NewsObject newsObject_head;
    public weichat_item_rollviewAdapter(Context context, RollPagerView viewPager) {
        this.context=context;
    }

    @Override
        public View getView(ViewGroup container, final int position) {

        View view= LayoutInflater.from(context).inflate(R.layout.rollwviewpager_layout,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.rollpagerview_image);
        TextView textView=(TextView)view.findViewById(R.id.rollpagerview_text);
//        if(newsObject_head.getNewsObject_head()[position].getBitmap()==null){
//            imageView.setImageResource(imags[position]);
//            ImageDownload download=new ImageDownload(imageView,newsObject_head.getNewsObject_head()[position]);
//            download.execute(newsObject_head.getNewsObject_head()[position].getUrl());
//        }else {
//            imageView.setImageBitmap(newsObject_head.getNewsObject_head()[position].getBitmap());
//        }
//        if(newsObject_head==null)
//            textView.setText(title[position]);
//        else
//            textView.setText(newsObject_head.getNewsObject_head()[position].getTitle());
        imageView.setImageResource(imags[position]);
        textView.setText(title[position]);
//            final int picNo = position + 1;
//            ImageView view = new ImageView(container.getContext());
//        Log.d("####","___________"+newsObject_head.getNewsObject_head().length);
//            view.setImageResource(imags[position]);
//            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(context, "点击了第" + position + "张图片", Toast.LENGTH_SHORT).show();
                }

            });
            return view;
        }

//    @Override
//    public int getRealCount()
//    {
//        return imags.length;
//    }

    @Override
    public int getCount() {
        return imags.length;
    }
//        @Override
//        public int getCount() {
//            return imags.length;
//        }
    }

