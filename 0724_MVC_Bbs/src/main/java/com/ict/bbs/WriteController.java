package com.ict.bbs;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.FileUploadUtil;

@Controller	//컴포넌트라고 할 수 있음 객체화 해서 필요할때 가져다 쓰겠다
public class WriteController {
	
	//sts에서는 resources까지를 기억하고 있다.
	private String imgPath = "/resources/editor_img";
	private String uploadPath = "/resources/upload";
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BbsDAO bbsDao;
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping("/write")
	public String writeForm() {
		return "write";// views/write.jsp를 의미함
	}
	
	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveImage(BbsVO vo) throws Exception  {
		//write.jsp에서 비동기식 통신을 이용하여이미지를 서버에 저장하기 위해 호출되는 곳
		//그리고 끝나기 전에 반드시 저장된 이미지 경로를 보내야 한다.
		MultipartFile upload = vo.getFile();
		String f_name = null;
		Map<String, String> map = new Hashtable<String, String>();
		
		//첨부된 파일이 있는지? 확인
		if(upload != null && upload.getSize() > 0) {
			//절대경로
			String path = application.getRealPath(imgPath);
			//파일명 얻기
			f_name = upload.getOriginalFilename();
			
			//동일한 파일명이 있다면 f_name을 변경!
			f_name = FileUploadUtil.checkSameFileName(
					f_name, path);
			
			//파일올리기
			upload.transferTo(new File(path, f_name));
			
			map.put("url", request.getContextPath()+imgPath+"/"+f_name);
		}
		
		
		return map;
	}
	//포스트 방식으로 받는 메소드를 만들어야함. 인자를 BbsVO로 받아서
	//쓰기기능
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView write(BbsVO vo) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//첨부 파일 확인 후 저장(upload)
		MultipartFile mf = vo.getFile();
		if(mf != null && mf.getSize() > 0) {
			//절대경로
			String path = application.getRealPath(uploadPath);
			//실제 파일명 얻기
			vo.setOri_name(mf.getOriginalFilename());
			//동일한 파일명이 있다면 변경한다.
			String f_name = FileUploadUtil.checkSameFileName(vo.getOri_name(), path);
			
			//파일 올리기
			mf.transferTo(new File(path, f_name));
			
			//변경된 파일명 얻기
			vo.setFile_name(f_name);
		}
		
		if(vo.getBname() == null)
			vo.setBname("BBS");
		
		
		//ip지정
		vo.setIp(request.getRemoteAddr());

		//dao를 통해 bbs_t에 insert 작업!!
		int cnt = bbsDao.add(vo);
		//ListController 다시 호출 다른 컨트롤러에서 리다이렉트 필수
		//mv.setViewName("redirect:/list?cnt"+cnt);
		mv.setViewName("redirect:/list");
		
		return mv;
	}
}
