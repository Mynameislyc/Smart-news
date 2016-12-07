package com.example.administrator.mynew.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.smartnewscontent.WeiChat_jingsuan;
import com.example.administrator.mynew.ui.Morefunny_RobotActivity;
import com.example.administrator.mynew.util.Config.Config_smart;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Friend_weichatAdapter extends ArrayAdapter<WeiChat_jingsuan>{
    private int resourceID;

    private WeiChat_jingsuan weiChat_jingsuan=null;

    private Viewholder_weichat_first viewholder_weichat_first;
    private Viewholder_weichat_second viewholder_weichat_second;

    private View view=null;
    public Friend_weichatAdapter(Context context, int resource, List<WeiChat_jingsuan> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }


    private void init_weichat_FIRST_ITEM(){
        view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        viewholder_weichat_first=new Viewholder_weichat_first();
        viewholder_weichat_first.weichar_rollpagerView=(RollPagerView)view.findViewById(R.id.weichat_rollpagerview);
        viewholder_weichat_first.weixinjingsuan=(LinearLayout)view.findViewById(R.id.weichatjingsuan);
        viewholder_weichat_first.lishidejintian=(LinearLayout)view.findViewById(R.id.lishidejintian);
        viewholder_weichat_first.xiaohuadaquan=(LinearLayout)view.findViewById(R.id.xiaohuadaquan);
        viewholder_weichat_first.jiqiwenda=(LinearLayout)view.findViewById(R.id.jiqiwenda);
        viewholder_weichat_first.nbasaishi=(LinearLayout)view.findViewById(R.id.nbasaishi);
        viewholder_weichat_first.zuqiuliansai=(LinearLayout)view.findViewById(R.id.zuqiuliansai);
        viewholder_weichat_first.xuexishengdi=(LinearLayout)view.findViewById(R.id.xuexishengdi);
        viewholder_weichat_first.gengduo=(LinearLayout)view.findViewById(R.id.gengduo);

        view.setTag(viewholder_weichat_first);
    }
    private void init_weichat_SECOND_ITEM(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.tab02_item_ly, null);
        viewholder_weichat_second=new Viewholder_weichat_second();
        viewholder_weichat_second.imageView=(ImageView)view.findViewById(R.id.weichat_item_ly_image);
        viewholder_weichat_second.textView=(TextView)view.findViewById(R.id.weichat_item_ly_title);

        viewholder_weichat_second.tab_02_item_ly_laiyuan=(LinearLayout) view.findViewById(R.id.tab_02_item_ly_laiyuan) ;
        viewholder_weichat_second.tab_02_item_ly_content=(LinearLayout) view.findViewById(R.id.tab_02_item_ly_content);
        viewholder_weichat_second.tab_02_item_ly_like=(LinearLayout) view.findViewById(R.id.tab_02_item_ly_like);
        viewholder_weichat_second.tab_02_item_ly_share=(LinearLayout) view.findViewById(R.id.tab_02_item_ly_share);
        viewholder_weichat_second.tab_02_item_ly_command=(LinearLayout) view.findViewById(R.id.tab_02_item_ly_command);

        viewholder_weichat_second.tab_02_item_ly_imageaview=(ImageView)view.findViewById(R.id.tab_02_item_ly_imageaview);
        viewholder_weichat_second.tab_02_item_ly_config=(ImageView)view.findViewById(R.id.tab_02_item_ly_config);
        viewholder_weichat_second.tab_02_item_ly_laiyuan_title=(TextView)view.findViewById(R.id.weichat_item_ly_title);
        viewholder_weichat_second.tab_02_item_ly_data=(TextView)view.findViewById(R.id.weichat_item_ly_title);
        viewholder_weichat_second.tab_02_item_ly_laiyuan_address=(TextView)view.findViewById(R.id.weichat_item_ly_title);

        view.setTag(viewholder_weichat_second);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        weiChat_jingsuan = getItem(position);
        view = convertView;

        if(view==null) {
            if (weiChat_jingsuan.getWEICHAT_ITEM_TYPE() == Config_smart.WEICHAT_FIRST_ITEM) {
                init_weichat_FIRST_ITEM();
            } else if (weiChat_jingsuan.getWEICHAT_ITEM_TYPE() == Config_smart.WEICHAT_SECOND_ITEM) {
                init_weichat_SECOND_ITEM();
            }
            //非空
        }else {
            if(weiChat_jingsuan.getWEICHAT_ITEM_TYPE()==Config_smart.WEICHAT_FIRST_ITEM){
                if(view.getTag()instanceof Viewholder_weichat_first)
                {
                    viewholder_weichat_first =(Viewholder_weichat_first)view.getTag();
                }else {
                    init_weichat_FIRST_ITEM();
                }
            }else if(weiChat_jingsuan.getWEICHAT_ITEM_TYPE()==Config_smart.WEICHAT_SECOND_ITEM){
                if(view.getTag()instanceof Viewholder_weichat_second){
                    viewholder_weichat_second=(Viewholder_weichat_second)view.getTag();
                }else {
                    init_weichat_SECOND_ITEM();
                }
            }

        }
        if(weiChat_jingsuan.getWEICHAT_ITEM_TYPE()==Config_smart.WEICHAT_FIRST_ITEM){
            viewholder_weichat_first.weichar_rollpagerView.setPlayDelay(2000);
            viewholder_weichat_first.weichar_rollpagerView.setAnimationDurtion(500);
            viewholder_weichat_first.weichar_rollpagerView.setAdapter(new weichat_item_rollviewAdapter(getContext(),viewholder_weichat_first.weichar_rollpagerView));
//            viewholder_weichat_first.weichar_rollpagerView.setHintView(new IconHintView(getContext(), R.drawable.card_background, R.drawable.morefunny_card_background));
//            viewholder_weichat_first.weichar_rollpagerView.setHintView(new ColorPointHintView(view.getContext(), Color.parseColor("#4D4D4D"),Color.WHITE));
            viewholder_weichat_first.weichar_rollpagerView.setHintView(new ColorPointHintView(view.getContext(), Color.parseColor("#00000000"),Color.parseColor("#00000000")));
            viewholder_weichat_first.weichar_rollpagerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    switch (position){
                        case 0:
                            Toast.makeText(view.getContext(),"这是Item"+position+",让我们来打开快时光", Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            Toast.makeText(view.getContext(),"fdsfdsdfsfs",Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(view.getContext(),"去你妹的"+position,Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });

            viewholder_weichat_first.weixinjingsuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"微信精选",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_first.lishidejintian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"历史的今天",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_first.xiaohuadaquan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"笑话大全",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_first.jiqiwenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Morefunny_RobotActivity.actionStart(getContext());
                }
            });
            viewholder_weichat_first.nbasaishi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"nba赛事",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_first.zuqiuliansai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"足球联赛",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_first.xuexishengdi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"学习圣地",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_first.gengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"更多",Toast.LENGTH_LONG).show();
                }
            });
        }else if(weiChat_jingsuan.getWEICHAT_ITEM_TYPE()==Config_smart.WEICHAT_SECOND_ITEM){
            viewholder_weichat_second.imageView.setImageResource(R.drawable.n21);
            viewholder_weichat_second.textView.setText(weiChat_jingsuan.getTitle());

            viewholder_weichat_second.tab_02_item_ly_laiyuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"这个是",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_second.tab_02_item_ly_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"内容",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_second.tab_02_item_ly_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"点赞",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_second.tab_02_item_ly_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"分享",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_second.tab_02_item_ly_command.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"评论",Toast.LENGTH_LONG).show();
                }
            });
            viewholder_weichat_second.tab_02_item_ly_config.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"设置这个类型：我不想看这个",Toast.LENGTH_LONG).show();
                }
            });

        }
        return view;
    }

    class Viewholder_weichat_first{
        private LinearLayout weixinjingsuan;
        private LinearLayout lishidejintian;
        private LinearLayout xiaohuadaquan;
        private LinearLayout jiqiwenda;
        private LinearLayout nbasaishi;
        private LinearLayout zuqiuliansai;
        private LinearLayout xuexishengdi;
        private LinearLayout gengduo;
        private RollPagerView weichar_rollpagerView;
    }
    class Viewholder_weichat_second{

        private ImageView imageView;
        private TextView textView;
        private LinearLayout tab_02_item_ly_laiyuan;
        private LinearLayout tab_02_item_ly_content;
        private LinearLayout tab_02_item_ly_like;
        private LinearLayout tab_02_item_ly_share;
        private LinearLayout tab_02_item_ly_command;
        private ImageView tab_02_item_ly_imageaview;
        private TextView tab_02_item_ly_laiyuan_title;
        private TextView tab_02_item_ly_data;
        private TextView tab_02_item_ly_laiyuan_address;
        private ImageView tab_02_item_ly_config;

    }
}
