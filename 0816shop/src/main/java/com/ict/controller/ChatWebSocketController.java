package com.ict.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.ict.domain.ChatVO;
import com.ict.domain.ChatroomVO;
import com.ict.domain.MemberVO;
import com.ict.persitence.ChatDAO;

@Controller
@RequestMapping("/chat/*")
public class ChatWebSocketController extends TextWebSocketHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketController.class);
	@Inject
	private ChatDAO dao;
	
	//유져 연결
	private List<WebSocketSession> connectedUsers;
	
	public ChatWebSocketController() {
		connectedUsers = new ArrayList<WebSocketSession>();
	}
	
	private Map<String, WebSocketSession> users
	= new ConcurrentHashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info(session.getId() + " 연결 됨!!");
		
		users.put(session.getId(), session);
		connectedUsers.add(session);
	}
	
	@Override
	public void afterConnectionClosed(

			WebSocketSession session, CloseStatus status) throws Exception {

		logger.info(session.getId() + " 연결 종료됨");
		connectedUsers.remove(session);
		users.remove(session.getId());

	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


		System.out.println(message.getPayload());

		  Map<String, Object> map = null;

	      ChatVO chatVO = ChatVO.convertChat(message.getPayload());

	      System.out.println("1 : " + ChatVO.class);


	      ChatroomVO roomVO  = new ChatroomVO();
	      roomVO.setGdsnum_gdsnum_id(chatVO.getGdsnum_gdsnum_id()); //상품번호
	      roomVO.setSeller_user_user_id(chatVO.getSeller_seller_id()); //판매자
	      roomVO.setUSER_user_id(chatVO.getUser_user_id()); //유저

	      ChatroomVO croom =null;
	      if(!chatVO.getUser_user_id().equals(chatVO.getSeller_seller_id())) {
	    	  System.out.println("a");



	    	  if(dao.isRoom(roomVO) == null ) {
	    		  System.out.println("b");
	    		  dao.createRoom(roomVO);
	    		  System.out.println("d");
	    		  croom = dao.isRoom(roomVO);

	    	  }else {
	    		  System.out.println("C");
	    		  croom = dao.isRoom(roomVO);
	    	  }
	      }else {

  		  croom = dao.isRoom(roomVO);
  	  }

	      chatVO.setCHATROOM_chatroom_id(croom.getChatroom_id());
	      if(croom.getUSER_user_id().equals(chatVO.getChat_sender())) {

	    	  chatVO.setChat_receiver(roomVO.getSeller_user_user_id());
	      }else {
	    	  chatVO.setChat_receiver(roomVO.getUSER_user_id());
	      }




	      for (WebSocketSession websocketSession : connectedUsers) {
	         map = websocketSession.getAttributes();
	         MemberVO login = (MemberVO) map.get("login");

	         //받는사람
	         if (login.getMemId().equals(chatVO.getChat_sender())) {

	            Gson gson = new Gson();
	            String msgJson = gson.toJson(chatVO);
	            websocketSession.sendMessage(new TextMessage(msgJson));
	         }

	         
	      }
	   }
	@Override
	public void handleTransportError(

			WebSocketSession session, Throwable exception) throws Exception {

		logger.info(session.getId() + " Exception: " + exception.getMessage());

	}
	
	private void log(String logmsg) {

		System.out.println(new Date() + " : " + logmsg);

	}
	
}
