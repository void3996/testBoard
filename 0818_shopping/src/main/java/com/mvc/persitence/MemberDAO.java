package com.mvc.persitence;

import com.mvc.domain.MemberVO;
//DB와 접속하는 역할
public interface MemberDAO {

	//회원가입
	public void signup(MemberVO vo) throws Exception;
	
	//로그인
	public MemberVO signin(MemberVO vo) throws Exception;
}
