package com.ict.bbs;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

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
public class EditController {

	private String uploadPath = "/resources/upload";
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BbsDAO bbsDao;
	
	@RequestMapping(value = "/edit", 
		method = RequestMethod.POST)
	public ModelAndView edit(BbsVO bvo) {
		
		//view.jsp에서 전달해 오는 파라미터 들이 bvo에 저장된 상태다.
		
		String ctx = request.getContentType();
		System.out.println(ctx); //application...
		
		ModelAndView mv = new ModelAndView();
		Map<String, String> map = 
				new Hashtable<String, String>();
		map.put("b_idx", bvo.getB_idx());
		map.put("bname", bvo.getBname());
		BbsVO vo = bbsDao.getBbs(map);
		if(vo != null) {
			mv.addObject("vo", vo);
			mv.setViewName("edit");//edit.jsp를 의미함!
						//편집할 수 있는 화면으로 이동!!
		}else
			mv.setViewName("redirect:/list");			
		
		mv.addObject("nowPage", bvo.getNowPage());
		mv.addObject("bname", bvo.getBname());
		
		return mv;
	}
	
	
	@RequestMapping("/edit_ok")
	public ModelAndView editOk(BbsVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		String ctx = request.getContentType();
		System.out.println(ctx); //multipart....
		
		//edit.jsp에서 넘어오는 파라미터들을 vo에 담고,
		// 그 값들을 bbs_t테이블에 update해야 한다.
		/*
		System.out.println(vo.getSubject());
		System.out.println(vo.getWriter());
		System.out.println(vo.getNowPage());
		System.out.println(vo.getB_idx());
		*/
		//if(ctx.startsWith("multipart")) {
		//첨부파일이 있는지? 확인
		MultipartFile mf = vo.getFile();
		if(mf != null && mf.getSize() > 0) {
			//절대경로 얻기
			String path = application.getRealPath(
					uploadPath);
			
			String f_name = mf.getOriginalFilename();
			
			f_name = FileUploadUtil.checkSameFileName(
					f_name, path);
			
			//업로드!!!
			mf.transferTo(new File(path, f_name));
			
			//파일명을 vo에 저장해 둔다.
			vo.setFile_name(f_name);
		}
		
		vo.setIp(request.getRemoteAddr());//ip 저장!
		
		//bbs_t테이블에 수정!!
		boolean chk = bbsDao.edit(vo);
		
		//성공여부를 확인하기 위해 작업이 필요하면 chk를 저장하고 가야 한다.
		
		mv.setViewName("redirect:/view?b_idx="+vo.getB_idx()+
			"&nowPage="+vo.getNowPage()+"&bname="+
				vo.getBname()+"&ch="+chk);
		return mv;
	}
}








