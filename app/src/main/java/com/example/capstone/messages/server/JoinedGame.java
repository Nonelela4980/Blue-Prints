package com.example.capstone.messages.server;
import com.example.capstone.Message;
public class JoinedGame extends Message {
    private static final long serialVersionUID = 40L;
    private String player_username;
    @Override
    public String toString() {
        return String.format("%s is online",player_username);
    }
}
