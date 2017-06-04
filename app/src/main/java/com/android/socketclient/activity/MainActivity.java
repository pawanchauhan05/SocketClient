package com.android.socketclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.android.socketclient.R;
import com.android.socketclient.Utils.Constants;
import com.android.socketclient.Utils.Utils;
import com.android.socketclient.app.Client;
import com.android.socketclient.app.SocketClientApplication;
import com.android.socketclient.interfaces.NetworkStateInterface;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, NetworkStateInterface {
    private EditText editTextUserName;
    private Button buttonConnect;
    private FrameLayout frameLayout;

    @Override
    protected void onResume() {
        super.onResume();
        SocketClientApplication.getInstance().setActivity(this);
        if(!Utils.readPreferenceData(this, Constants.USER_NAME, "").equals("")) {
            startActivity(new Intent(this, OnlineUsersActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(this);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutInternet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonConnect:
                if(editTextUserName.getText().toString().trim().length() == 0) {
                    editTextUserName.setError("Please enter name");
                } else {
                    Client.getInstance(editTextUserName.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void toggleNetworkError() {
        frameLayout.setVisibility(SocketClientApplication.getInstance().isConnected() ? View.GONE : View.VISIBLE);
    }
}
