package com.ict.bbs;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.FileUploadUtil;

@Controller
public class WriteController {

	@Autowired
	private ServletContext application;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BbsDAO b_dao;
	
	private String uploadPath = "/resources/upload";
	
	@RequestMapping("/writeForm")
	public String writeForm(String cPage, Model m) {
		m.addAttribute("nowPage", cPage);
		
		return "write";
	}
	
	@RequestMapping(value="/write",
			method = RequestMethod.POST)
	public ModelAndView write(BbsVO vo)throws Exception{
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
		
		//DB저장
		boolean value = b_dao.add(vo);
		if(value)
			mv.setViewName("redirect:/list");
		else
			mv.setViewName(
			"redirect:/writeForm?cPage="+vo.getNowPage());
		
		return mv;
	}
}

