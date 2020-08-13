package com.ict.bbs;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.FileUploadUtil;

@Controller
public class AnswerController {

	@Autowired
	private ServletContext application;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BbsDAO b_dao;
	
	private String uploadPath = "/resources/upload";
	
	@RequestMapping(value="/answer",
			method = RequestMethod.GET)
	public ModelAndView answer(BbsVO vo) {
		//파라미터로 seq, ref, step, sunbun, nowPage등이
		//넘어온다. 이것이 모두 vo에 저장된 상태다.
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		mv.setViewName("answer");
		
		return mv;
	}
	
	@RequestMapping(value="/answer",
			method = RequestMethod.POST)
	public ModelAndView addAnswer(BbsVO vo)throws Exception {
		ModelAndView mv = new ModelAndView();
		
		MultipartFile mf = vo.getUpload();
		
		if(mf != null && mf.getSize() > 0) {
			//절대경로
			String path = application.getRealPath(
					uploadPath );
			
			//파일명 얻기
			String f_name = mf.getOriginalFilename();
			
			//동일한 파일명이 있다면 변경하자!
			f_name = FileUploadUtil.checkSameFileName(
					f_name, path);
			
			//업로드
			mf.transferTo(new File(path, f_name));
			
			vo.setUploadFileName(f_name);
		}else
			vo.setUploadFileName("");
		
		vo.setIp(request.getRemoteAddr());
		
		
		//sunbun정리		
		b_dao.updateSunbun(vo.getRef(), vo.getSunbun());
		
		//참조글의 step에서 1을 더한 값을 준비하자!
		int step = Integer.parseInt(vo.getStep())+1;
		vo.setStep(String.valueOf(step));
		
		int sunbun = Integer.parseInt(
				vo.getSunbun())+1;
		vo.setSunbun(String.valueOf(sunbun));
		
		
		
		//DB저장
		boolean value = b_dao.addAns(vo);
		
		mv.setViewName("redirect:/list?cPage="+
				vo.getNowPage());
		
		return mv;
	}
}
