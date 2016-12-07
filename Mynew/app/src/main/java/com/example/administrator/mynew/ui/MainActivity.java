package com.example.administrator.mynew.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.fragment.FreindMainTabFragment;
import com.example.administrator.mynew.fragment.MydatasMainTabFragment;
import com.example.administrator.mynew.fragment.NewsMainTabFragment;
import com.jauker.widget.BadgeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mviewPager;
    private FragmentPagerAdapter mfragmentPagerAdapter;
    private List<Fragment> mDates;

    private TextView mNewsTextView;
    private TextView mFriendTextView;
    private TextView mMydataTextView;

    private BadgeView mbadgeView;
    private LinearLayout mNewsLinerLayout;
    private LinearLayout mFriendLinerLayout;
    private LinearLayout mMydataLinerLayout;

    private ImageView mTabline;
    private int mScreen1_3;
    private int mCurrentPageIndex;
    NewsMainTabFragment tab01 ;
    FreindMainTabFragment tab02 ;
    MydatasMainTabFragment tab03 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        initTablint();
        initView();//初始化各种View和fragment
        setonclistener_inlayout();


    }

    private void setonclistener_inlayout() {
        mNewsLinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewPager.setCurrentItem(0);
//                EventBus.getDefault().post(arrayAdapter);

            }
        });
        mFriendLinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewPager.setCurrentItem(1);
            }
        });
        mMydataLinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewPager.setCurrentItem(2);
            }
        });
    }

    private void initTablint() {
        mTabline = (ImageView) findViewById(R.id.head_line);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);//outMetrics携带了整个屏幕的宽度和高度
        mScreen1_3 = outMetrics.widthPixels / 3;//mScreen1_3取整个屏幕宽度的三分之一

        ViewGroup.LayoutParams Ip = mTabline.getLayoutParams();
        Ip.width = mScreen1_3;
        mTabline.setLayoutParams(Ip);
    }

    private void initView() {
        mviewPager = (ViewPager) findViewById(R.id.id_viewpaget);
        mNewsTextView = (TextView) findViewById(R.id.mNewsTextView);
        mFriendTextView = (TextView) findViewById(R.id.mFriendTextView);
        mMydataTextView = (TextView) findViewById(R.id.mMydataTextView);
        mDates = new ArrayList<Fragment>();

        tab01 = new NewsMainTabFragment();
        tab02 = new FreindMainTabFragment();
        tab03 = new MydatasMainTabFragment();

//        tab01.newsListview.setNewsListner(MainActivity.this);


        mDates.add(tab01);
        mDates.add(tab02);
        mDates.add(tab03);


        mNewsLinerLayout = (LinearLayout) findViewById(R.id.id_ll_chat_wapper);
        mFriendLinerLayout = (LinearLayout) findViewById(R.id.id_ll_friends_wapper);
        mMydataLinerLayout = (LinearLayout) findViewById(R.id.id_ll_contact_wapper);

        mfragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDates.get(position);

            }

            @Override
            public int getCount() {
                return mDates.size();
            }
        };
        mviewPager.setAdapter(mfragmentPagerAdapter);

        mviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams ip = (LinearLayout.LayoutParams) mTabline.getLayoutParams();
//                Log.d("####", position + "___" + positionOffset + "____" + positionOffsetPixels);
                if (mCurrentPageIndex == 0 && position == 0) {//0->1
                    ip.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 0) {//1->0

//                    Log.d("####", position + "####################");
                    ip.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1) * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 1) {//1->2
                    ip.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + positionOffset * mScreen1_3);
                } else if (mCurrentPageIndex == 2 && position == 1) {
                    ip.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1) * mScreen1_3);
                }
                mTabline.setLayoutParams(ip);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextveiw();
                switch (position) {
                    case 0:
                        if (mbadgeView != null) {
                            mNewsLinerLayout.removeView(mbadgeView);
                        }
                        mbadgeView = new BadgeView(MainActivity.this);
                        mbadgeView.setBadgeCount(7);
                        mNewsLinerLayout.addView(mbadgeView);

                        mNewsTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        mFriendTextView.setTextColor(Color.parseColor("#008000"));
//                        mFriendTextView.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        mMydataTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                }
//                Log.d("####", position + "fdsfdsfdsfd");
                mCurrentPageIndex = position;


                Log.d("##aaaa", "onPageSelected:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetTextveiw() {
        mNewsTextView.setTextColor(Color.BLACK);
        mFriendTextView.setTextColor(Color.BLACK);
        mMydataTextView.setTextColor(Color.BLACK);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvent(ArrayAdapter arrayAdapter){

//        Log.d("####", "[onEvent]My Thread is############################################# ");
    }

}
