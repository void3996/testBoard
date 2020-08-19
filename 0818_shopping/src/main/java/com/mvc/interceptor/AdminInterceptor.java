package com.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvc.domain.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	
	//HandlerInterceptorAdapter에서 제공하는 method
	//컨트롤러(Requestmapping이 선언된 method) 진입하기 전에 동작
	//return 값이 true일때 정상적으로 진행되고 false면 실행을 멈춘다(컨트롤러로 진입하지 않음)
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//현재의 세션을 불러와 session 에 저장한 뒤,
		HttpSession session = request.getSession();
		//그중 member 라는 명칭의 세션을 불러와 MemberVO 의 형태로 변환한 뒤,
				//MemberVO 형태의 변수인 member 에 저장한다 
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		//member가 null(비로그인상태) 이거나 verify의 값이 9가 아니라면 처음화면으로 보낸다
		/*
		 * if(member == null || member.getVerify() !=9) { response.sendRedirect("/");
		 * return false; }
		 */
		//비로그인 상태에서 관리자페이지로 접속하면 로그인 창으로 보낸다
		if(member == null) {
			response.sendRedirect("/member/signin");
			return false;
		}
		
		if(member.getVerify() !=9) {
			response.sendRedirect("/");
			return false;
		}
		
		return true;
	}
}
