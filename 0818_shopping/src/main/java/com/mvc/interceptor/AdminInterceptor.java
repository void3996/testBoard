package com.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	
	//HandlerInterceptorAdapter에서 제공하는 method
	//컨트롤러(Requestmapping이 선언된 method) 진입하기 전에 동작
	//return 값이 true일때 정상적으로 진행되고 false면 실행을 멈춘다(컨트롤러로 진입하지 않음)
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		
		return true;
		}
}
