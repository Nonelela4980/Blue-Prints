package com.example.capstone.views;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.capstone.BluePrintClient;
import com.example.capstone.Message;
import com.example.capstone.R;
import com.example.capstone.controller.GameLobby;

public class connect_activity extends AppCompatActivity {
    TextView name_textField, ip_addressTextField;
    GameLobby gameLobby;
    ListView gameLobbyPlayers;
    BluePrintClient bluePrintClient;
    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_connect_activity);

        name_textField = findViewById(R.id.name_textField);
        ip_addressTextField = findViewById(R.id.ipAddress_textField);
        gameLobby=new GameLobby(connect_activity.this);

        gameLobbyPlayers=findViewById(R.id.lobby_players);
        startBtn=findViewById(R.id.startBtn);
        boolean hasPermission =
                checkSelfPermission(Manifest.permission.INTERNET) ==
                        PackageManager.PERMISSION_GRANTED;
        if(hasPermission)
        Log.i("HasPermission","permissions granted");
        else
            Log.i("HasPermission","permissions not granted");
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBtnClick();
            }
        });
    }
    public void startBtnClick() {
        bluePrintClient=new BluePrintClient(message -> runOnUiThread(()->addMessage(message)));
        bluePrintClient.connectToServer(ip_addressTextField.getText().toString());
        runOnUiThread(()->gameLobby.startMatchMaking());
    }

    public void backBtn_Login(View view)
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void addMessage(Message message){
            gameLobby.addMessage(message);
    }
}