package com.example.blueprints;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.blueprints.controller.GameController;
import com.example.blueprints.controller.ItemDragListener;
import com.example.blueprints.controller.ItemTouchListener;
import com.example.blueprints.controller.Neigbours;
import com.example.blueprints.controller.PlayerHandDragListener;
import com.example.blueprints.controller.ShowConfirmMove;
import com.example.blueprints.controller.TrashBinListener;

public class playActivity extends AppCompatActivity  implements Neigbours {

    ItemDragListener dragListener;
    ImageCard start;
    ImageView undo_actionBtn, confirm_actionBtn,bin,bin_lid;

    //grid0,grid1,grid2,grid3,grid4;
//    ImageCard grid5,grid6,grid7,grid8,grid9;
//    ImageCard grid10,grid11,grid12,grid13,grid14;
    ImageCard[] gridBoard;
    ImageCard[] playerDeckCards;
    int[] player_deckCards; //keeps the id of the player deck ImageCardViews;
    GameController gameController;
    int[][] neighbours = new int[3][5]; //keeps grid item id's

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_play);


        gameController = new GameController(this, this,this);
        gridBoard = initializeGridBoard();
        playerDeckCards = initializePlayerDeckCards();
        undo_actionBtn = findViewById(R.id.undo_actionBtn);
        confirm_actionBtn = findViewById(R.id.cofirm_actionBtn);
        bin= findViewById(R.id.bin);
        bin_lid= findViewById(R.id.bin_lid);

        bin.setOnDragListener(new TrashBinListener(gameController));
        bin_lid.setOnDragListener(new TrashBinListener(gameController));
    }

    //Initialize the ImageCards for the board
    private ImageCard[] initializeGridBoard() {

        ImageCard[] imageCards = new ImageCard[15];
        start = findViewById(R.id.start);

        start.setOnDragListener(new ItemDragListener(gameController));
        imageCards[0] = start;
        int counter = 0;
        int j=0;
        for (int i = 0; i < imageCards.length; i++) {
            String id = "grid" + i;
            imageCards[i] = (findViewById(getResources().getIdentifier(id, "id", getPackageName())));
            imageCards[i].setOnDragListener(new ItemDragListener(gameController));

            if(i==5)
                Log.i("check5","Dx of 5 is "+imageCards[i].getDx());

            if(j==neighbours[0].length)
                j=0;

            if (counter < 5) {
                neighbours[0][j] = (imageCards[i]).getId(); //refers to first row of the machine
                imageCards[i].setDx(0);
                imageCards[i].setDy(j);
            } else if (counter >= 5 && counter <= 9) {
               neighbours[1][j] = (imageCards[i]).getId();//refers to second row of the machine
                imageCards[i].setDx(1);
                imageCards[i].setDy(j);
            } else {
                neighbours[2][j] = (imageCards[i]).getId();//refers to third row of the machine
                imageCards[i].setDx(2);
                imageCards[i].setDy(j);
            }
            j++;
            counter++;
        }
        return imageCards;
    }

    //Initialize the ImageCards for the player deck
    private ImageCard[] initializePlayerDeckCards() {
        ImageCard[] player_cards = new ImageCard[5];
        for (int i = 0; i < player_cards.length; i++) {
            int k = i + 1;
            String id = "player_deck" + k;
            player_cards[i] = (findViewById(getResources().getIdentifier(id, "id", getPackageName())));
            player_cards[i].setOnTouchListener(new ItemTouchListener());

        }
        return player_cards;
    }


    public void undo(View view) {
        gameController.undo(undo_actionBtn, confirm_actionBtn);
    }


    public void confirm(View view) {

        gameController.confirm(undo_actionBtn, confirm_actionBtn);

    }

    @Override
    public int[][] getNeigbours() {
        return neighbours;
    }
}