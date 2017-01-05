package com.motionblue.mi.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;

public class MultiChatServer {
	private HashMap<String, DataOutputStream> clients;
	private ServerSocket serverSocket;
	
	public static void main(String[] args){
		new MultiChatServer().start();
	}
	
	private void start() {
		// TODO Auto-generated method stub
		try{
			Socket socket;
			
			// 리스너 소켓 생성
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			// 클라이언트와 연결되면
			while(true){
				// 통신 소켓을 생성하고 스레드 생성 (소켓은 1:1로만 연결된다.)
				socket = serverSocket.accept();
				ServerReceiver receiver = new ServerReceiver(socket, clients);
				receiver.start();
			}
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}

	public MultiChatServer(){
		clients = new HashMap<String, DataOutputStream>();
		
		//여러 스레드에서 접근할 것이므로 동기화
		Collections.synchronizedMap(clients);
	}
}
