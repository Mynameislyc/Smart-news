package com.example.administrator.mynew.common.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.smartnewscontent.robot.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class ChatMessageAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<ChatMessage>mDatas;

    private int BasePosition;

    private static long nowtimie=0;
    private static long lasttime=0;

    public ChatMessageAdapter(){};
    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas){
        mInflater=LayoutInflater.from(context);
        this.mDatas=mDatas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage mchatmessage;
        mchatmessage=mDatas.get(position);
//        Log.d("####","here is coming+++"+ mchatmessage.getMsg());
        if(mchatmessage.getType()== ChatMessage.Type.INCOMING){
            return 0;
        }

        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage=mDatas.get(position);
        if(mDatas.size()>=3) {
            lasttime = mDatas.get(position - 1).getData().getTime() / 1000;
            nowtimie = mDatas.get(position).getData().getTime() / 1000;
        }
        BasePosition=position;
        ViewHolder viewHolder=null;
        if(convertView==null){
            //通过itemType设置不同布局
            if(getItemViewType(position)==0){
                convertView=mInflater.inflate(R.layout.item_from_megs,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mDate=(TextView)convertView.findViewById(R.id.id_from_msg_date);
                viewHolder.mMsg=(TextView)convertView.findViewById(R.id.id_from_msg_info);
                viewHolder.nName=(TextView)convertView.findViewById(R.id.id_com_name);

            }else {
                convertView=mInflater.inflate(R.layout.item_send_megs,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mDate=(TextView)convertView.findViewById(R.id.id_from_to_date);
                viewHolder.mMsg=(TextView)convertView.findViewById(R.id.id_to_msg_info);
                viewHolder.nName=(TextView)convertView.findViewById(R.id.id_send_name);
            }
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        //设置数据
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.mDate.setText(dateFormat.format(chatMessage.getData()));
        viewHolder.mMsg.setText(chatMessage.getMsg());
        viewHolder.nName.setText(chatMessage.getName());
        return convertView;
    }
    /////////////////////////////
    public static void updateView(View viewHolder) {

        if(viewHolder == null) {
            return;
        }
        ViewHolder holder = (ViewHolder) viewHolder.getTag();
        long sss=nowtimie-lasttime;
        if(sss<10){
            holder.mDate.setVisibility(View.GONE);
        }else {
            holder.mDate.setVisibility(View.VISIBLE);
        }
        Log.d("####","_________________showTime"+nowtimie);


    }

    private final class ViewHolder{
        TextView mDate;
        TextView mMsg;
        TextView nName;
    }
}
