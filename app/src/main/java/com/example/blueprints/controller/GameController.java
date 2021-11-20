package com.example.blueprints.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.blueprints.ImageCard;
import com.example.blueprints.R;
import com.example.blueprints.gamelogic.Player;
import com.example.blueprints.playActivity;

/**
 * Controls the behaviour of the game
 */
public class GameController {
    private final Context context;
    private final playActivity activity;
    private ImageCard curDeck;
    private ImageCard curGridCard;
    private Drawable curShade;
    static GameController GAME_CONTROLLER = null;
    private ImageView bin;
    private ImageView bin_lid;
    float binInitial_rotate=0;
    float lidInitial_rotate=0;
    private Neigbours neigbours;


    public GameController(Context context, playActivity activity, Neigbours neigbours) {
        this.context = context;
        this.activity = activity;
        this.neigbours = neigbours;
        bin=(ImageView)((playActivity)context).findViewById(R.id.bin);
        bin_lid=(ImageView)((playActivity)context).findViewById(R.id.bin_lid);
        initializeDeckCards();
        initializePlayerHandCards();
    }

    public void Show_theActionButtons(ImageView undo, ImageView confirmBtn) {
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

    public void lockNeigbours() {
        int[][] temp = neigbours.getNeigbours();

        /**If card was drag to the centre*/
        if (curGridCard.getDx() == 1) {
            int top = temp[0][curGridCard.getDy()];
            int bottom = temp[2][curGridCard.getDy()];

            //Log.i("bound","dx is"+curGridCard.getDx());
            ImageCard topCard = (ImageCard) ((playActivity) context).findViewById(top);
            ImageCard bottomCard = (ImageCard) ((playActivity) context).findViewById(bottom);
            //lock the drag listeners
            topCard.setOnDragListener(null);
            bottomCard.setOnDragListener(null);

        } else if(curGridCard.getDx() == 0 || curGridCard.getDx()==2)
        {
            //we lock the centre
            int cardId=temp[1][curGridCard.getDy()];
            ImageCard centreCard= (ImageCard) ((playActivity) context).findViewById(cardId);
            centreCard.setOnDragListener(null);
        }
        curGridCard.setHas_card(true);
    }

    public void resetNeighbours() {
        int[][] temp = neigbours.getNeigbours();
//hey
        //String a=
        Log.i("bound", "dx is" + curGridCard.getDx());

        /**unlock centre*/
        if (curGridCard.getDx() == 1) {
            int top = temp[0][curGridCard.getDy()];
            int bottom = temp[2][curGridCard.getDy()];

            //Log.i("bound","dx is"+curGridCard.getDx());

            ImageCard topCard = (ImageCard) ((playActivity) context).findViewById(top);
            ImageCard bottomCard = (ImageCard) ((playActivity) context).findViewById(bottom);
            //lock the drag listeners
            topCard.setOnDragListener(new ItemDragListener(this));
            bottomCard.setOnDragListener(new ItemDragListener(this));
        } else if(curGridCard.getDx() == 0 || curGridCard.getDx()==2)
        {
            /**we lock the centre*/
            int cardId=temp[1][curGridCard.getDy()];
            ImageCard centreCard= (ImageCard) ((playActivity) context).findViewById(cardId);
            centreCard.setOnDragListener(new ItemDragListener(this));
        }
        curGridCard.setHas_card(false);
    }

    public playActivity getActivity() {
        return activity;
    }

    public void undo(ImageView undo_actionBtn, ImageView confirm_actionBtn) {

        curDeck.setImageDrawable(curGridCard.getDrawable());
        curGridCard.setImageDrawable(null);
        curGridCard.setImageDrawable(curShade);

        //curGridCard.setOnDragListener(new ItemDragListener(this)); /**must now take cards if the card is removed*/
         //put back the drag listeners
        resetNeighbours();
        undo_actionBtn.setVisibility(View.INVISIBLE);
        confirm_actionBtn.setVisibility(View.INVISIBLE);

    }

    public void confirm(ImageView undo_actionBtn, ImageView confirm_actionBtn) {

        lockNeigbours();
        undo_actionBtn.setVisibility(View.INVISIBLE);
        confirm_actionBtn.setVisibility(View.INVISIBLE);
    }

    public void opeBin(){
        binInitial_rotate=bin.getRotation();
        lidInitial_rotate=bin_lid.getRotation();
        float angle_lid=bin_lid.getRotation()+32f;
        float angle_bin=bin.getRotation()+10f;
        bin_lid.animate().rotation(angle_lid).setInterpolator(new AccelerateDecelerateInterpolator());
        bin.animate().rotation(angle_bin).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void binInitialState(){
        float angle_lid=bin_lid.getRotation()-32f;
        float angle_bin=bin.getRotation()-10f;
        bin_lid.animate().rotation(binInitial_rotate).setInterpolator(new AccelerateDecelerateInterpolator());
        bin.animate().rotation(lidInitial_rotate).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void removeCardFromPlayerHand(DragEvent dragEvent){
        ImageCard cardDragged=(ImageCard)dragEvent.getLocalState();
        Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
       cardDragged.setImageDrawable(transparentDrawable);
    }

    private void initializeDeckCards(){
        ImageCard unseen=(ImageCard)((playActivity)context).findViewById(R.id.unseen_card);
        ImageCard deckCard1=(ImageCard)((playActivity)context).findViewById(R.id.deck_card1);
        ImageCard deckCard2=(ImageCard)((playActivity)context).findViewById(R.id.deck_card2);
        ImageCard deckCard3=(ImageCard)((playActivity)context).findViewById(R.id.deck_card3);

        unseen.setOnTouchListener(new ItemTouchListener());
        deckCard1.setOnTouchListener(new ItemTouchListener());
        deckCard2.setOnTouchListener(new ItemTouchListener());
        deckCard3.setOnTouchListener(new ItemTouchListener());

//        unseen.setOnDragListener(new TopDeckCardListener(this));
//        deckCard1.setOnDragListener(new TopDeckCardListener(this));
//        deckCard2.setOnDragListener(new TopDeckCardListener(this));
//        deckCard3.setOnDragListener(new TopDeckCardListener(this));

        unseen.setDeckCard(true);
        deckCard1.setDeckCard(true);
        deckCard2.setDeckCard(true);
        deckCard3.setDeckCard(true);
    }

    private void initializePlayerHandCards(){
        ImageCard card1=(ImageCard)((playActivity)context).findViewById(R.id.player_deck1);
        ImageCard card2=(ImageCard)((playActivity)context).findViewById(R.id.player_deck2);
        ImageCard card3=(ImageCard)((playActivity)context).findViewById(R.id.player_deck3);
        ImageCard card4=(ImageCard)((playActivity)context).findViewById(R.id.player_deck4);
        ImageCard card5=(ImageCard)((playActivity)context).findViewById(R.id.player_deck5);

        card1.setOnDragListener(new PlayerHandDragListener(this));
        card2.setOnDragListener(new PlayerHandDragListener(this));
        card3.setOnDragListener(new PlayerHandDragListener(this));
        card4.setOnDragListener(new PlayerHandDragListener(this));
        card5.setOnDragListener(new PlayerHandDragListener(this));

        card1.setHas_card(true);
        card2.setHas_card(true);
        card3.setHas_card(true);
        card4.setHas_card(true);
        card5.setHas_card(true);
    }
}
