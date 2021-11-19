package com.example.blueprints.controller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;

public class ItemShadowBuilder extends View.DragShadowBuilder {
    private static Drawable Shadow;
    private int width, height; //width and height of the shadow
    /**creates shadows while dragging*/

    public ItemShadowBuilder(View dragView){
        super(dragView);
        Shadow=new ColorDrawable(Color.DKGRAY); //I want the shadow to be grey when dragging
    }
    @Override
    public void onProvideShadowMetrics (Point size, Point touch) {
        /**The width and hight of the shadow are set to be half of the View's original height*/
        width = getView().getWidth() ;

        height = getView().getHeight();

        Shadow.setBounds(0, 0, width, height);
        size.set(width, height);
        //the touched point is the middle point of the shadow
        touch.set(width / 2, height / 2);
    }
    @Override
    public void onDrawShadow(Canvas canvas) {
        /**Draws the shadow on the canvas provided by the system*/
        Shadow.draw(canvas);
    }
}


