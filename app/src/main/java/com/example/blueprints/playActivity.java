package com.example.blueprints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class playActivity extends AppCompatActivity  {

    ItemDragListener dragListener;
    ImageCard start;
//            ,grid0,grid1,grid2,grid3,grid4;
//    ImageCard grid5,grid6,grid7,grid8,grid9;
//    ImageCard grid10,grid11,grid12,grid13,grid14;
    ImageCard[] gridBoard;
    ImageCard[] playerDeckCards;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_play);
        gridBoard=initializeGridBoard();
        playerDeckCards=initializePlayerDeckCards();
    }

    //Initialize the ImageCards for the board
    private ImageCard[] initializeGridBoard(){

        ImageCard[] imageCards=new ImageCard[15];
        start=findViewById(R.id.start);
        start.setOnDragListener(new ItemDragListener());
        imageCards[0]=start;
        for (int i = 0; i < imageCards.length; i++) {
            String id = "grid" + i;
            imageCards[i] = (findViewById(getResources().getIdentifier(id, "id", getPackageName())));
            imageCards[i].setOnDragListener(new ItemDragListener());
        }
      return imageCards;
    }
    //Initialize the ImageCards for the player deck
    private ImageCard[] initializePlayerDeckCards()
    {
       ImageCard[] player_cards=new ImageCard[5];

        for (int i = 0; i <player_cards.length; i++)
        {
            int k=i+1;
            String id = "player_deck" + k;
            player_cards[i] = (findViewById(getResources().getIdentifier(id, "id", getPackageName())));
            player_cards[i].setOnTouchListener(new ItemTouchListener());
        }
        return player_cards;
    }


}