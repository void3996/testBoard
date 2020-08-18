package com.mvc.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.domain.MemberVO;
import com.mvc.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger Logger = LoggerFactory.getLogger(MemberController.class);
		
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	//회원 가입 get
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() {
		Logger.info("get signup");
	}
	
	//회원가입 post
	@RequestMapping
	public String postSignup(MemberVO vo) throws Exception{
		Logger.info("post signup");
		
		String inputPass = vo.getUserPass();	//vo로부터 입력받은 패스워드
		String pass = passEncoder.encode(inputPass);
		//패스워드를 BCryptPasswordEncoder로 암호화 시키고
		vo.setUserPass(pass);	//다시 MemberVo에 넘겨준다.
		
		service.signup(vo);
		
		return "redirect:/";
		
	}
}
