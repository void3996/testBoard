package com.ict.domain;

public class ChatroomVO {
	

//		    chatroom_id NUMBER(11) not null,
//		    user_user_id varchar2(45),
//		    seller_user_user_id varchar(45),
//		    gdsnum_gdsnum_id Number(11)

	private String chatroom_id;
	private String USER_user_id;
	private String seller_user_user_id;
	private int gdsnum_gdsnum_id;
	public String getChatroom_id() {
		return chatroom_id;
	}
	public void setChatroom_id(String chatroom_id) {
		this.chatroom_id = chatroom_id;
	}
	public String getUSER_user_id() {
		return USER_user_id;
	}
	public void setUSER_user_id(String uSER_user_id) {
		USER_user_id = uSER_user_id;
	}
	public String getSeller_user_user_id() {
		return seller_user_user_id;
	}
	public void setSeller_user_user_id(String seller_user_user_id) {
		this.seller_user_user_id = seller_user_user_id;
	}
	public int getGdsnum_gdsnum_id() {
		return gdsnum_gdsnum_id;
	}
	public void setGdsnum_gdsnum_id(int gdsnum_gdsnum_id) {
		this.gdsnum_gdsnum_id = gdsnum_gdsnum_id;
	}
	@Override
	public String toString() {
		return "ChatroomVO [chatroom_id=" + chatroom_id + ", USER_user_id=" + USER_user_id + ", seller_user_user_id="
				+ seller_user_user_id + ", gdsnum_gdsnum_id=" + gdsnum_gdsnum_id + "]";
	}
	
	
	
	
	
	
}
