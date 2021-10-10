package com.example.blueprints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class playActivity extends AppCompatActivity  {

    ItemDragListener dragListener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_play);

        ImageView card=findViewById(R.id.imageView12);
        /**The user starts the drag operation by a longPress*/
        card.setOnLongClickListener(view -> {
            ClipData.Item item = new ClipData.Item((Intent) view.getTag());

            ClipData dragData = new ClipData(
                    (CharSequence) view.getTag(),
                    new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                    item);
            ItemShadowBuilder dragShadow=new ItemShadowBuilder(view);
//            view.startDrag(dragData,dragShadow,null,0);
                view.startDragAndDrop(dragData,dragShadow,view,0);

                view.setVisibility(View.INVISIBLE);
            return true;
        });

        dragListener=new ItemDragListener();
        card.setOnDragListener(dragListener);
    }

}