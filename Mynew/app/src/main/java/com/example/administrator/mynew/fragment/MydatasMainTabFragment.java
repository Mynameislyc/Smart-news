package com.example.administrator.mynew.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.MorefunnyObject;
import com.example.administrator.mynew.common.adapter.MorefunnyAdapter;
import com.example.administrator.mynew.ui.Morefunny_RobotActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class MydatasMainTabFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private List<MorefunnyObject> morefunnyList;
    private MorefunnyAdapter morefunnyAdapter;

    private final int TULING_ROBOT=1;
    private final int FUNNY_LAUGH=2;
    private final int SETTING=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab03, container, false);
        listView=(ListView)view.findViewById(R.id.morefunnylistview);
        MorefunnyObject morefunnyObject_tulingRobot=new MorefunnyObject(TULING_ROBOT,"好玩的拾掇Robot","拾掇是一名舌嘴伶俐的智能机器人，上知天文下知地理，有什么事就仅管问它吧",R.drawable.morefunny_tulingrobot);
        MorefunnyObject morefunnyObject_laugh=new MorefunnyObject(FUNNY_LAUGH,"好玩好看的笑话大全","有事没事，来这里看一看，包你捧腹大笑",R.drawable.morefunny_laugh);
        MorefunnyObject morefunnyObject_setting=new MorefunnyObject(SETTING,"点击我可以设置","关于作者...",R.drawable.setting);
        morefunnyList=new ArrayList<MorefunnyObject>();
        morefunnyList.add(morefunnyObject_tulingRobot);
        morefunnyList.add(morefunnyObject_laugh);
        morefunnyList.add(morefunnyObject_setting);
        morefunnyAdapter=new MorefunnyAdapter(getContext(),R.layout.morefunny_item,morefunnyList);
        listView.setAdapter(morefunnyAdapter);
        listView.setOnItemClickListener(this);
        ImageView imageViewbackground=(ImageView)view.findViewById(R.id.morefunny_backgroud);
        imageViewbackground.setImageResource(R.drawable.morefunnybackground);
        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MorefunnyObject morefunnyObject=morefunnyList.get(i);
        switch (morefunnyObject.getTYPE()){
            case TULING_ROBOT:
                Morefunny_RobotActivity.actionStart(getContext());
                break;
            case FUNNY_LAUGH:
                Toast.makeText(getContext(),"这是laugh！",Toast.LENGTH_SHORT).show();

                break;
            case SETTING:
                Toast.makeText(getContext(),"这是setting！",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getContext(),"出错了！",Toast.LENGTH_SHORT).show();
        }
    }
}
