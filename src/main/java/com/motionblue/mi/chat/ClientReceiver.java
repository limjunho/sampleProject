package com.motionblue.mi.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver extends Thread {
	Socket socket;
	DataInputStream input;
	public ClientReceiver(Socket socket){
		this.socket = socket;
		try{
			input = new DataInputStream(socket.getInputStream());
		}catch(IOException ie){
			
		}
	}
	
	@Override
	public void run(){
		while (input != null){
			try{
				System.out.println(input.readUTF());
			}catch(IOException ie){
				
			}
		}
	}
}
