package com.android.socketclient.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.socketclient.R;
import com.android.socketclient.activity.ChatActivity;
import com.android.socketclient.activity.OnlineUsersActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawansingh on 03/06/17.
 */

public class OnlineUserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<String> onlineUserList = new ArrayList<>();
    private OnlineUsersActivity onlineUsersActivity;

    public OnlineUserAdapter(List<String> onlineUserList, OnlineUsersActivity onlineUsersActivity) {
        this.onlineUserList = onlineUserList;
        this.onlineUsersActivity = onlineUsersActivity;
    }

    public List<String> getOnlineUserList() {
        return onlineUserList;
    }

    public void setOnlineUserList(List<String> onlineUserList) {
        this.onlineUserList = onlineUserList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.textViewUser.setText(onlineUserList.get(position));
        holder.textViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onlineUsersActivity, ChatActivity.class);
                intent.putExtra("NAME", onlineUserList.get(position));
                onlineUsersActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return onlineUserList.size();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder {
    TextView textViewUser;
    public UserViewHolder(View itemView) {
        super(itemView);
        textViewUser = (TextView) itemView.findViewById(R.id.textViewUser);
    }
}
