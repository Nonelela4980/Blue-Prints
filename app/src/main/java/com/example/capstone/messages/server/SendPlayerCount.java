package com.example.capstone.messages.server;

import com.example.capstone.Message;
public class SendPlayerCount extends Message {
    private static final long serialVersionUID=55L;
    int count;
    @Override
    public String toString() {
        return String.format("%s",count);
    }
}
