package com.example.capstone.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.capstone.Message;
import com.example.capstone.R;

import java.util.ArrayList;

public class GameLobby {
   private Activity activity;
   private AlertDialog alertDialog;
   private ArrayAdapter<Message> adapter;
   private  AlertDialog.Builder builder;
   private ListView messages;
    public GameLobby(Activity activity) {
        this.activity = activity;
        adapter = new ArrayAdapter<Message>(activity.getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<Message>());
    }

    public void startMatchMaking(){
        builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.game_lobby,null);
        //builder.setView(inflater.inflate(R.layout.game_lobby,null));
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog=builder.create();
        alertDialog.show();
        messages=view.findViewById(R.id.lobby_players);
        messages.setAdapter(adapter);
        //((connect_activity)activity.getApplicationContext()).bluePrintClient.connectToServer();
    }
    public void stopMatchMaking(){
        alertDialog.dismiss();
    }

    public void addMessage(Message message)
    {
        adapter.add(message);
    }
    public int count()
    {
        return adapter.getCount();
    }
}
