package com.android.socketclient.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.socketclient.R;
import com.android.socketclient.activity.ChatActivity;
import com.android.socketclient.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawansingh on 03/06/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatMsgViewHolder> {
    private List<ChatMessage> chatMsgList = new ArrayList<>();
    private ChatActivity chatActivity;

    public ChatAdapter(List<ChatMessage> chatMsgList, ChatActivity chatActivity) {
        this.chatMsgList = chatMsgList;
        this.chatActivity = chatActivity;
    }

    public List<ChatMessage> getChatMsgList() {
        return chatMsgList;
    }

    public void setChatMsgList(List<ChatMessage> chatMsgList) {
        this.chatMsgList = chatMsgList;
    }

    @Override
    public ChatMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item_layout, parent, false);
        return new ChatMsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatMsgViewHolder holder, int position) {
        ChatMessage chatMessage = chatMsgList.get(position);
        if(chatMessage.isRight()) {
            holder.textViewLeft.setText(chatMessage.getMsg());
            holder.textViewRight.setVisibility(View.GONE);
        } else {
            holder.textViewRight.setText(chatMessage.getMsg());
            holder.textViewLeft.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatMsgList.size();
    }
}

class ChatMsgViewHolder extends RecyclerView.ViewHolder {
    TextView textViewLeft, textViewRight;
    public ChatMsgViewHolder(View itemView) {
        super(itemView);
        textViewLeft = (TextView) itemView.findViewById(R.id.textViewLeft);
        textViewRight = (TextView) itemView.findViewById(R.id.textViewRight);
    }
}
