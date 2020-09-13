package com.ict.persitence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ict.domain.MessageVO;
@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@Inject
	private SqlSession sql;
	//매퍼
	private static String namespace = "com.ict.mappers.messagemapper";
	
	//메세지 작성
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		sql.insert(namespace + ".addMessage", vo);
	}
	
	//메세지 열기
	@Override
	public MessageVO readMessage(int messageid) throws Exception{
		return null;
	}
	
	//메세지 열람처리
	@Override
	public void updateMessage(int messageid) throws Exception{
		
	}
	
	//메세지 목록
	@Override
	public List<MessageVO> getList(String mid) throws Exception{			
		return sql.selectList(namespace + ".getList", mid);
	}
	
	
	
}
