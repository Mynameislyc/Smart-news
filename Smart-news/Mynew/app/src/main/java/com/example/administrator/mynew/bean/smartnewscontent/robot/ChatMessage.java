package com.example.administrator.mynew.bean.smartnewscontent.robot;


import java.util.Date;

/**
 * Created by Administrator on 2016/10/24.
 */
public class ChatMessage {
    private String name;//名称
    private String msg;//发送内容
    private Type type;//发送类型还是接收类型
    private Date data;
    public enum Type{
        INCOMING,OUTCOMING
    }
    public ChatMessage(){

    }

    public ChatMessage(String name, String msg, Type type, Date date){
        super();
        this.name=name;
        this.msg=msg;
        this.type=type;
        this.data=date;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }
}
