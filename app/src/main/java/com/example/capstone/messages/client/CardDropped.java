package com.example.capstone.messages.client;
import androidx.annotation.NonNull;

import com.example.capstone.Message;
import com.example.capstone.models.cards.Assignment;

/**This message is send to the server when the client confirms a placement of the card*/
public class CardDropped extends Message {
    private static final long serialVersionUID = 5L;
    int drawable;
    int cardID;
    //Assignment assignment; //assignment on the card
    //int posX; row on the board
    //int posY; column on the board
    public CardDropped(int cardID,int drawable) {
        this.drawable=drawable;
        this.cardID=cardID;
    }
    @NonNull
    @Override
    public String toString()
    {
        return String.format("Card-Dropped=> cardID=%s drawable=%s",cardID,drawable);
    }
}
