package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.MemberVO;
import com.ict.domain.MessageVO;
import com.ict.service.MessageService;

@RestController
@RequestMapping("/message/*")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Inject
	MessageService messageService;
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public void getMessage() throws Exception{
		logger.info("get message");
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	//HTTP상태코드+데이터 전달 JSON 데이터를 객체로 만들어준다
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
		
		logger.info("--------------------------------");
		logger.info(""+vo);
		logger.info("get sendMessage");
		
		ResponseEntity<String> entity = null;
			
		try {
			messageService.addMessage(vo);
			//			ResponseEntity<자료형>(리턴값, HTTP상태코드)
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/list")
	//HTTP상태코드+데이터 전달 JSON 데이터를 객체로 만들어준다
	public ResponseEntity<List<MessageVO>> getList(HttpSession session) throws Exception{
	
		System.out.println("getList............");
		//
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		String mid = member.getMemId();
		
		List<MessageVO> msglist = messageService.getList(mid);
		
		
		
		System.out.println(mid);
		return new ResponseEntity<List<MessageVO>>(msglist, HttpStatus.OK);
	}
	
}
