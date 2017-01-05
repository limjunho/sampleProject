package com.motionblue.mi.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ServerReceiver extends Thread{
	Socket socket;
	HashMap<String, DataOutputStream> clients;
	DataInputStream input;
	DataOutputStream output;
	
	public ServerReceiver(Socket socket, HashMap<String, DataOutputStream> clients){
		this.socket = socket;
		this.clients = clients;
		try{
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		}catch(Exception e){
			
		}
	}
	
	@Override
	public void run(){
		String name = "";
		try{
			//클라이언트가 서버에 접속하면 대화방에 알린다.
			name = input.readUTF();
			sendToAll("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "님이 대화방에 접속하였습니다.");
			
			clients.put(name, output);
			System.out.println(name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "님이 대화방에 접속하였습니다.");
			
			System.out.println("현재 " + clients.size() + "명이 대화방에 접속 중입니다.");
			
			//메시지 전송
			while(input != null){
				sendToAll(input.readUTF());
			}
		}catch (IOException ie){
			ie.printStackTrace();
		}finally{
			// 접속이 종료되면
			clients.remove(name);
			sendToAll("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "님이 대화방에 나갔습니다.");
			System.out.println(name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "님이 대화방에 나갔습니다.");
			System.out.println("현재 " + clients.size() + "명이 대화방에 접속 중입니다.");
		}
	}
	
	public void sendToAll(String message){
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()){
			try{
				DataOutputStream dos = clients.get(it.next());
				dos.writeUTF(message);
			}catch(Exception e){
				
			}
		}
	}
}
