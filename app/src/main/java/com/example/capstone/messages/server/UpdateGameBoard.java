package com.example.capstone.messages.server;
import com.example.capstone.Message;
/**Message receive from server to update the board*/
public class UpdateGameBoard extends Message {
    private static final long serialVersionUID = 25L;
    public int drawable;
     public int cardID;
    public UpdateGameBoard(int cardID,int drawable)
    {
        this.cardID=cardID;
        this.drawable = drawable;
    }
}
