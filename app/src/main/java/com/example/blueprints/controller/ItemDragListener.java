package com.example.blueprints.controller;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.blueprints.ImageCard;
import com.example.blueprints.R;

public class ItemDragListener implements View.OnDragListener{

    ShowConfirmMove showConfirmMove;
    GameController gameController;
    private ImageView undo;
    private ImageView confirm;
    private Drawable previousDrawable;
    public ItemDragListener(GameController gameController) {
       // this.showConfirmMove = showConfirmMove;
        this.gameController=gameController;
        this.undo = gameController.getActivity().findViewById(R.id.undo_actionBtn);
        this.confirm = gameController.getActivity().findViewById(R.id.cofirm_actionBtn);
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        Log.i("check Drag","drag started");
        ImageCard givingImage = (ImageCard) event.getLocalState();
        ImageCard receivingImage = (ImageCard)view;
        int[] views=new int[2];

        views[0]=givingImage.getId();
        views[1]=receivingImage.getId();

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
                if(undo.getVisibility() == View.VISIBLE && confirm.getVisibility() == View.VISIBLE){
                    gameController.undo(undo, confirm);
                }
                gameController.setCurDeck(givingImage);
                gameController.setCurGridCard(receivingImage);
                gameController.setCurShade(receivingImage.getDrawable());
                //previousDrawable=receivingImage.getDrawable();
                Drawable image = givingImage.getDrawable();
                receivingImage.setImageDrawable(image);
                receivingImage.setOnClickListener(v->receivingImage.rotateCard());
                Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
                givingImage.setImageDrawable(transparentDrawable);
                receivingImage.setOnDragListener(null); /**must no longer take any cards if a card was placed on it*/
                Log.i("bound","dx is recieving"+receivingImage.getDx());
//                if(receivingImage.getDx()==1){
//                    gameController.lockNeigbours();
//                }
                gameController.Show_theActionButtons(undo,confirm);
                break;
            default:break;
        }
        return true;
    }
}
