package com.ict.persitence;

import java.util.List;

import com.ict.domain.MessageVO;

public interface MessageDAO {
	
	//메세지 작성
	public void addMessage(MessageVO vo) throws Exception;
	
	//메세지 목록
	public List<MessageVO> getList(String mid) throws Exception;
	
	//메세지 열기
	public MessageVO readMessage(int messageid) throws Exception;
	
	//메세지 열람처리
	public void updateMessage(int messageid) throws Exception;
}
