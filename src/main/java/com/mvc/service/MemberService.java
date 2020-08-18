package com.mvc.service;

import com.mvc.domain.MemberVO;

public interface MemberService {
	//회원가입
	public void signup(MemberVO vo) throws Exception;
}
