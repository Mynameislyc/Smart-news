package com.example.administrator.mynew.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.news_bean.NewsObject;
import com.example.administrator.mynew.ui.NewswebActivity;
import com.example.administrator.mynew.util.Config.Config_smart;
import com.example.administrator.mynew.util.task.ImageDownload;
import com.example.administrator.mynew.util.If_show_secondType;
import com.example.administrator.mynew.util.task.ImageDownload_Volley;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.mynew.R.id.marqueeView;

/**
 * Created by Administrator on 2016/11/4.
 */
public class NewslistAdapter extends ArrayAdapter<NewsObject> {
    private int resourceID;
    boolean bigimage;

    private View view;
    private NewsObject newsObject;
    private ViewHolder_SecondImage viewHolder_secondImage=null;
    private ViewHolder_oneImage viewHolder_oneImage=null;
    private ViewHolder_oneImage_FIRST_ITEM viewHolder_oneImage_first_item=null;
    private ViewHolder_oneImage_SECOND_ITEM viewHolder_oneImage_second_item=null;

    public NewslistAdapter(Context context, int resource, List<NewsObject> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }
    private void init_Big_Image(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.newlist_item_ly_2, null);
        viewHolder_secondImage = new ViewHolder_SecondImage();
        viewHolder_secondImage.newsTitle2 = (TextView) view.findViewById(R.id.newsTitle2);
        viewHolder_secondImage.newsimage2 = (ImageView) view.findViewById(R.id.newsimageIntitle2_1);
        viewHolder_secondImage.newsdate2 = (TextView) view.findViewById(R.id.newsDate2);
        viewHolder_secondImage.realtype2=(TextView)view.findViewById(R.id.newstype);
        view.setTag(viewHolder_secondImage);
    }
    private void init_Small_Image(){
        view= LayoutInflater.from(getContext()).inflate(resourceID, null);
        viewHolder_oneImage=new ViewHolder_oneImage();
        viewHolder_oneImage.newsimage=(ImageView)view.findViewById(R.id.newsimageIntitle);
        viewHolder_oneImage.newsTitle=(TextView)view.findViewById(R.id.newsTitle);
        viewHolder_oneImage.newsdate=(TextView)view.findViewById(R.id.newsDate);
        view.setTag(viewHolder_oneImage);
    }
    private void init_head_FIRST_ITEM(){
        view=LayoutInflater.from(getContext()).inflate(R.layout.tab01_newslist_item_first,null);
        viewHolder_oneImage_first_item=new ViewHolder_oneImage_FIRST_ITEM();
        viewHolder_oneImage_first_item.rollPagerView=(RollPagerView)view.findViewById(R.id.rollpagerview);
        view.setTag(viewHolder_oneImage_first_item);
    }
    private void init_head_SECOND_ITEM(){
        view=LayoutInflater.from(getContext()).inflate(R.layout.tab01_newslist_item_second,null);
        viewHolder_oneImage_second_item=new ViewHolder_oneImage_SECOND_ITEM();
        viewHolder_oneImage_second_item.marqueeView = (MarqueeView) view.findViewById(marqueeView);
        view.setTag(viewHolder_oneImage_second_item);
    }

    //重新利用View
    private void get_big_or_small_viewTag(){
        if(bigimage){
            if(view.getTag()instanceof ViewHolder_SecondImage) {
                viewHolder_secondImage = (ViewHolder_SecondImage) view.getTag();
            }else {
                init_Big_Image();
            }
        }else {
            if(view.getTag()instanceof ViewHolder_oneImage) {
                viewHolder_oneImage = (ViewHolder_oneImage) view.getTag();
            }else {
                init_Small_Image();
            }
        }
    }
    private void setlistDatas(){
        if(bigimage){
            viewHolder_secondImage.newsTitle2.setText(newsObject.getTitle());
            viewHolder_secondImage.newsimage2.setImageResource(R.drawable.leftline_photos);
            if(newsObject.getBitmap()==null){
//                ImageDownload imageDownload=new ImageDownload(getContext(),viewHolder_secondImage.newsimage2,newsObject);
//                imageDownload.execute(newsObject.getThumbnail_picso3());
                new ImageDownload_Volley(view.getContext(),newsObject.getThumbnail_picso3(),viewHolder_secondImage.newsimage2,newsObject,null);
            }else {
                viewHolder_secondImage.newsimage2.setImageBitmap(newsObject.getBitmap());
            }
            viewHolder_secondImage.newsdate2.setText(newsObject.getData());
            viewHolder_secondImage.realtype2.setText(newsObject.getRealtype());
        }else{
            viewHolder_oneImage.newsTitle.setText(newsObject.getTitle());
            viewHolder_oneImage.newsimage.setImageResource(R.drawable.leftline_photos);
            viewHolder_oneImage.newsdate.setText(newsObject.getData());
            if(newsObject.getBitmap()==null){

                ImageDownload imageDownload = new ImageDownload(getContext(), viewHolder_oneImage.newsimage,newsObject);
                imageDownload.execute(newsObject.getThumbnail_pic_s());//异步加载图片
//                Context context, String url, ImageView image, final NewsObject newsObject, final WeiChat_jingsuan weiChat_jingsuan
//                Log.d("####","下载图片1");
//               new ImageDownload_Volley(view.getContext(),newsObject.getThumbnail_pic_s(),viewHolder_oneImage.newsimage,newsObject,null);
//                Log.d("####","下载图片2");
            }else {
                viewHolder_oneImage.newsimage.setImageBitmap(newsObject.getBitmap());
//                Log.d("####","viewHolder_oneImage.newsimage++++");

            }
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view=convertView;
        newsObject=getItem(position);
        bigimage=(newsObject.getType().equals("头条")&&(newsObject.getRealtype().equals("娱乐")||
                newsObject.getRealtype().equals("时尚")))||(newsObject.getCategory().equals("社会")&& If_show_secondType.if_show(newsObject))||
                (newsObject.getCategory().equals("国内")&& If_show_secondType.if_show(newsObject))||(newsObject.getCategory().equals("国际")&& If_show_secondType.if_show(newsObject))
        ||(newsObject.getCategory().equals("娱乐")&& If_show_secondType.if_show(newsObject))||(newsObject.getCategory().equals("体育")&& If_show_secondType.if_show(newsObject))
        ||(newsObject.getCategory().equals("军事")&& If_show_secondType.if_show(newsObject))||(newsObject.getCategory().equals("科技")&& If_show_secondType.if_show(newsObject))
        ||(newsObject.getCategory().equals("财经")&& If_show_secondType.if_show(newsObject))||(newsObject.getCategory().equals("时尚")&& If_show_secondType.if_show(newsObject));
//        Log.d("####","This is fragment:"+newsObject.getTitle()+"++++++++++++++"+newsObject.getRealtype()+"等于："+oneimage);
//        Log.d("####","这个是头条===="+(newsObject.getType()));

        if(convertView==null){
          if(newsObject.getType()=="头条"){//头条篇
              if(newsObject.getItem_type()==Config_smart.NEWSLIST_FIRST_ITEM) {
                  init_head_FIRST_ITEM();
              }
              else if(newsObject.getItem_type()==Config_smart.NEWSLIST_SECOND_ITEM) {
                  init_head_SECOND_ITEM();

              }
              else {
                  if(bigimage) {
                      init_Big_Image();
                }else{
                      init_Small_Image();
                }
              }
          }else {
              if(bigimage) {
                  init_Big_Image();
                }else{
                  init_Small_Image();
                }
          }
        }else {//view非空
            if(newsObject.getType().equals("头条")){
                if(newsObject.getItem_type()==Config_smart.NEWSLIST_FIRST_ITEM){
                    if(view.getTag()instanceof ViewHolder_oneImage_FIRST_ITEM){
                        viewHolder_oneImage_first_item=(ViewHolder_oneImage_FIRST_ITEM)view.getTag();
                    }else {
                        init_head_FIRST_ITEM();
                    }
                }else if(newsObject.getItem_type()==Config_smart.NEWSLIST_SECOND_ITEM){
                    if(view.getTag()instanceof ViewHolder_oneImage_SECOND_ITEM){
                        viewHolder_oneImage_second_item=(ViewHolder_oneImage_SECOND_ITEM)view.getTag();
                    }else {
                        init_head_SECOND_ITEM();
                    }
                }else {
                    get_big_or_small_viewTag();
                }
            }else {
                get_big_or_small_viewTag();
            }

            }
        /**
         * 设置数据
         */
        if(newsObject.getType().equals("头条")){
            if(newsObject.getItem_type()==Config_smart.NEWSLIST_FIRST_ITEM){
                //设置播放时间间隔
                viewHolder_oneImage_first_item.rollPagerView.setPlayDelay(2000);
            //设置透明度
                viewHolder_oneImage_first_item.rollPagerView.setAnimationDurtion(500);

            //设置适配器
                viewHolder_oneImage_first_item.rollPagerView.setAdapter(new Newslist_item_first_rollviewAdapter(getContext(),viewHolder_oneImage_first_item.rollPagerView,newsObject));
                viewHolder_oneImage_first_item.rollPagerView.setHintView(new ColorPointHintView(view.getContext(), Color.parseColor("#00000000"),Color.parseColor("#00000000")));

            }else if(newsObject.getItem_type()==Config_smart.NEWSLIST_SECOND_ITEM){

                List<String> info = new ArrayList<>();
                info.add("1. 大家好，我是卢昱诚。");
                info.add("2. 欢迎大家关注我哦！");

                viewHolder_oneImage_second_item.marqueeView.startWithList(info);
                viewHolder_oneImage_second_item.marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, TextView textView) {
                    NewswebActivity.actionStart3(view.getContext(),"https://github.com/Mynameislyc/Smart-news");
                    }
                });


            }else {
                setlistDatas();
            }
        }else {
            setlistDatas();
        }


