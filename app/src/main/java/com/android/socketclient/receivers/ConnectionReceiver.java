package com.android.socketclient.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.socketclient.app.SocketClientApplication;
import com.android.socketclient.interfaces.NetworkStateInterface;

public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean connected = activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
        SocketClientApplication.getInstance().setConnected(connected);
        final Activity activity = SocketClientApplication.getInstance().getActivity();
        if(activity != null)
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((NetworkStateInterface)activity).toggleNetworkError();
                }
            });
    }
}
