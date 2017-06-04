package com.android.socketclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.socketclient.R;
import com.android.socketclient.app.Client;
import com.android.socketclient.app.SocketClientApplication;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText editTextUserName;
    private Button buttonConnect;

    @Override
    protected void onResume() {
        super.onResume();
        SocketClientApplication.getInstance().setActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonConnect:
                Client.getInstance(editTextUserName.getText().toString().trim());
                startActivity(new Intent(this, OnlineUsersActivity.class));
                break;
        }
    }
}
