package com.example.administrator.mynew.httputil;

import com.example.administrator.mynew.bean.smartnewscontent.robot.ChatMessage;
import com.example.administrator.mynew.bean.smartnewscontent.robot.Result;
import com.example.administrator.mynew.ui.Morefunny_RobotActivity;
import com.example.administrator.mynew.util.Config.API_CONFIG;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;


/**
 * Created by Administrator on 2016/10/24.
 */
public class HttpUtils {
    private static final String URL = API_CONFIG.SMARTNEWSCONTENT_TOLLINGROBOT;
    private static final String API_KEY = API_CONFIG.SMARTNEWSCONTENT_TOLLINGROBOTAPI_KEY;
    public static String doGet(String msg) {
        String result = "";
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        String url = getParams(msg);//得到完整地址
        try {
            java.net.URL urlNet = new URL(url);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlNet.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            is = conn.getInputStream();
            int len = -1;
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();

            result = new String(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return result;
    }

    private static String getParams(String msg) {
        String url = null;
        try {
            url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 发送一个消息，得到么回消看息。
     *
     * @param msg
     * @return
     */
    public static ChatMessage sendMessage(String msg) {


        ChatMessage chatMessage = new ChatMessage();
        String jsonRes = doGet(msg);
        Gson gson = new Gson();
        Result result = null;
        try {
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg(result.getText());
            Morefunny_RobotActivity.mTts.startSpeaking(result.getText(), Morefunny_RobotActivity.hSynListener);
        } catch (JsonSyntaxException e) {
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setData(new Date());
        chatMessage.setName("拾掇");
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }
}
