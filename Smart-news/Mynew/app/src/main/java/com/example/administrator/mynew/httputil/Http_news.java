package com.example.administrator.mynew.httputil;

import com.example.administrator.mynew.util.Config.API_CONFIG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Http_news {
    private static final String URL= API_CONFIG.NEWS_TOUTIAO;
    private static final String API_KEY=API_CONFIG.NEWS_TOUTIAOAPI_KEY;

    public  String doGet(String newtype){
        String result="";
        BufferedReader reader=null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl=getParams(URL,newtype);
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getParams(String msg,String newtype) {
        String url = null;
            url = msg+"?type="+newtype+"&key="+API_KEY;
        return url;
    }
}
