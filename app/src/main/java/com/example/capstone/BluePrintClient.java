package com.example.capstone;

import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class BluePrintClient {
    private Socket socket=null;
    private ObjectOutputStream out=null;
    private ObjectInputStream in=null;
    private ReadThread readThread;
    private WriteThread writeThread;
    private OnMessageReceived onMessageReceived;
    private String SERVER_ADDRESS;
    private int PORT_NUMBER=5000;
    public BluePrintClient(OnMessageReceived onMessageReceived) {
        this.onMessageReceived = onMessageReceived;
    }
    /**Connects to the server*/
    public void connectToServer(String serverAddress){
        Log.i("ServerAddress","Server address is "+serverAddress);
        this.SERVER_ADDRESS=serverAddress;
        readThread=new ReadThread();
        readThread.start();
    }

    public void displayMessage(Message message)
    {
        if(onMessageReceived==null)
            return;
        onMessageReceived.messageReceived(message);
    }
    public class ReadThread extends Thread{
        @Override
        public void run() {
            readThread=this;
            try
            {
                socket=new Socket(SERVER_ADDRESS,5000);
                Log.i("BluePrintClient","Connected to"+ InetAddress.getLocalHost().getAddress());
                in=new ObjectInputStream(socket.getInputStream());
                out=new ObjectOutputStream(socket.getOutputStream());
                out.flush();

                writeThread=new WriteThread();
                writeThread.start();
                Log.i("ReadThread","User allowed to read messages");
                Message message;
                while (socket.isConnected())
                {
                    Log.i("ReadThread","User allowed to read messages");
                    message= (Message) in.readObject();

                    displayMessage(message);
                    Log.i("JoinedMessage",message.toString());
                }
            }catch (Exception e){
                Log.i("ReadThread","Error"+e.getMessage());
            }
            finally {
                readThread=null;
                if(writeThread!=null)
                    writeThread.interrupt();
            }
        }
    }
    public class WriteThread extends Thread{
        @Override
        public void run() {
            try
            {
             String message;
             //Allow the client to send messages to server while its connected
             while (socket.isConnected())
             {
                 Log.i("WriteThread","user is allowed to write");
             }
            }catch (Exception e){
                Log.i("ReadThread","Error"+e.getMessage());
                writeThread=null;
            }
        }
    }
}
