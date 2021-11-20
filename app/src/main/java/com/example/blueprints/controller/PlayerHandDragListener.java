package com.example.blueprints.controller;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.blueprints.ImageCard;

public class PlayerHandDragListener implements View.OnDragListener {
    GameController gameController;

    public PlayerHandDragListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        ImageCard givingImage = (ImageCard) dragEvent.getLocalState();
        ImageCard receivingImage = (ImageCard) view;

        Log.i("Nonelela", "yhooo");

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                Log.i("Nonelela2", "yhooo2");
                if (!receivingImage.isHas_card()) {
                    Log.i("Nonelela", "I'm IN Bra");
                    Drawable image = givingImage.getDrawable();
                    receivingImage.setImageDrawable(image);
                    //receivingImage.setOnClickListener(v->receivingImage.rotateCard());
                    Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
                    givingImage.setImageDrawable(transparentDrawable);
                    receivingImage.setHas_card(true); //now it has a card
                    givingImage.setHas_card(false);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }
}
