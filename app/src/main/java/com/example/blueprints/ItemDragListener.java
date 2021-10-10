package com.example.blueprints;

import android.content.ClipDescription;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ItemDragListener implements View.OnDragListener{
    /**Will be used to make the cards to respond to different drag evems*/
    @Override
    public boolean onDrag(View view, DragEvent event) {

        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED: 
        }
        return true;
    }
}
