package com.ict.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.MessageVO;
import com.ict.persitence.MessageDAO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Inject
	private MessageDAO dao;
	
	@Transactional	//트랜잭션 처리대상 method
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		dao.addMessage(vo);
	}
	//메세지 열기
	@Override
	public MessageVO readMessage(int messageid) {
		return null;
	}
	//메세지 열람처리
	@Override
	public void updateMessage(int messageid) {
		
	}
	
	@Override
	public List<MessageVO> getList(String mid) throws Exception {
		
		return dao.getList(mid);
	}
	
	

}
