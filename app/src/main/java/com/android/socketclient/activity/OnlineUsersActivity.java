package com.android.socketclient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.socketclient.R;
import com.android.socketclient.adapters.OnlineUserAdapter;
import com.android.socketclient.app.SocketClientApplication;

import java.util.ArrayList;
import java.util.List;

public class OnlineUsersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> onlineUserList = new ArrayList<>();
    private OnlineUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_users);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new OnlineUserAdapter(onlineUserList, this);
        recyclerView.setAdapter(adapter);
    }

    public void updateOnlineUsers(List<String> userList) {
        onlineUserList.clear();
        adapter.setOnlineUserList(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SocketClientApplication.getInstance().setActivity(this);
    }
}
