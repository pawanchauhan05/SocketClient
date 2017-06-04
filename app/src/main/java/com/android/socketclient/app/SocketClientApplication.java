package com.android.socketclient.app;

import android.app.Activity;
import android.app.Application;

/**
 * Created by pawansingh on 03/06/17.
 */

public class SocketClientApplication extends Application {
    private Activity activity;
    private Client client;
    private static SocketClientApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static SocketClientApplication getInstance() {
        return instance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
