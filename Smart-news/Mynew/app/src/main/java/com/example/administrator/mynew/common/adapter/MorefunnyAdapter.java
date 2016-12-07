package com.example.administrator.mynew.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.MorefunnyObject;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MorefunnyAdapter extends ArrayAdapter<MorefunnyObject> {
    private int resourceID;
    public MorefunnyAdapter(Context context, int resource, List<MorefunnyObject> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MorefunnyObject morefunnyObject=getItem(position);
        View view=convertView;
        if(convertView==null)
        {
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
        }else {
            view=convertView;
        }
        TextView morefunnytitle=(TextView)view.findViewById(R.id.morefunny_title);
        TextView morefunnycontent=(TextView)view.findViewById(R.id.morefunny_content);
        ImageView imageView=(ImageView)view.findViewById(R.id.morefunny_image);
        morefunnytitle.setText(morefunnyObject.getMorefunnytitle());
        morefunnycontent.setText(morefunnyObject.getMorefunnycontent());
        imageView.setImageResource(morefunnyObject.getImageID());
        return view;
    }
}
