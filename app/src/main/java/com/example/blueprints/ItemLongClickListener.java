package com.example.blueprints;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class ItemLongClickListener implements View.OnLongClickListener{

    @Override
    public boolean onLongClick(View view) {
        Log.i("drag","drag and drop pressed");
        ClipData.Item item = new ClipData.Item((Intent) view.getTag());
        ClipData dragData = new ClipData(
                (CharSequence) view.getTag(),
                new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                item);
        ItemShadowBuilder dragShadow=new ItemShadowBuilder(view);
        Log.i("drag","on drag method to start");
        view.startDrag(dragData,dragShadow,view,0);
        Log.i("drag","on drag method to start2");
        view.setVisibility(View.INVISIBLE);
        return true;
    }
}
