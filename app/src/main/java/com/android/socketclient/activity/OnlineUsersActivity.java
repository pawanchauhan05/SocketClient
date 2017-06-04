package com.android.socketclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.android.socketclient.R;
import com.android.socketclient.Utils.Utils;
import com.android.socketclient.adapters.OnlineUserAdapter;
import com.android.socketclient.app.SocketClientApplication;
import com.android.socketclient.interfaces.NetworkStateInterface;

import java.util.ArrayList;
import java.util.List;

public class OnlineUsersActivity extends AppCompatActivity implements NetworkStateInterface {
    private RecyclerView recyclerView;
    private List<String> onlineUserList = new ArrayList<>();
    private OnlineUserAdapter adapter;
    private FrameLayout frameLayout;

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
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutInternet);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Utils.clearPreferences(this);
                if(SocketClientApplication.getInstance().getClient() != null && SocketClientApplication.getInstance().getClient().getSocket() != null && SocketClientApplication.getInstance().getClient().getSocket().isConnected()) {
                    SocketClientApplication.getInstance().getClient().endConnection();
                }
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void toggleNetworkError() {
        frameLayout.setVisibility(SocketClientApplication.getInstance().isConnected() ? View.GONE : View.VISIBLE);
    }
}
