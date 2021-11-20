package com.example.blueprints.controller;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.blueprints.ImageCard;

public class TopDeckCardListener implements View.OnDragListener {
    GameController gameController;

    public TopDeckCardListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        ImageCard givingImage = (ImageCard) dragEvent.getLocalState();
        ImageCard receivingImage = (ImageCard)view;
        int[] views=new int[2];

        Log.i("DragBug1","dropped ended1");
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:

                break;
            case DragEvent.ACTION_DRAG_EXITED:

                break;
            case DragEvent.ACTION_DRAG_LOCATION:

                break;
            case DragEvent.ACTION_DRAG_ENDED:

                break;
            case DragEvent.ACTION_DROP:
                Log.i("DragBug2","dropped ended2");
                break;
            default:break;
        }
        return true;
    }
}
