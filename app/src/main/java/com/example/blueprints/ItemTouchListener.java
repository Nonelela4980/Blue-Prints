package com.example.blueprints;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

public class ItemTouchListener implements View.OnTouchListener {

    private float startingPointerX;
    private float startingPointerY;
    private float startingViewX;
    private float startingViewY;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
//            ClipData data=ClipData.newPlainText("","");
//            View.DragShadowBuilder shadowBuilder=new View.DragShadowBuilder(view);
//            view.startDragAndDrop(data,shadowBuilder,view,0);
//            view.setVisibility(View.INVISIBLE);
//            return true;
//        }
//        else
//            return false;
        switch (motionEvent.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                startingViewX=view.getX();
                startingViewY=view.getY();

                startingPointerX=motionEvent.getX();
                startingPointerY=motionEvent.getY();

                float dx=startingPointerX;
                float dy=startingPointerY;

                float viewX=startingViewX+dx;
                float viewY=startingViewY+dy;

                view.setX(viewX);
                view.setY(viewY);
                ClipData data=ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder=new View.DragShadowBuilder(view);
            view.startDragAndDrop(data,shadowBuilder,view,0);
            view.setVisibility(View.INVISIBLE);
            break;
            default:return false;
            //case MotionEvent.ACTION_HOVER_ENTER
        }
        return true;
    }
}
