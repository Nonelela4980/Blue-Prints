package com.example.blueprints.controller;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.blueprints.ImageCard;

public class TrashBinListener implements View.OnDragListener {
    GameController gameController;

    public TrashBinListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        ImageCard cardDragged=(ImageCard)dragEvent.getLocalState();

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                if(!cardDragged.isDeckCard()){
                    gameController.opeBin();
                }
                break;
            case DragEvent.ACTION_DRAG_ENTERED:

                break;
            case DragEvent.ACTION_DRAG_EXITED:

                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                gameController.binInitialState();
                break;
            case DragEvent.ACTION_DROP:
                if(!cardDragged.isDeckCard()){
                    gameController.removeCardFromPlayerHand(dragEvent);
                    cardDragged.setHas_card(false);
                }
                break;
            default:
                break;
        }
        return true;
    }
}

