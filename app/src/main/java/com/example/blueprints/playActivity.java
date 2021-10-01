package com.example.blueprints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.blueprints.models.BluePrints;
import com.example.blueprints.models.Brick;
import com.example.blueprints.models.InstructionGive;
import com.example.blueprints.models.Straw;
import com.example.blueprints.models.Wood;

public class playActivity extends AppCompatActivity  {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_play);


        InstructionGive card=new InstructionGive(360,new Wood(3));
         imageView=findViewById(R.id.imageView5);

        imageView.setImageResource(card.get_drawable());

    }

    float x,y,dx,dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x=event.getX();
            y=event.getY();
        }

        if(event.getAction()==MotionEvent.ACTION_MOVE){
            dx=event.getX()-x;
            dy=event.getY()-y;

            imageView.setX(imageView.getX()+dx);
            imageView.setY(imageView.getY()+dy);

            x=event.getX();
            y=event.getY();
        }

        return super.onTouchEvent(event);
    }
}