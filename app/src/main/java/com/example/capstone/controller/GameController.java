package com.example.capstone.controller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.capstone.BluePrintClient;
import com.example.capstone.messages.client.CardDropped;
import com.example.capstone.messages.server.UpdateGameBoard;
import com.example.capstone.models.cards.Assignment;
import com.example.capstone.models.cards.BankGive;
import com.example.capstone.models.cards.BluePrints;
import com.example.capstone.models.cards.IFCard;
import com.example.capstone.models.cards.InstructionGive;
import com.example.capstone.models.cards.LoopCard;
import com.example.capstone.models.resources.Brick;
import com.example.capstone.models.resources.Straw;
import com.example.capstone.models.resources.Wood;
import com.example.capstone.views.ImageCard;
import com.example.capstone.R;
import com.example.capstone.views.playActivity;

import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<Assignment> assignmentCards = new ArrayList<>();
    public ArrayList<ImageCard> playerHand = new ArrayList<>();
    private ImageView bin;
    private ImageView bin_lid;
    float binInitial_rotate = 0;
    float lidInitial_rotate = 0;
    private Neigbours neigbours;
    ImageCard[] board;

    public GameController(Context context, playActivity activity, Neigbours neigbours) {
        this.context = context;
        this.activity = activity;
        this.neigbours = neigbours;
        bin = (ImageView) ((playActivity) context).findViewById(R.id.bin);
        bin_lid = (ImageView) ((playActivity) context).findViewById(R.id.bin_lid);
        AssignmentCards();
        initializeDeckCards();
        initializePlayerHandCards();

    }

    public void Show_theActionButtons(ImageView undo, ImageView confirmBtn) {
        undo.setVisibility(View.VISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
    }

    //Randomly selects cards from the assignment cards list
    public void setUpPlayerHand() {

        playerHand.forEach(imageCard -> {
            int indx = (int) Math.floor(Math.random() * ((assignmentCards.size() - 1) - 0 + 1) + 0);
            imageCard.setAssignment(assignmentCards.get(indx));
        });
    }

    //Creates an instance of the cardImage
    public void AssignmentCards() {
        assignmentCards.add(new BankGive(270, new Brick(1)));
        assignmentCards.add(new BankGive(270, new Straw(1)));
        assignmentCards.add(new BankGive(270, new Wood(1)));

        assignmentCards.add(new BankGive(270, new Brick(2)));
        assignmentCards.add(new BankGive(270, new Straw(2)));
        assignmentCards.add(new BankGive(270, new Wood(2)));

        assignmentCards.add(new BankGive(270, new Brick(3)));
        assignmentCards.add(new BankGive(270, new Straw(3)));
        assignmentCards.add(new BankGive(270, new Wood(3)));

        assignmentCards.add(new BluePrints(1, 270, new Brick(2)));
        assignmentCards.add(new BluePrints(1, 270, new Straw(2)));
        assignmentCards.add(new BluePrints(1, 270, new Wood(2)));

        assignmentCards.add(new BluePrints(1, 270, new Brick(3)));
        assignmentCards.add(new BluePrints(1, 270, new Straw(3)));
        assignmentCards.add(new BluePrints(1, 270, new Wood(3)));

        //Start region: Instruction cards
        assignmentCards.add(new InstructionGive(90, new Brick(1))); //Instruction_give_i_type  card where i=1
        assignmentCards.add(new InstructionGive(90, new Straw(1)));
        assignmentCards.add(new InstructionGive(90, new Wood(1)));

        assignmentCards.add(new InstructionGive(90, new Brick(2)));//Instruction_give_i_type  card where i=2
        assignmentCards.add(new InstructionGive(90, new Straw(2)));
        assignmentCards.add(new InstructionGive(90, new Wood(2)));

        assignmentCards.add(new InstructionGive(90, new Brick(3)));//Instruction_give_i_type  card where i=3
        assignmentCards.add(new InstructionGive(90, new Straw(3)));
        assignmentCards.add(new InstructionGive(90, new Wood(3)));

        assignmentCards.add(new InstructionGive(180, new Brick(1)));//Instruction_give_left_i_type  card where i=1
        assignmentCards.add(new InstructionGive(180, new Straw(1)));
        assignmentCards.add(new InstructionGive(180, new Wood(1)));

        assignmentCards.add(new InstructionGive(180, new Brick(2)));//Instruction_give_left_i_type  card where i=2 type=brick,straw or wood
        assignmentCards.add(new InstructionGive(180, new Straw(2)));
        assignmentCards.add(new InstructionGive(180, new Wood(2)));

        assignmentCards.add(new InstructionGive(180, new Brick(3)));//Instruction_give_left_i_type  card where i=3 type=brick,straw or wood
        assignmentCards.add(new InstructionGive(180, new Straw(3)));
        assignmentCards.add(new InstructionGive(180, new Wood(3)));

        assignmentCards.add(new InstructionGive(360, new Brick(1)));//Instruction_give_right_i_type  card where i=1 type=brick,straw or wood
        assignmentCards.add(new InstructionGive(360, new Straw(1)));
        assignmentCards.add(new InstructionGive(360, new Wood(1)));

        assignmentCards.add(new InstructionGive(360, new Brick(2)));//Instruction_give_right_i_type  card where i=2 type=brick,straw or wood
        assignmentCards.add(new InstructionGive(360, new Straw(2)));
        assignmentCards.add(new InstructionGive(360, new Wood(2)));

        assignmentCards.add(new InstructionGive(360, new Brick(3)));//Instruction_give_right_i_type  card where i=3 type=brick,straw or wood
        assignmentCards.add(new InstructionGive(360, new Straw(3)));
        assignmentCards.add(new InstructionGive(360, new Wood(3)));
        //End region: Instruction cards

        //Start region: While cards
        assignmentCards.add(new IFCard(270, new Brick(1)));//Instruction_has_i_type  card where i=1,type=brick,straw or wood
        assignmentCards.add(new IFCard(270, new Straw(1)));
        assignmentCards.add(new IFCard(270, new Wood(1)));

        assignmentCards.add(new IFCard(270, new Brick(2)));//Instruction_has_i_type  card where i=2,type=brick,straw or wood
        assignmentCards.add(new IFCard(270, new Straw(2)));
        assignmentCards.add(new IFCard(270, new Wood(2)));

        assignmentCards.add(new IFCard(270, new Brick(3)));//Instruction_has_i_type  card where i=3,type=brick,straw or wood
        assignmentCards.add(new IFCard(270, new Straw(3)));
        assignmentCards.add(new IFCard(270, new Wood(3)));
        //End region: While cards

        //Start region: Loop cards
        assignmentCards.add(new LoopCard(270, new Brick(1))); //Instruction_loop_i_type  card where i=1,type=brick,straw or wood
        assignmentCards.add(new LoopCard(270, new Straw(1)));
        assignmentCards.add(new LoopCard(270, new Wood(1)));

        assignmentCards.add(new LoopCard(270, new Brick(2))); //Instruction_loop_i_type  card where i=2,type=brick,straw or wood
        assignmentCards.add(new LoopCard(270, new Straw(2)));
        assignmentCards.add(new LoopCard(270, new Wood(2)));

        assignmentCards.add(new LoopCard(270, new Brick(3))); //Instruction_loop_i_type  card where i=3,type=brick,straw or wood
        assignmentCards.add(new LoopCard(270, new Straw(3)));
        assignmentCards.add(new LoopCard(270, new Wood(3)));
        //End region: While cards
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

        } else if (curGridCard.getDx() == 0 || curGridCard.getDx() == 2) {
            //we lock the centre
            int cardId = temp[1][curGridCard.getDy()];
            ImageCard centreCard = (ImageCard) ((playActivity) context).findViewById(cardId);
            centreCard.setOnDragListener(null);
        }
        curGridCard.setHas_card(true);
    }

    public void resetNeighbours() {
        int[][] temp = neigbours.getNeigbours();
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
        } else if (curGridCard.getDx() == 0 || curGridCard.getDx() == 2) {
            /**we lock the centre*/
            int cardId = temp[1][curGridCard.getDy()];
            ImageCard centreCard = (ImageCard) ((playActivity) context).findViewById(cardId);
            centreCard.setOnDragListener(new ItemDragListener(this));
        }
        curGridCard.setHas_card(false);
    }

    public playActivity getActivity() {
        return activity;
    }

    /**
     * Player chooses not to confirm or drags another card from the hand
     */
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

    /**
     * @param undo_actionBtn,confirm_actionBtn are the undo and confirm actions when a player drops a card on the machine
     *                                         Player chooses to confirm the move
     */
    public void confirm(ImageView undo_actionBtn, ImageView confirm_actionBtn) {
        lockNeigbours();
        undo_actionBtn.setVisibility(View.INVISIBLE);
        confirm_actionBtn.setVisibility(View.INVISIBLE);
        BluePrintClient.sendMessage(new CardDropped(ItemDragListener.cardId, ItemDragListener.card_drawable));
    }

    /**
     * When a player drags a card from the player hand the bin opens only removes the card if dropped on it
     */
    public void opeBin() {
        binInitial_rotate = bin.getRotation();
        lidInitial_rotate = bin_lid.getRotation();
        float angle_lid = bin_lid.getRotation() + 32f;
        float angle_bin = bin.getRotation() + 10f;
        bin_lid.animate().rotation(angle_lid).setInterpolator(new AccelerateDecelerateInterpolator());
        bin.animate().rotation(angle_bin).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    /**
     * Returns the bin to the initial state(close bin)
     */
    public void binInitialState() {
        float angle_lid = bin_lid.getRotation() - 32f;
        float angle_bin = bin.getRotation() - 10f;
        bin_lid.animate().rotation(binInitial_rotate).setInterpolator(new AccelerateDecelerateInterpolator());
        bin.animate().rotation(lidInitial_rotate).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void removeCardFromPlayerHand(DragEvent dragEvent) {
        ImageCard cardDragged = (ImageCard) dragEvent.getLocalState();
        Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
        cardDragged.setImageDrawable(transparentDrawable);
    }

    private void initializeDeckCards() {
        ImageCard unseen = (ImageCard) ((playActivity) context).findViewById(R.id.unseen_card);
        ImageCard deckCard1 = (ImageCard) ((playActivity) context).findViewById(R.id.deck_card1);
        ImageCard deckCard2 = (ImageCard) ((playActivity) context).findViewById(R.id.deck_card2);
        ImageCard deckCard3 = (ImageCard) ((playActivity) context).findViewById(R.id.deck_card3);

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

    private void initializePlayerHandCards() {
        ImageCard card1 = (ImageCard) ((playActivity) context).findViewById(R.id.player_deck1);
        ImageCard card2 = (ImageCard) ((playActivity) context).findViewById(R.id.player_deck2);
        ImageCard card3 = (ImageCard) ((playActivity) context).findViewById(R.id.player_deck3);
        ImageCard card4 = (ImageCard) ((playActivity) context).findViewById(R.id.player_deck4);
        ImageCard card5 = (ImageCard) ((playActivity) context).findViewById(R.id.player_deck5);

//        card1.setOnDragListener(new PlayerHandDragListener(this));
//        card2.setOnDragListener(new PlayerHandDragListener(this));
//        card3.setOnDragListener(new PlayerHandDragListener(this));
//        card4.setOnDragListener(new PlayerHandDragListener(this));
//        card5.setOnDragListener(new PlayerHandDragListener(this));
        card1.setOnDragListener(new TopDeckCardListener(this));
        card2.setOnDragListener(new TopDeckCardListener(this));
        card3.setOnDragListener(new TopDeckCardListener(this));
        card4.setOnDragListener(new TopDeckCardListener(this));
        card5.setOnDragListener(new TopDeckCardListener(this));

        card1.setHas_card(true);
        card2.setHas_card(true);
        card3.setHas_card(true);
        card4.setHas_card(true);
        card5.setHas_card(true);
    }

    public Drawable getInitialDrawable() {
        return ((playActivity) context).getCardInitialDrawable();
    }
}
