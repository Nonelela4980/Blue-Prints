package com.example.blueprints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class playActivity extends AppCompatActivity  {

    ItemDragListener dragListener;
    RelativeLayout.LayoutParams layoutParams;
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
//        card.setOnLongClickListener(view -> {
//            Log.i("drag","drag and drop pressed");
//            ClipData.Item item = new ClipData.Item((Intent) view.getTag());
//
//            ClipData dragData = new ClipData(
//                    (CharSequence) view.getTag(),
//                    new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
//                    item);
//            ItemShadowBuilder dragShadow=new ItemShadowBuilder(view);
//            Log.i("drag","on drag method to start");
//                view.startDrag(dragData,dragShadow,card,0);
//            Log.i("drag","on drag method to start2");
//                view.setVisibility(View.INVISIBLE);
//            return true;
//        });

        card.setOnLongClickListener(new ItemLongClickListener());

        dragListener=new ItemDragListener();
        card.setOnDragListener(new ItemDragListener());
        //card.setOnDragListener(dragListener);
//        card.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View view, DragEvent event) {
//                Log.i("drag","onDrag method");
//                int action = event.getAction();
//                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED: layoutParams=(RelativeLayout.LayoutParams) view.getLayoutParams();
//                        break;
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        int x__cord=(int)event.getX();
//                        int y_cord=(int)event.getY();
//                        Log.i("drag","drag and drop started");
//                        break;
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        Log.i("drag","drag and drop exited");
//                        x__cord=(int)event.getX();
//                        y_cord=(int)event.getY();
//                        layoutParams.leftMargin=x__cord;
//                        layoutParams.topMargin=y_cord;
//                        view.setLayoutParams(layoutParams);
//                        break;
//                    case DragEvent.ACTION_DRAG_LOCATION:
//                        x__cord=(int)event.getX();
//                        y_cord=(int)event.getY();
//                        break;
//                    case DragEvent.ACTION_DRAG_ENDED:
//                        System.out.println("hello world");
//                        break;
//                    case DragEvent.ACTION_DROP:
//                        System.out.println("hello dsfworld");
//                        break;
//                    default:break;
//                }
//                return true;
//            }
//        });

        card.setOnTouchListener(new ItemTouchListener());


    }

}