package com.example.blueprints;

import android.view.DragEvent;
import android.view.View;

public class CardListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        return false;
    }
}
