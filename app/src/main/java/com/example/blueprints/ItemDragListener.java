package com.example.blueprints;

import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ItemDragListener implements View.OnDragListener{


    @Override
    public boolean onDrag(View view, DragEvent event) {
        Log.i("check Drag","drag started");
        ImageView givingImage = (ImageView) event.getLocalState();
        ImageView receivingImage = (ImageView)view;
        switch (event.getAction()) {
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
                Drawable image = givingImage.getDrawable();
                receivingImage.setImageDrawable(image);
                Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
                givingImage.setImageDrawable(transparentDrawable);

                break;
            default:break;
        }
        return true;
    }
}
