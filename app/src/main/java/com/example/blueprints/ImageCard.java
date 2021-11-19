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



    int dx,dy;
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
