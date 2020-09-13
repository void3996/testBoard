package com.ict.domain;

import java.sql.Date;

import com.google.gson.Gson;

public class ChatVO {
	
	
//		    chat_id NUMBER(11) not null,
//		    chat_sender VARCHAR2(45),
//		    chat_receiver VARCHAR2(45),
//		    chat_content VARCHAR2(4000),
//		    chat_sendtime DATE,
//		    chatroom_chatroom_id NUMBER(11),
//		    chat_readtime DATE,
//		    user_user_id VARCHAR2(45),
//		    seller_USER_user_id VARCHAR2(45),
//		    gdsnum_gdsnum_id NUMBER(11));

	private String CHAT_id;
	private String chat_sender;
	private String chat_receiver;
	private String chat_content;
	private Date chat_sendTime;
	private Date chat_readTime;
	private String CHATROOM_chatroom_id;
	private String user_user_id;
	private String seller_USER_user_id;
	private int gdsnum_gdsnum_id;
	
	private String user_profileImagePath;
	private String receiver_user_profileImagePath;
	private String memNickname;
	private String receiver_memNickname;
	private String gdsName;
	private int gdsNum;
	private String seller_seller_id;
	private String seller_name;
	private String buyer_id;
	private int unReadCount;
	public String getCHAT_id() {
		return CHAT_id;
	}
	public void setCHAT_id(String cHAT_id) {
		CHAT_id = cHAT_id;
	}
	public String getChat_sender() {
		return chat_sender;
	}
	public void setChat_sender(String chat_sender) {
		this.chat_sender = chat_sender;
	}
	public String getChat_receiver() {
		return chat_receiver;
	}
	public void setChat_receiver(String chat_receiver) {
		this.chat_receiver = chat_receiver;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public Date getChat_sendTime() {
		return chat_sendTime;
	}
	public void setChat_sendTime(Date chat_sendTime) {
		this.chat_sendTime = chat_sendTime;
	}
	public Date getChat_readTime() {
		return chat_readTime;
	}
	public void setChat_readTime(Date chat_readTime) {
		this.chat_readTime = chat_readTime;
	}
	public String getCHATROOM_chatroom_id() {
		return CHATROOM_chatroom_id;
	}
	public void setCHATROOM_chatroom_id(String cHATROOM_chatroom_id) {
		CHATROOM_chatroom_id = cHATROOM_chatroom_id;
	}
	public String getUser_user_id() {
		return user_user_id;
	}
	public void setUser_user_id(String user_user_id) {
		this.user_user_id = user_user_id;
	}
	public String getSeller_USER_user_id() {
		return seller_USER_user_id;
	}
	public void setSeller_USER_user_id(String seller_USER_user_id) {
		this.seller_USER_user_id = seller_USER_user_id;
	}
	public int getGdsnum_gdsnum_id() {
		return gdsnum_gdsnum_id;
	}
	public void setGdsnum_gdsnum_id(int gdsnum_gdsnum_id) {
		this.gdsnum_gdsnum_id = gdsnum_gdsnum_id;
	}
	public String getUser_profileImagePath() {
		return user_profileImagePath;
	}
	public void setUser_profileImagePath(String user_profileImagePath) {
		this.user_profileImagePath = user_profileImagePath;
	}
	public String getReceiver_user_profileImagePath() {
		return receiver_user_profileImagePath;
	}
	public void setReceiver_user_profileImagePath(String receiver_user_profileImagePath) {
		this.receiver_user_profileImagePath = receiver_user_profileImagePath;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getReceiver_memNickname() {
		return receiver_memNickname;
	}
	public void setReceiver_memNickname(String receiver_memNickname) {
		this.receiver_memNickname = receiver_memNickname;
	}
	public String getGdsName() {
		return gdsName;
	}
	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}
	public int getGdsNum() {
		return gdsNum;
	}
	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}
	public String getSeller_seller_id() {
		return seller_seller_id;
	}
	public void setSeller_seller_id(String seller_seller_id) {
		this.seller_seller_id = seller_seller_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public int getUnReadCount() {
		return unReadCount;
	}
	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}
	
	public static ChatVO convertChat(String source) {
		ChatVO chat = new ChatVO();
		Gson gson = new Gson();
		chat = gson.fromJson(source, ChatVO.class);
		return chat;
	}
	
	
	
	
}
