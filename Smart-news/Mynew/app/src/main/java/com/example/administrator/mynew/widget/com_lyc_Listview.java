package com.example.administrator.mynew.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.common.listnener.NewsListener;

/**
 * Created by Administrator on 2016/10/28.
 */
public class com_lyc_Listview extends ListView implements AbsListView.OnScrollListener {

    int totalItemCount;//总Item数量
    int lastVisibleItem;//最后一个可见的item
    boolean isLoading;//正在加载

    NewsListener newsListener;
    View footer;
    private Context context;
    public com_lyc_Listview(Context context) {
        super(context);
        initView(context);
        this.context=context;
    }


    public com_lyc_Listview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        this.context=context;
    }

    public com_lyc_Listview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        this.context=context;
    }

    private void initView(Context context) {
        LayoutInflater inflater=LayoutInflater.from(context);
        footer=inflater.inflate(R.layout.footer_layout,null);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        isLoading=false;
        this.addFooterView(footer);
        this.setOnScrollListener(this);

    }
    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {

        if(totalItemCount==lastVisibleItem&&scrollState==SCROLL_STATE_IDLE)
        {
            if(isLoading==false)
            footer.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
//            Toast.makeText(context,"不能再往上拉了",Toast.LENGTH_SHORT).show();
            newsListener.LoadNews();

        }

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }
    public void setNewsListner(NewsListener newsListener)
    {
        this.newsListener=newsListener;
    }
    /*
加载完毕
 */
    public void loadComplete()
    {
        isLoading=false;
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
    }
}
