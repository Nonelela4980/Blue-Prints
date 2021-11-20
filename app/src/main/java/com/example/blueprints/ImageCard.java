package com.example.blueprints;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImageCard extends androidx.appcompat.widget.AppCompatImageView implements View.OnClickListener {


    boolean has_card=false;
    boolean isDeckCard=false;
    int dx,dy;

    public boolean isDeckCard() {
        return isDeckCard;
    }

    public void setDeckCard(boolean deckCard) {
        isDeckCard = deckCard;
    }

    public boolean isHas_card() {
        return has_card;
    }

    public void setHas_card(boolean has_card) {
        this.has_card = has_card;
    }

    public ImageCard(@NonNull Context context) {
        super(context);
    }

    public ImageCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    
    public ImageCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void rotateCard(){
        float degreesRotation = this.getRotation() + 90F;
        this.animate().rotation(degreesRotation).setInterpolator(new AccelerateDecelerateInterpolator());
        Log.i("CardClick","Hello I'm rotating");
    }

    /**When a card is clicked it must be rotated, so it calls the rotate*/
    @Override
    public void onClick(View v) {
        Log.i("CardClick","Hello I'm clicked");
        //if it doesn't have a card it the board ImageCard should not rotate

        if(!has_card){
            Log.i("has card","No card detected");
            return;
        }
        Log.i("has card","Card detected");
        this.rotateCard();
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
