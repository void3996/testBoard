package com.ict.persitence;

import java.util.List;

import com.ict.domain.ChatVO;
import com.ict.domain.ChatroomVO;
import com.ict.domain.MessageVO;

public interface ChatDAO {
	
	public void createRoom(ChatroomVO vo)throws Exception;
	public ChatroomVO isRoom(ChatroomVO vo)throws Exception;
	public void insertMessage(ChatVO vo)throws Exception;
	public String getPartner(ChatroomVO vo)throws Exception;
	public String getProfile(String str)throws Exception;
	public String getName(String str)throws Exception;
	public List<ChatVO> getMessageList(String str)throws Exception;
	public List<ChatroomVO> getRoomList(String str)throws Exception;
	public List<ChatroomVO> getRoomList2(String str)throws Exception;
	public MessageVO getRecentMessage(String str)throws Exception;
	//public String isGetChatList(String str)throws Exception;
	
	public String getTutorId(String str)throws Exception;
	public List<ChatroomVO> getRoomListTutor(String str)throws Exception;
	public void updateReadTime(int gdsNum, String memId, String seller_USER_user_id)throws Exception;
	public void updateReadTimeTutor(int gdsNum, String memId, String seller_USER_user_id)throws Exception;
	
	public int getUnReadCount(String seller_USER_user_id, int gdsNum, String memId)throws Exception;
	public int getUnReadCountTutor(String seller_USER_user_id, int gdsNum, String memId)throws Exception;
	
	public int getAllCount(String str);
}
