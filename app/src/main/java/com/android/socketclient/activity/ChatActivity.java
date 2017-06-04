package com.android.socketclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.socketclient.R;
import com.android.socketclient.adapters.ChatAdapter;
import com.android.socketclient.app.SocketClientApplication;
import com.android.socketclient.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewChatTitle;
    private RecyclerView recyclerView;
    private EditText editTextMsg;
    private Button buttonSend;
    private ChatAdapter adapter;
    private List<ChatMessage> chatMsgList = new ArrayList<>();
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        textViewChatTitle = (TextView) findViewById(R.id.textViewChatTitle);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editTextMsg = (EditText) findViewById(R.id.editTextMsg);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);

        Intent intent = getIntent();
        if(intent != null) {
            name  = intent.getStringExtra("NAME");
            textViewChatTitle.setText("Chat with " + name);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ChatAdapter(chatMsgList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSend:
                adapter.getChatMsgList().add(new ChatMessage(editTextMsg.getText().toString().trim(), name, false));
                adapter.notifyItemInserted(adapter.getChatMsgList().size()-1);
                SocketClientApplication.getInstance().getClient().sendMessage(new ChatMessage(editTextMsg.getText().toString(), name, true));
                editTextMsg.getText().clear();
                break;
        }
    }

    public void updateChatMsg(ChatMessage chatMessage) {
        adapter.getChatMsgList().add(chatMessage);
        adapter.notifyItemInserted(adapter.getChatMsgList().size()-1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SocketClientApplication.getInstance().setActivity(this);
    }
}
