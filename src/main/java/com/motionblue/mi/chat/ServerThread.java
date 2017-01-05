package com.motionblue.mi.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread {
	Socket client;
	BufferedReader buffer;
	BufferedWriter bufferWriter;
	Vector<ServerThread> connectList;
	
	public ServerThread(Vector<ServerThread> connectList, Socket socket) {
		this.connectList = connectList;
		this.client = socket;
		try {
			buffer = new BufferedReader(new InputStreamReader((client.getInputStream())));
			bufferWriter = new BufferedWriter(new OutputStreamWriter((client.getOutputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		while(true){
			String msg = listen();
			send(msg);
		}
	}

	//메시지 청취
	public String listen(){

		String msg="";
		try {
			msg= buffer.readLine();
			System.out.println("msg:"+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

	//메시지 전송
	public void send(String msg){
		try {
			for(int i=0;i<connectList.size();i++){
				ServerThread st = connectList.get(i);
				System.out.println("send msg:"+msg);
				st.bufferWriter.write(msg+"\n");
				st.bufferWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
