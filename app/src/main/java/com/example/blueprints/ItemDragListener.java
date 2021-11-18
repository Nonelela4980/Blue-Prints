package com.example.blueprints;

import android.content.ClipDescription;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ItemDragListener implements View.OnDragListener{
    /**Will be used to make the cards to respond to different drag evems*/
    RelativeLayout.LayoutParams layoutParams;
    @Override
    public boolean onDrag(View view, DragEvent event) {
        Log.i("drag","onDrag method");
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED: layoutParams=(RelativeLayout.LayoutParams) view.getLayoutParams();
            break;
            case DragEvent.ACTION_DRAG_ENTERED:
                int x__cord=(int)event.getX();
                int y_cord=(int)event.getY();
                Log.i("drag","drag and drop started");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.i("drag","drag and drop exited");
                 x__cord=(int)event.getX();
                 y_cord=(int)event.getY();
                 layoutParams.leftMargin=x__cord;
                 layoutParams.topMargin=y_cord;
                 view.setLayoutParams(layoutParams);
                 break;
                 case DragEvent.ACTION_DRAG_LOCATION:
                     x__cord=(int)event.getX();
                     y_cord=(int)event.getY();
                     break;
            case DragEvent.ACTION_DRAG_ENDED:
                System.out.println("hello world");
                break;
            case DragEvent.ACTION_DROP:
                System.out.println("hello dsfworld");
                break;
            default:break;
        }
        return true;
    }
}
