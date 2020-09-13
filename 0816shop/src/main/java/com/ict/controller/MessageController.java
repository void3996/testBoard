package com.ict.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.domain.MemberVO;
import com.ict.domain.MessageVO;
import com.ict.service.MessageService;

@Controller
@RequestMapping("/message/*")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Inject
	MessageService messageService;
	
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public void getMessage(HttpSession session, Model model ) throws Exception{
		logger.info("get message");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		
		
		System.out.println("getList............2: " + member);
		String mid = member.getMemId();
		
		System.out.println("getList............3: " + mid);
		
		List<MessageVO> msglist = messageService.getList(mid);
		
		model.addAttribute("msglist", msglist);
		
		
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	//HTTP상태코드+데이터 전달 JSON 데이터를 객체로 만들어준다
	public String addMessageGET(String target, Model model){
		
		System.out.println("target: " + target);
		
		model.addAttribute("target", target);
		
		return "/message/message_send";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	//HTTP상태코드+데이터 전달 JSON 데이터를 객체로 만들어준다
	public String addMessage(HttpSession session, MessageVO vo){
		
		
		
		logger.info("--------------------------------");
		logger.info(""+vo);
		logger.info("send sendMessage");
		
		
		try {
			messageService.addMessage(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/message/my";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//HTTP상태코드+데이터 전달 JSON 데이터를 객체로 만들어준다
	public ResponseEntity<List<MessageVO>> getList(HttpSession session) throws Exception{
	
		System.out.println("getList............");
		
		if(session.getAttribute("member") == null) {
			
			
			String mid = "a";
			
			System.out.println("getList............3: " + mid);
			
			List<MessageVO> msglist = messageService.getList(mid);
			
			System.out.println(mid);
			return new ResponseEntity<List<MessageVO>>(msglist, HttpStatus.OK);
			
		}else {
			
			//
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			
			
			System.out.println("getList............2: " + member);
			String mid = member.getMemId();
			
			System.out.println("getList............3: " + mid);
			
			List<MessageVO> msglist = messageService.getList(mid);
			
			System.out.println(mid);
			return new ResponseEntity<List<MessageVO>>(msglist, HttpStatus.OK);
			
		}

	}
	
}
