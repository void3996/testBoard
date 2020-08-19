package com.mvc.controller;

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

import com.mvc.domain.MemberVO;
import com.mvc.service.MemberService;

//view/member 안의 jsp를 맵핑
@Controller
@RequestMapping("/member/*")
public class memberController {
	
	private static final Logger Logger = LoggerFactory.getLogger(memberController.class);
		
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	//회원 가입 get 화면을 띄우기 위한 용도 및 추가적으로 DB 또는 추가 로직을 통하여 사용자에게 보여줄 데이터를 준비
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() {
		Logger.info("get signup");
	}
	
	//회원가입 post 사용자가 입력한 데이터를 처리하는 로직으로 연결
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO vo) throws Exception{
		Logger.info("post signup");
		
		String inputPass = vo.getUserPass();	//vo로부터 입력받은 패스워드
		String pass = passEncoder.encode(inputPass);
		//패스워드를 BCryptPasswordEncoder로 암호화 시키고
		vo.setUserPass(pass);	//다시 MemberVo에 넘겨준다.
		
		service.signup(vo);
		
		return "redirect:/";
		
	}
	//로그인 get
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception{
		Logger.info("get signin");
	}
	//로그인 post
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String postSignup(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		Logger.info("post signup");
		
		
		
		MemberVO login = service.signin(vo);
		HttpSession session = req.getSession();
		
		//사용자가 입력한 password와 db에 저장된 password를 비교해서 동일하면 true, 아닐경우 false를 반환
		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());
		
		if (login != null && passMatch) {	//login에 값이 없거나 id와 password가 모두 맞으면
			session.setAttribute("member", login);	//해당 코드가 실행된다
		}else {
			session.setAttribute("member", null);	//id나 password 중 하나라도 틀리다면 세션 값 제거하고 
			rttr.addFlashAttribute("msg", false);	//특정 페이지로 이동될때 1회성 변수 msg의 false를 부여한다
			return "redirect:/member/signin";
		}
		
		return "redirect:/";
	
	}
	
	//로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception{
		Logger.info("get logout");
		
		service.signout(session);
		
		return "redirect:/";
	}
}
