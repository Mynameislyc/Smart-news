package com.example.administrator.mynew.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mynew.R;
import com.example.administrator.mynew.bean.smartnewscontent.robot.ChatMessage;
import com.example.administrator.mynew.common.adapter.ChatMessageAdapter;
import com.example.administrator.mynew.httputil.HttpUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Morefunny_RobotActivity extends Activity {

    private  ListView mMsgs;
    private ChatMessageAdapter mAdapter;
    private  List<ChatMessage> mDatas;
    private EditText mInputMsg;
    private Button mSendMsgs;

    private String getinput;
    public static long activityNewTime;
    public static long activityLastTime;
    public static long isHideTime;

    public static SpeechSynthesizer mTts;
    private String sayHello="hey，我叫拾掇";

    public static void actionStart(Context context ){
        Intent intent=new Intent(context,Morefunny_RobotActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morefunny__robot);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=581807dd");

        initView();//初始人控件
        initListViewListener();//给listview添加控件
        initData();//初始化列表
        initListener();//初始化机器人
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        mTts= SpeechSynthesizer.createSynthesizer(this, null);
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "vixx"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "100");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置） ，保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //3.开始合成
        mTts.startSpeaking(sayHello, hSynListener);
    }
    private void initListViewListener() {
        mMsgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //隐藏系统键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });
    }
    //合成监听器
    public static SynthesizerListener hSynListener = new SynthesizerListener(){
        //开始播放
        @Override
        public void onSpeakBegin() {

        }
        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息
        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }
        //暂停播放
        @Override
        public void onSpeakPaused() {

        }
        //恢复播放回调接口
        @Override
        public void onSpeakResumed() {

        }
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置
        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }
        //会话结束回调接口，没有错误时，error为null
        @Override
        public void onCompleted(SpeechError speechError) {

        }
        //会话事件回调接口
        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };


    private void initListener() {
        mSendMsgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initComintdata();
//                isHideTime =mDatas.get(mMsgs.getChildCount()).getData().getTime()/1000-mDatas.get(mMsgs.getChildCount()-1).getData().getTime()/1000;
            }
        });
    }
//    public static long getListTime(){
//
//        return  mDatas.get(mMsgs.getChildCount()).getData().getTime()/1000;
//    }
    private void initComintdata(){
        ChatMessage chatMessage = new ChatMessage();
        getinput=mInputMsg.getText().toString();
        if (getinput.length() != 0) {
            chatMessage.setMsg(getinput);
            chatMessage.setType(ChatMessage.Type.OUTCOMING);
            chatMessage.setData(new Date());
            chatMessage.setName("Me");
            mDatas.add(chatMessage);
            mAdapter.notifyDataSetChanged();
            ChatMessageAdapter.updateView(mMsgs.getChildAt(mMsgs.getChildCount()));


//            Log.d("####", "这是上一次的time" + mDatas.get(mMsgs.getChildCount() - 1).getData().getTime() / 1000);
//            Log.d("####", "最新的time" + mDatas.get(mMsgs.getChildCount()).getData().getTime()/1000);
//            Log.d("####", "this is a time____"+isHideTime);

            mInputMsg.setText("");
        }else {
            Toast.makeText(Morefunny_RobotActivity.this, "发送的内容不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                try {
                    ChatMessage fromChatMessage= HttpUtils.sendMessage(getinput);

                    mDatas.add(fromChatMessage);
                    Thread.sleep(500);
                    Message message=new Message();
                    message.what=0;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    private void initData() {
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("拾掇",sayHello, ChatMessage.Type.INCOMING,new Date()));
        mAdapter=new ChatMessageAdapter(this,mDatas);
        mMsgs.setAdapter(mAdapter);
    }

    private void initView() {
        mMsgs=(ListView)findViewById(R.id.id_listview_msgs);
        mInputMsg=(EditText)findViewById(R.id.id_input_msg);
        mSendMsgs=(Button)findViewById(R.id.id_send_msg);

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    mAdapter.notifyDataSetChanged();
                    ChatMessageAdapter.updateView(mMsgs.getChildAt(mMsgs.getChildCount() - 1));
                    mMsgs.setSelectionFromTop(mMsgs.getCount(), 0);//显示最底端最新内容
                    break;
                default:
                    Toast.makeText(Morefunny_RobotActivity.this,"系统出错",Toast.LENGTH_SHORT).show();
            }
        }
    };

    //判断是否可以上网
    private boolean isNetworkAvailable() {
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
