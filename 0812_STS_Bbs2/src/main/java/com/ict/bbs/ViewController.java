package com.ict.bbs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class ViewController {

	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/view")
	public ModelAndView view(String nowPage, 
			String seq, String bname)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//한번이라도 읽기를 한 게시물은
		//세션에 read_list라는 이름으로 저장된 
		//ArrayList에 있다.
		Object obj = session.getAttribute("read_list");
		
		List<BbsVO> r_list = null;
		if(obj != null)
			r_list = (List<BbsVO>) obj;
		else
			r_list = new ArrayList<BbsVO>();
		
		if(bname == null)
			bname = "BBS";
		
		BbsVO vo = null;
		if(seq != null) {
			vo = b_dao.getBbs(seq, bname);
			
			//한번이라도 읽었던 게시물인지? 아닌지? 판단!!!
			boolean chk = false;
			for(BbsVO bvo : r_list) {
				if(vo.getSeq().equals(bvo.getSeq())) {
					//같은 게시물을 찾은 경우
					//(이전에 읽었던 게시물)
					chk = true;
					break;//가장 가까운 반복문 탈출!
				}
			}
			
			if(!chk) {
				//한번도 읽기를 하지 않은 게시물 (hit증가)
				b_dao.hit(seq);
				
				String h = vo.getHit();
				int hit = Integer.parseInt(h);
				++hit;
				vo.setHit(String.valueOf(hit));
				
				//r_list에 vo를 저장!
				r_list.add(vo);
				session.setAttribute("read_list", r_list);
			}
		}
		
		mv.addObject("vo", vo);
		//mv.addObject("nowPage", nowPage);
		// 파라미터로 전달되어 왔으므로 view.jsp에서
		// ${param.nowPage}로 활용할 수 있기 때문에 
		// addObject를 하지 않아도 된다.
		mv.setViewName("view");
		return mv;
	}
}
