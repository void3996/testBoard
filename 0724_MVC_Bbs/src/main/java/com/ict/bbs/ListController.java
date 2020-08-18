package com.ict.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.Paging;

@Controller
public class ListController {
	@Autowired
	private BbsDAO bbsDao;
	//페이징 기법에 필요한 여러 변수들 
	int nowPage;
	
	public final int BLOCK_LIST = 7;	// 한 페이지당 보여질 게시물 수
	
	public final int BLOCK_PAGE = 3;	// 한 블럭당 보여질 페이지 수
	
	int rowTotal;	//전체 게시물 수
	String pageCode;	//페이징 처리된 HTML 코드가 저장될 곳
	
	//나중에 게시물에 대한 검색을 한다면...
	String searchType; //0이면 제목 검색, 1이면 글쓴이 검색, 2면 내용검색, 3이면 날짜 검색... 등
	String searchValue; //검색할 단어!
	
	@RequestMapping(value = "/list")
	public ModelAndView list(String nowPage, String bname)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//현재 페이지(nowPage 값이 없으면 1로 초기화한다
		if(nowPage == null)
			this.nowPage = 1;
		else	//사용자가 페이지 번호를 누르거나 페이지 블럭을 바꾼 경우
			//스트링과 인트가 같지 않기 때문에 숫자인데 문자열 형태로 와있기 때문에 parseInt
			this.nowPage = Integer.parseInt(nowPage);
		//게시판을 구별하자!
		if(bname == null)
			bname = "BBS";//일반게시판
		
		//전체 게시물의 수
		rowTotal = bbsDao.getTotalCount(bname);
		
		//페이징 처리를 해주는 객체생성!
		Paging page = new Paging(this.nowPage, rowTotal, BLOCK_LIST, BLOCK_PAGE);
		
		
		pageCode = page.getSb().toString(); //JSP 에서 사용될 페이징 코드
		
		int begin = page.getBegin();	//nowPage에서 연산이 되어져서 온다. 그렇게 때문에 여기는 int로 받아야 한다.
		int end = page.getEnd();
		
		BbsVO[] ar = bbsDao.getList(String.valueOf(begin), String.valueOf(end), bname);
		//ModelAndView에 list란 이름에 담겨있음
		mv.addObject("list", ar);
		mv.addObject("pageCode", pageCode);
		mv.addObject("nowPage", this.nowPage);
		mv.addObject("blockList", BLOCK_LIST);
		mv.addObject("rowTotal", rowTotal);		//게시물 역순으로 나오게 하기 위해 rowTotal 반드시 필요함 끝번호가 먼저 찍혀야 하니까
		
		mv.setViewName("list");// view/list.jsp를 의미
		
				
		return mv;
		
	}
	
}
