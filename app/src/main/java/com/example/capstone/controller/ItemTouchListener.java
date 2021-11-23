package com.example.capstone.controller;
import android.content.ClipData;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ItemTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("check touch","touch started");
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            ClipData data=ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder=new View.DragShadowBuilder(view);
            view.startDragAndDrop(data,shadowBuilder,view,0);
            //view.setVisibility(View.INVISIBLE);
            return true;
        }
        else
            return false;
    }
}
