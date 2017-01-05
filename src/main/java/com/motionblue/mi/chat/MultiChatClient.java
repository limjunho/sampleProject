package com.motionblue.mi.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	private String name;
	private Socket socket;
	private String serverIp = "192.168.1.114";
	
	public static void main(String[] args){
		new MultiChatClient().start();
	}
	
	public void start(){
		try{
			socket = new Socket(serverIp, 7777);
			System.out.println("서버와 연결되었습니다. 대화명을 입력하세요 : ");
			name = new Scanner(System.in).nextLine();
			
			ClientReceiver clientReceiver = new ClientReceiver(socket);
			ClientSender clientSender = new ClientSender(socket, name);
			
			clientReceiver.start();
			clientSender.start();
		}catch(IOException ie){
			
		}
	}
}
