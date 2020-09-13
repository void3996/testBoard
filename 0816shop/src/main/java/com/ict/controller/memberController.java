package com.ict.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.MemberVO;
import com.ict.service.MemberService;
import com.ict.controller.memberController;

@Controller
@RequestMapping("/member/*")
public class memberController {
	
	private static final Logger logger = LoggerFactory.getLogger(memberController.class);
	
	@Inject
	MemberService service;

	@Autowired
	BCryptPasswordEncoder passEncoder;
	  
	// 회원 가입 get
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() throws Exception {
	 logger.info("get signup");
	}

	// 회원 가입 post
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO vo) throws Exception {
	 logger.info("post signup");
	 
	 String birthday = vo.getMemBirthday1()+"/"+vo.getMemBirthday2()+"/"+vo.getMemBirthday3();
	 String inputPass = vo.getMemPassword();
	 String pass = passEncoder.encode(inputPass);
	 vo.setMemPassword(pass);
	 vo.setMemBirthday(birthday);

	 service.signup(vo);

	 return "redirect:/";
	}
	
	// 로그인  get
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception {
	 logger.info("get signin");
	}

	// 로그인 post
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
	 logger.info("post signin");
	   
	 MemberVO login = service.signin(vo);  
	 HttpSession session = req.getSession();
	 
	 boolean passMatch = passEncoder.matches(vo.getMemPassword(), login.getMemPassword());
	 
	 if(login != null && passMatch) {
	  session.setAttribute("member", login);
	 } else {
	  session.setAttribute("member", null);
	  rttr.addFlashAttribute("msg", false);
	  return "redirect:/member/signin";
	 }  
	 
	 return "redirect:/";
	}
	  
	// 로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {
	 logger.info("get logout");
	 
	 service.signout(session);
	   
	 return "redirect:/";
	}
	
	// 내 정보
	@RequestMapping(value = "/memedit", method = RequestMethod.GET)
	public void getMemedit(MemberVO vo) throws Exception {
	 logger.info("get memedit");
	 
	}
	
	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public String goMsg() throws Exception {
	 logger.info("get signin");
	 return "/utils/message";
	}
}