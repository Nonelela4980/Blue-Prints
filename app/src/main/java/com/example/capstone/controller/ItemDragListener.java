package com.example.capstone.controller;

import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.capstone.BluePrintClient;
import com.example.capstone.messages.client.CardDropped;
import com.example.capstone.views.ImageCard;
import com.example.capstone.R;

public class ItemDragListener implements View.OnDragListener{

    ShowConfirmMove showConfirmMove;
    GameController gameController;
    private ImageView undo;
    private ImageView confirm;

    public static int cardId;
    public static int card_drawable;
    public ItemDragListener(GameController gameController){
        this.gameController=gameController;
        this.undo = gameController.getActivity().findViewById(R.id.undo_actionBtn);
        this.confirm = gameController.getActivity().findViewById(R.id.cofirm_actionBtn);
    }
    @Override
    public boolean onDrag(View view, DragEvent event) {
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
                if(!givingImage.isDeckCard()){
                    if(undo.getVisibility() == View.VISIBLE && confirm.getVisibility() == View.VISIBLE)
                    {
                        gameController.undo(undo, confirm);
                    }

                    gameController.setCurDeck(givingImage);
                    gameController.setCurGridCard(receivingImage);
                    gameController.setCurShade(receivingImage.getDrawable());

                    Drawable image = givingImage.getDrawable();
                    receivingImage.setImageDrawable(image);

                    receivingImage.setOnClickListener(v->receivingImage.rotateCard());
                    givingImage.setOnDragListener(new PlayerHandDragListener(gameController));//now the player hand can take other cards

                    Drawable drawable=gameController.getInitialDrawable();
                    givingImage.setImageDrawable(drawable);
                    receivingImage.setOnDragListener(null); /**must no longer take any cards if a card was placed on it*/
                    givingImage.setHas_card(false);

                    cardId=receivingImage.getId();
                    int k=(int)givingImage.getTag(); //tag was set on the customized cardView(ImageCard)

                    card_drawable=k;
                    gameController.Show_theActionButtons(undo,confirm);
                }
                break;
            default:break;
        }
        return true;
    }
}