////            if(convertView==null){
//                view= LayoutInflater.from(getContext()).inflate(resourceID, null);
////            }else {
////                view=convertView;
////            }
//            newsimage=(ImageView)view.findViewById(R.id.newsimageIntitle);
//            TextView title=(TextView)view.findViewById(R.id.newsTitle);
//            TextView date=(TextView)view.findViewById(R.id.newsDate);
//            newsimage.setImageResource(R.drawable.photos);
//            /**
//             * 如果对象里面没有图片，先用异步加载图片，并缓存在对象里面，进而达到图片缓存的效里
//             */
//            if(newsObject.getBitmap()==null){
//                ImageDownload imageDownload = new ImageDownload(getContext(), newsimage,newsObject);
//                imageDownload.execute(newsObject.getThumbnail_pic_s());//异步加载图片
//            }else {
//                newsimage.setImageBitmap(newsObject.getBitmap());
//            }
//            title.setText(newsObject.getTitle());
//            date.setText(newsObject.getData());
        return view;
    }

    class ViewHolder_SecondImage{
        TextView newsTitle2;
        ImageView newsimage2;
        TextView newsdate2;
        TextView realtype2;
    }
    class ViewHolder_oneImage{
        ImageView newsimage;
        TextView newsTitle;
        TextView newsdate;

    }
    class ViewHolder_oneImage_FIRST_ITEM{
        RollPagerView rollPagerView;
    }
    class ViewHolder_oneImage_SECOND_ITEM{
        MarqueeView marqueeView;
    }

}
