package com.mvc.service;

import javax.inject.Inject;

import com.mvc.domain.MemberVO;
import com.mvc.persitence.MemberDAO;

public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO dao;
	
	//회원 가입
	@Override
	public void signup(MemberVO vo) throws Exception {
		dao.signup(vo);
		
	}

}
