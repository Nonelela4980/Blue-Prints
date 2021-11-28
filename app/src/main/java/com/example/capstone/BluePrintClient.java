package com.example.capstone;
import android.util.Log;
import com.example.capstone.messages.client.Quit;
import com.example.capstone.messages.server.JoinedGame;
import com.example.capstone.messages.server.SendPlayerCount;
import com.example.capstone.messages.server.StartPlay;
import com.example.capstone.messages.server.UpdateGameBoard;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
public class BluePrintClient {
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private ReadThread readThread;
    private WriteThread writeThread;
    private OnMessageReceived onMessageReceived;
    private StartGame startGame;
    private static OnUpdateUI onUpdateUI;
    public boolean isConnected = false;
    public static   String username;
    public static void setOnUpdateUI(OnUpdateUI updateUI) {
        onUpdateUI = updateUI;
    }
    private String SERVER_ADDRESS;
    private static BlockingQueue<Message> outgoingMessages = new LinkedBlockingDeque<>();

    public BluePrintClient(OnMessageReceived onMessageReceived, StartGame startGame) {
        this.onMessageReceived = onMessageReceived;
        this.startGame = startGame;
    }
    /**
     * Connects to the server
     */
    public void connectToServer(String serverAddress,String username) {
        Log.i("ServerAddress", "Server address is " + serverAddress);
        this.SERVER_ADDRESS = serverAddress;
        this.username=username;
        readThread = new ReadThread();
        readThread.start();
    }

    public String getUsername(){
        return username;
    }
    //Put messages on the outgoing messages queue
    public static void sendMessage(Message message) {
        try {
            outgoingMessages.put(message);
        } catch (Exception e) {
            Log.i("Error-client-SendMessage", e.getMessage());
        }
    }

    public void displayMessage(Message message) {
        if (onMessageReceived == null)
            return;
        onMessageReceived.messageReceived(message);
    }

    //A read thread to read messages from the server
    public class ReadThread extends Thread {
        @Override
        public void run() {
            readThread = this;
            try {
                socket = new Socket(SERVER_ADDRESS, 5000);
                isConnected = socket.isConnected();
                Log.i("BluePrintClient", "Connected to" + InetAddress.getLocalHost().getAddress());
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();

                writeThread = new WriteThread();
                writeThread.start();
                Log.i("ReadThread", "User allowed to read messages");
                Message message;
                while (socket.isConnected()) {
                    Log.i("ReadThread", "User allowed to read messages");
                    message = (Message) in.readObject();

                    if (message.getClass() == StartPlay.class)
                        startGame.startGame();
                    else if (message.getClass() == JoinedGame.class)
                        displayMessage(message);
                    else if (message.getClass() == Quit.class) {
                        Log.i("Server>>", message.toString());
                    }
                    else {
                        onUpdateUI.updateBoard(message);
                    }
                    Log.i("Server>>", message.toString());
                }
            } catch (Exception e) {
                Log.i("ReadThread", "Error" + e.getMessage());
            } finally {
                readThread = null;
                if (writeThread != null)
                    writeThread.interrupt();
            }
        }
    }

    //a write thread to write messages to the client
    public class WriteThread extends Thread {
        @Override
        public void run() {
            writeThread = this;
            try {
                Message message;
                //Allow the client to send messages to server while its connected
                while (socket.isConnected()) {
                    message = outgoingMessages.take();
                    out.writeObject(message);
                    out.flush();
                    Log.i("WriteThread", "user is allowed to write");
                }
            } catch (Exception e) {
                Log.i("ReadThread", "Error" + e.getMessage());
                writeThread = null;
            }
        }
    }
}
