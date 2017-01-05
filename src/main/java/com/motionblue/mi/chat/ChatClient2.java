package com.motionblue.mi.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient2 {
	public static void main(String[] args){
		try{
			Socket s = new Socket("192.168.1.114", 1201);	//server ip and port
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String msgin = "", msgout = "";
			while(!msgin.equals("end")){
				msgout = br.readLine();
				dout.writeUTF(msgout);
				msgin = din.readUTF();
				System.out.println(msgin); //printing server msg.
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
