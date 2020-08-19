package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	//log4j 를 이용하여 console 창에 log가 찍히게 한다 오류발생 대처 편리
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping
	public void getIndex() throws Exception{
		logger.info("get index");
	}
}
