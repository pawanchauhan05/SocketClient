package com.android.socketclient.app;

import android.app.Activity;
import android.os.AsyncTask;

import com.android.socketclient.activity.ChatActivity;
import com.android.socketclient.activity.OnlineUsersActivity;
import com.android.socketclient.models.ChatMessage;
import com.android.socketclient.models.Users;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.android.socketclient.Utils.Constants.SERVER;

/**
 * Created by pawansingh on 03/06/17.
 */

public class Client {
    public static final String TAG = Client.class.getName();
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;


    private String userName;
    private static Client instance;

    private Client(String userName) {
        this.userName = userName;
    }

    public static Client getInstance(String userName) {
        if(instance == null) {
            instance = new Client(userName);
            if(instance != null)
                instance.createConnection();
        } else {
            if(!instance.socket.isConnected())
                instance.createConnection();
        }
        SocketClientApplication.getInstance().setClient(instance);
        return instance;
    }

    public void createConnection() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(SERVER, 9999);
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    printWriter = new PrintWriter(socket.getOutputStream(),true);
                    printWriter.println(userName);
                    while (true) {
                        final String serverString = bufferedReader.readLine();
                        final Activity currentActivity = SocketClientApplication.getInstance().getActivity();
                        if(currentActivity != null) {
                            currentActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final Users users = new Gson().fromJson(serverString, Users.class);
                                    if (currentActivity instanceof OnlineUsersActivity) {
                                        ((OnlineUsersActivity) currentActivity).updateOnlineUsers(users.getUsers());
                                    } else if(currentActivity instanceof ChatActivity) {
                                        ((ChatActivity) currentActivity).updateChatMsg(users.getChatMessage());
                                    }
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        AsyncTask.execute(runnable);
    }

    public void endConnection() {
        printWriter.println("end");
    }

    public void sendMessage(ChatMessage chatMessage) {
        printWriter.println(new Gson().toJson(chatMessage));
    }


}
