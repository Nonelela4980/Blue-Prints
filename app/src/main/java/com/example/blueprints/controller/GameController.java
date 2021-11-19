package com.example.blueprints.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.blueprints.ImageCard;
import com.example.blueprints.R;
import com.example.blueprints.gamelogic.Player;
import com.example.blueprints.playActivity;

/**Controls the behaviour of the game*/
public class GameController {
   private final Context context;
   private final playActivity activity;
   private ImageCard curDeck;
   private ImageCard curGridCard;
   private Drawable curShade;
  static  GameController GAME_CONTROLLER = null;
   Neigbours  neigbours;
  public GameController(Context context, playActivity activity,Neigbours neigbours){
      this.context = context;
      this.activity = activity;
      this.neigbours=neigbours;

  }

  public void Show_theActionButtons(ImageView undo, ImageView confirmBtn)
  {
      undo.setVisibility(View.VISIBLE);
      confirmBtn.setVisibility(View.VISIBLE);
  }

    public ImageCard getCurDeck() {
        return curDeck;
    }

    public void setCurDeck(ImageCard curDeck) {
        this.curDeck = curDeck;
    }

    public ImageCard getCurGridCard() {
        return curGridCard;
    }

    public void setCurGridCard(ImageCard curGridCard) {
        this.curGridCard = curGridCard;
    }

    public Context getContext() {
        return context;
    }

    public void setCurShade(Drawable curShade) {
        this.curShade = curShade;
    }

    public void lockNeigbours(){
        int[][] temp=neigbours.getNeigbours();

            int top=temp[0][curGridCard.getDy()];
            int bottom=temp[2][curGridCard.getDy()];

        //Log.i("bound","dx is"+curGridCard.getDx());

            ImageCard topCard=(ImageCard) ((playActivity)context).findViewById(top);
            ImageCard bottomCard=(ImageCard) ((playActivity)context).findViewById(bottom);
            //lock the drag listeners
            topCard.setOnDragListener(null);
            bottomCard.setOnDragListener(null);

    }

    public void resetNeighbours(){
        int[][] temp=neigbours.getNeigbours();

        //String a=
        Log.i("bound","dx is"+curGridCard.getDx());

        int top=temp[0][curGridCard.getDy()];
        int bottom=temp[2][curGridCard.getDy()];

        ImageCard topCard=(ImageCard) ((playActivity)context).findViewById(top);
        ImageCard bottomCard=(ImageCard) ((playActivity)context).findViewById(bottom);
        topCard.setOnDragListener(new ItemDragListener(this));
        bottomCard.setOnDragListener(new ItemDragListener(this));
    }

    public playActivity getActivity() {
        return activity;
    }
    public void undo(ImageView undo_actionBtn, ImageView confirm_actionBtn){

        curDeck.setImageDrawable(curGridCard.getDrawable());
        curGridCard.setImageDrawable(null);
        curGridCard.setImageDrawable(curShade);

        curGridCard.setOnDragListener(new ItemDragListener(this)); /**must now take cards if the card is removed*/

        if(curGridCard.getDx()==1) //put back the drag listeners
            resetNeighbours();

        undo_actionBtn.setVisibility(View.INVISIBLE);
        confirm_actionBtn.setVisibility(View.INVISIBLE);
    }
    public void confirm(ImageView undo_actionBtn, ImageView confirm_actionBtn ){

      if(curGridCard.getDx()==1)
          lockNeigbours();

        undo_actionBtn.setVisibility(View.INVISIBLE);
        confirm_actionBtn.setVisibility(View.INVISIBLE);
    }
}
