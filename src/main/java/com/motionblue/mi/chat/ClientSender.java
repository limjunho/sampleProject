package com.motionblue.mi.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSender extends Thread{
	Socket socket;
    DataOutputStream output;
    String name;

    public ClientSender(Socket socket, String name) {
    	this.name = name;
        this.socket = socket;
        try {
            output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(name);
            System.out.println("대화방에 입장하였습니다.");
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String msg = "";

        while (output != null) {
            try {
                msg = sc.nextLine();
                if(msg.equals("exit"))
                    System.exit(0);
                 
                output.writeUTF("[" + name + "]" + msg);
            } catch (IOException e) {
            }
        }
    }
}
