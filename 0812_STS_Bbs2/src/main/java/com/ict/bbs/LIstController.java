package com.ict.bbs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.Paging;

@Controller
public class LIstController {
	@Autowired
	private BbsDAO b_dao;
	//페이징 기법에 필요한 여러 변수들
	int nowPage;	//현재 페이지 값
	
	public final int BLOCK_LIST = 10; // 한 페이지당 보여질 게시물 수
	public final int BLOCK_PAGE = 5;	// 한 블럭당 보여질 페이지 수
	
	int rowTotal;	//전체 게시물 수
	String pageCode;	//페이징 처리된 HTML 코드가 저장될곳
	
	String searchType;
	String searchValue;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/list")
	public ModelAndView list (String cPage, String bname) throws Exception{
		if(cPage == null)
			nowPage = 1;
		else 
			nowPage = Integer.parseInt(cPage);
			
		if(bname == null)
			bname = "BBS";	//일반 게시판
		//총 게시물수를 얻는다
		rowTotal = b_dao.getTotalCount(bname);
		//페이징 처리를 해주는 객체 생성
		
		Paging page = new Paging(nowPage, rowTotal, BLOCK_LIST, BLOCK_PAGE);
		//생성된 페이지 기법의 html 코드를 변수 page code에 저장
		
		pageCode = page.getSb().toString();

		//JSP에서 표현gkf 게시물들의 목록을 얻기 위한 변수
		int begin = page.getBegin();
		int end = page.getEnd();
		
		BbsVO[] ar = b_dao.getList(bname, begin, end);
		
		//jsp에서 사용할 모든 값들을 mv에 저장한다
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("list", ar);
		mv.addObject("pageCode", pageCode);
		mv.addObject("nowPage", nowPage);
		mv.addObject("blocklist", BLOCK_LIST);
		mv.addObject("rowTotal", rowTotal);
		
		mv.setViewName("list");
		
		return mv;
	} 
}
