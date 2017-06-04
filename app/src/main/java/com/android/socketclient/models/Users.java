package com.android.socketclient.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawansingh on 03/06/17.
 */

public class Users {
    private ChatMessage chatMessage;
    private List<String> users = new ArrayList<>();

    public Users(List<String> users) {
        this.users = users;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
