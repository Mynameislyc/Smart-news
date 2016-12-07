package com.example.administrator.mynew.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.smartnewscontent.WeiChat_jingsuan;
import com.example.administrator.mynew.common.adapter.Friend_weichatAdapter;
import com.example.administrator.mynew.common.listnener.NewsListener;
import com.example.administrator.mynew.util.Config.Config_smart;
import com.example.administrator.mynew.widget.com_lyc_Listview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class FreindMainTabFragment extends Fragment implements NewsListener{
    private View view;
    private List<WeiChat_jingsuan> listdatas;
    private Friend_weichatAdapter weichatAdapter;
    private com_lyc_Listview weichatListview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.tab02,container,false);
        WeiChat_jingsuan weiChat_jingsuan_First=new WeiChat_jingsuan();
        WeiChat_jingsuan weiChat_jingsuan_Second1=new WeiChat_jingsuan();
        WeiChat_jingsuan weiChat_jingsuan_Second2=new WeiChat_jingsuan();
        weiChat_jingsuan_First.setWEICHAT_ITEM_TYPE(Config_smart.WEICHAT_FIRST_ITEM);
        weiChat_jingsuan_Second1.setWEICHAT_ITEM_TYPE(Config_smart.WEICHAT_SECOND_ITEM);
        weiChat_jingsuan_Second2.setWEICHAT_ITEM_TYPE(Config_smart.WEICHAT_SECOND_ITEM);
        weiChat_jingsuan_Second1.setTitle("国外女孩所经历的与众不同的生活");
        weiChat_jingsuan_Second2.setTitle("女生独自一人闯野外战场");

        weichatListview=(com_lyc_Listview)view.findViewById(R.id.friend_weichat);
        listdatas=new ArrayList<>();
        listdatas.add(weiChat_jingsuan_First);

        listdatas.add(weiChat_jingsuan_Second1);
        listdatas.add(weiChat_jingsuan_Second2);

        weichatAdapter=new Friend_weichatAdapter(getContext(),R.layout.tab02_layout_first,listdatas);
        weichatListview.setAdapter(weichatAdapter);
        weichatListview.setNewsListner(this
        );
        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void LoadNews() {
        weichatListview.loadComplete();
    }
}
