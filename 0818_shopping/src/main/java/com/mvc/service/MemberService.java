package com.mvc.service;

import javax.servlet.http.HttpSession;

import com.mvc.domain.MemberVO;
//DAO와 Controller 사이를 연결해주는 역할
public interface MemberService {
	// 회원 가입
	public void signup(MemberVO vo) throws Exception;

	// 로그인
	public MemberVO signin(MemberVO vo) throws Exception;
	
	// 로그아웃
	public void signout(HttpSession session) throws Exception;
	
}
