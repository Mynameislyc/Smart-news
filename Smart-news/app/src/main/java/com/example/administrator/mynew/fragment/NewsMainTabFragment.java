package com.example.administrator.mynew.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.news_bean.NewsObject;
import com.example.administrator.mynew.bean.news_bean.NewsObject_head;
import com.example.administrator.mynew.common.listnener.NewsListener;
import com.example.administrator.mynew.common.adapter.NewslistAdapter;
import com.example.administrator.mynew.httputil.Http_news;
import com.example.administrator.mynew.ui.NewswebActivity;
import com.example.administrator.mynew.util.Config.Config_smart;
import com.example.administrator.mynew.widget.com_lyc_Listview;
import com.example.administrator.mynew.widget.NewsTypebar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class NewsMainTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,NewsListener,AdapterView.OnItemClickListener,View.OnClickListener{


    private SwipeRefreshLayout swipeRefreshLayout;
    private com_lyc_Listview comlycListview;

    private final int INIT_NEWS_LIST_DATA=0;
    private final int LOAD_NEWS_LIST_DATA=1;

    private final int INIT_FIRST_LIST_DATA=2;

    public List<NewsObject>  newsListDatas;
    public NewslistAdapter newslistAdapter;
    private String newsType;
    View view;
    private int loadlinglenth ;
    private  JSONArray newsData;

    public HorizontalScrollView horizontalScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab01,container,false);

        comlycListview =(com_lyc_Listview)view.findViewById(R.id.newsListview);
        newsListDatas = new ArrayList<NewsObject>();
        newslistAdapter=new NewslistAdapter(this.getContext(),R.layout.newlist_item_ly,newsListDatas);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeLayout);
        horizontalScrollView=(HorizontalScrollView)view.findViewById(R.id.scrollview);
        swipeRefreshLayout.setOnRefreshListener(this);
        comlycListview.setNewsListner(this);
        comlycListview.setOnItemClickListener(this);

        newsType="top";
        showNews(newsType);
        NewsTypebar.initTextView(view);
        NewsTypebar.setTextViewClickListener(this);
        NewsTypebar.initTypebarText_Color();
        NewsTypebar.top.setTextColor(Color.parseColor("#01814A"));
        return view;

    }

    private void showNews(String newsType){
        if(newsType=="top"){
            initTop_News(newsType);
        }else {
            initSort_News(newsType);
        }
    }
    //头条新闻
    private void initTop_News(final String newsType) {
        new Thread(){
            @Override
            public void run() {
                Http_news a = new Http_news();
                NewsObject newsObject_FIRST_ITEM=new NewsObject();
                NewsObject newsObject_SECOND_ITEM=new NewsObject();
                newsObject_FIRST_ITEM.setItem_type(Config_smart.NEWSLIST_FIRST_ITEM);
                newsObject_FIRST_ITEM.setType("头条");
                newsObject_SECOND_ITEM.setItem_type(Config_smart.NEWSLIST_SECOND_ITEM);
                newsObject_SECOND_ITEM.setType("头条");
                newsObject_FIRST_ITEM.setNewsObject_head(new NewsObject_head[3]);

                String jsoRes = a.doGet(newsType);
                JSONObject object = null;
                try {
                    object = new JSONObject(jsoRes);
//                    String reason = object.getString("reason");
                    String result = object.getString("result");
                    JSONObject objectdata = new JSONObject(result);
                    newsData = objectdata.getJSONArray("data");

                    for(int i=0;i<newsObject_FIRST_ITEM.getNewsObject_head().length;i++){
                        int x=(int)(Math.random()*newsData.length());
                        JSONObject one = newsData.getJSONObject(x);
                        String title = one.getString("title");
                        String date = one.getString("date");
                        String author_name = one.getString("author_name");
                        String thumbnail_pic_s = one.getString("thumbnail_pic_s");
                        String thumbnail_pic_s02 = one.getString("thumbnail_pic_s02");
                        String thumbnail_pic_s03 = one.getString("thumbnail_pic_s03");
                        String url = one.getString("url");
                        String uniquekey = one.getString("uniquekey");
                        String type = one.getString("type");//头条
                        String realtype = one.getString("realtype");//头条
                        newsObject_FIRST_ITEM.getNewsObject_head()[i]=new NewsObject_head();
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setTitle(title);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setData(date);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setAuthor(author_name);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setThumbnail_pic_s(thumbnail_pic_s);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setThumbnail_pic_so2(thumbnail_pic_s02);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setThumbnail_picso3(thumbnail_pic_s03);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setUrl(url);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setUniquekey(uniquekey);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setType(type);
                        newsObject_FIRST_ITEM.getNewsObject_head()[i].setRealtype(realtype);
//                        Log.d("####","gdsfgds"+title+"_______________________"+newsObject_FIRST_ITEM.getNewsObject_head().length);
                    }
                    newsListDatas.add(newsObject_FIRST_ITEM);
                    newsListDatas.add(newsObject_SECOND_ITEM);

                    loadlinglenth=(int)((newsData.length())/2);
                    for (int i = 0; i < loadlinglenth; i++) {
                        JSONObject one = newsData.getJSONObject(i);
                        NewsObject newsObject = new NewsObject();
                        String title = one.getString("title");
                        String date = one.getString("date");
                        String author_name = one.getString("author_name");
                        String thumbnail_pic_s = one.getString("thumbnail_pic_s");
                        String thumbnail_pic_s02 = one.getString("thumbnail_pic_s02");
                        String thumbnail_pic_s03 = one.getString("thumbnail_pic_s03");
                        String url = one.getString("url");
                        String uniquekey = one.getString("uniquekey");
                        String type = one.getString("type");//头条
                        String realtype = one.getString("realtype");//头条

                        newsObject.setTitle(title);
                        newsObject.setData(date);
                        newsObject.setItem_type(Config_smart.NEWSLIST_NORMAL_ITEM);
                        newsObject.setAuthor(author_name);
                        newsObject.setThumbnail_pic_s(thumbnail_pic_s);
                        newsObject.setThumbnail_pic_so2(thumbnail_pic_s02);
                        newsObject.setThumbnail_picso3(thumbnail_pic_s03);
                        newsObject.setUrl(url);
                        newsObject.setUniquekey(uniquekey);
                        newsObject.setType(type);
                        newsObject.setRealtype(realtype);
                        newsListDatas.add(newsObject);
                    }

                    Message message=new Message();
                    message.what=INIT_NEWS_LIST_DATA;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //非头条新闻
    private void initSort_News(final String newsType) {
        new Thread(){
            @Override
            public void run() {
                Http_news a = new Http_news();
                String jsoRes = a.doGet(newsType);
                JSONObject object = null;
                try {
                    object = new JSONObject(jsoRes);
                    String reason = object.getString("reason");
                    String result = object.getString("result");
                    JSONObject objectdata = new JSONObject(result);
                    newsData = objectdata.getJSONArray("data");
                    loadlinglenth=(int)(newsData.length()/2);
                    for (int i = 0; i < loadlinglenth; i++) {
                        JSONObject one = newsData.getJSONObject(i);
                        NewsObject newsObject = new NewsObject();
                        String title = one.getString("title");
                        String date = one.getString("date");
                        String category= one.getString("category");
                        String author_name = one.getString("author_name");
                        String thumbnail_pic_s = one.getString("thumbnail_pic_s");
                        String thumbnail_pic_s03 = one.getString("thumbnail_pic_s03");
                        String url = one.getString("url");

                        newsObject.setTitle(title);
                        newsObject.setData(date);
                        newsObject.setAuthor(author_name);
                        newsObject.setThumbnail_pic_s(thumbnail_pic_s);
                        newsObject.setCategory(category);
                        newsObject.setThumbnail_picso3(thumbnail_pic_s03);
                        newsObject.setUrl(url);
                        newsListDatas.add(newsObject);
                    }
                    Message message=new Message();
                    message.what=INIT_NEWS_LIST_DATA;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    private void refresh_or_change_News()
    {
        newsListDatas = new ArrayList<NewsObject>();
        newslistAdapter=new NewslistAdapter(this.getContext(),R.layout.newlist_item_ly,newsListDatas);
        showNews(newsType);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Log.d("##aaaa", "现在开始下载更新。。。。。。。。。。。。");
        refresh_or_change_News();
    }

    @Override
    public void LoadNews() {
        if(loadlinglenth==newsData.length())
        {
            Toast.makeText(getContext(),"没有更多显示了！",Toast.LENGTH_SHORT).show();
        }
        Message message=new Message();
        message.what=LOAD_NEWS_LIST_DATA;
        handler.sendMessage(message);

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case INIT_NEWS_LIST_DATA:
                    //初始化新闻数据并显示
                    comlycListview.setAdapter(newslistAdapter);
                    break;
                case LOAD_NEWS_LIST_DATA:
                    //上拉更新
                    if(newsType=="top"){
                        try {
                            //若是头条
                            Thread.sleep(500);
                            if(loadlinglenth<newsData.length()){
                                for(int i=loadlinglenth;i<newsData.length();i++){
                                    try {
                                        String title = null;
                                        JSONObject one = newsData.getJSONObject(i);
                                        NewsObject newsObject = new NewsObject();
                                        title = one.getString("title");
                                        String date = one.getString("date");
                                        String author_name = one.getString("author_name");
                                        String thumbnail_pic_s = one.getString("thumbnail_pic_s");
                                        String thumbnail_pic_s02 = one.getString("thumbnail_pic_s02");
                                        String thumbnail_pic_s03 = one.getString("thumbnail_pic_s03");
                                        String url = one.getString("url");
                                        String uniquekey = one.getString("uniquekey");
                                        String type = one.getString("type");//头条
//                                        Log.d("####","这里是fragment"+type);
                                        String realtype = one.getString("realtype");//头条
                                        newsObject.setTitle(title);
                                        newsObject.setData(date);
                                        newsObject.setAuthor(author_name);
                                        newsObject.setThumbnail_pic_s(thumbnail_pic_s);
                                        newsObject.setThumbnail_pic_so2(thumbnail_pic_s02);
                                        newsObject.setThumbnail_picso3(thumbnail_pic_s03);
                                        newsObject.setUrl(url);
                                        newsObject.setUniquekey(uniquekey);
                                        newsObject.setType(type);
                                        newsObject.setRealtype(realtype);
                                        newsListDatas.add(newsObject);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                loadlinglenth=newsData.length();
                            }else {

                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        //若不是头条
                        try {
                            Thread.sleep(500);
                            if(loadlinglenth<newsData.length()){
                                for(int i=loadlinglenth;i<newsData.length();i++){
                                    try {
                                        JSONObject one = newsData.getJSONObject(i);
                                        NewsObject newsObject = new NewsObject();

                                        String title = one.getString("title");
                                        String date = one.getString("date");
                                        String category= one.getString("category");
                                        String author_name = one.getString("author_name");
                                        String thumbnail_pic_s = one.getString("thumbnail_pic_s");
                                        String thumbnail_pic_s03 = one.getString("thumbnail_pic_s03");
                                        String url = one.getString("url");


                                        newsObject.setTitle(title);
                                        newsObject.setData(date);
                                        newsObject.setAuthor(author_name);
                                        newsObject.setThumbnail_pic_s(thumbnail_pic_s);
                                        newsObject.setCategory(category);
                                        newsObject.setThumbnail_picso3(thumbnail_pic_s03);
                                        newsObject.setUrl(url);

                                        newsListDatas.add(newsObject);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                loadlinglenth=newsData.length();
                            }else {

                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                        newslistAdapter.notifyDataSetChanged();
                        comlycListview.loadComplete();
                    break;
                case INIT_FIRST_LIST_DATA:


                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        NewsObject newsobject=newsListDatas.get(position);
        NewswebActivity.actionStart(getActivity(),newsobject);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.top:
                newsType="top";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.top.setTextColor(Color.parseColor("#01814A"));

                refresh_or_change_News();
                break;
            case R.id.shehui:
                newsType="shehui";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.shehui.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.guonei:
                newsType="guonei";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.guonei.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.guoji:
                newsType="guoji";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.guoji.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.yule:
                newsType="yule";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.yule.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.tiyu:
                newsType="tiyu";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.tiyu.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.junshi:
                newsType="junshi";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.junshi.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.keji:
                newsType="keji";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.keji.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.caijing:
                newsType="caijing";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.caijing.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;
            case R.id.shishang:
                newsType="shishang";
                NewsTypebar.initTypebarText_Color();
                NewsTypebar.shishang.setTextColor(Color.parseColor("#01814A"));
                refresh_or_change_News();
                break;

        }
    }
}
