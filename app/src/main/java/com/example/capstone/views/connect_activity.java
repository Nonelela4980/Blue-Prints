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
import android.widget.Toast;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_connect_activity);

        name_textField = findViewById(R.id.name_textField);
        ip_addressTextField = findViewById(R.id.ipAddress_textField);
        gameLobby = new GameLobby(connect_activity.this);
        gameLobbyPlayers = findViewById(R.id.lobby_players);
        startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBtnClick();
            }
        });
    }

    //connects to the server, if connection was successful player is taken to the game lobby, and it waits for other players to connect
    public void startBtnClick() {
        bluePrintClient = new BluePrintClient(message -> runOnUiThread(() -> addMessage(message)), () -> runOnUiThread(this::startPlaying));
        bluePrintClient.connectToServer(ip_addressTextField.getText().toString(),name_textField.getText().toString());
//        if (bluePrintClient.isConnected) {
            runOnUiThread(() -> gameLobby.startMatchMaking()); //starts the lobby
//        } else {
//            Toast.makeText(getApplicationContext(), "Failed to connect", Toast.LENGTH_LONG);
//        }
    }

    public void backBtn_Login(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //Adds messages on the listView in the game lobby
    public void addMessage(Message message) {
        gameLobby.addMessage(message);
    }

    public void startPlaying() {
        gameLobby.stopMatchMaking(); //stops the game lobby to take the player to the play screen
        Intent intent = new Intent(getApplicationContext(), playActivity.class);
        startActivity(intent);
    }
}