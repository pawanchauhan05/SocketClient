package com.android.socketclient.models;

/**
 * Created by pawansingh on 03/06/17.
 */

public class ChatMessage {
    private String msg;
    private String user;
    private boolean isRight;

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public ChatMessage(String msg, String user, boolean isRight) {
        this.msg = msg;
        this.user = user;
        this.isRight = isRight;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "msg='" + msg + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
