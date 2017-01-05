package com.motionblue.mi.util;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	private static Logger log = LoggerFactory.getLogger(EchoHandler.class);
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		sessionList.add(session);
		
		log.info("{} 연결됨", session.getId());
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		log.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage("echo:" + message.getPayload()));
		}
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		sessionList.remove(session);
		
		log.info("{}연결 끊김.", session.getId());
	}
}
