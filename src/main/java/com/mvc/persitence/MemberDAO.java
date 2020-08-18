package com.mvc.persitence;

import com.mvc.domain.MemberVO;

public interface MemberDAO {

	//회원가입
	public void signup(MemberVO vo) throws Exception;
}
