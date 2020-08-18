package com.ict.bbs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class ViewController {
	@Autowired
	private BbsDAO bbsDao;
	
	@Autowired
	private HttpSession session;
	
	//list.jsp 에서 제목을 클릭했을때 호출됨
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public ModelAndView view(String b_idx, String nowPage, String bname, String ch) {
		//한번이라도 읽기를 한 게시물들은
		//세션에 read_list라는 이름으로 저장된 ArrayList에 저장해둔다.
		Object obj = session.getAttribute("read_list");	
		//ArrayList<BbsVO> 제너릭 타입은<BbsVO> 세션은 오브젝트파일로 받는다.
		List<BbsVO> r_list = null;
		if(obj != null)
			r_list = (List<BbsVO>) obj;
		else  //비어있을 경우 r_list를 새로 만들어줘
			r_list = new ArrayList<BbsVO>();
		//이 if문을 지나면 r_list는 null이 아니다.
		
		
		
		// BbsDAO의 getBbs메서드를 호출하기 위해 Map 구조 생성!

		
		Map<String, String> map = new Hashtable<String, String>();		

		
		map.put("b_idx", b_idx);
		map.put("bname", bname);
		
		BbsVO vo = bbsDao.getBbs(map);
		
		//한번이라도 읽었던 게시물인지? 아닌지?
		//판단하자 
		//한번이라도 읽었으면 ArrayList에 있다. 없을경우 hit수 증가
		boolean chk = false;
		//읽은 게시물들이 모두 모여있는 r_list에서 
		//지금 가져온 vo의 기본키의 값과 같은 것이 있는지 알아내기 위한 반복 문!!
		
		for (BbsVO bvo : r_list) {
				//받는 타입이 String이라 equals
			if(bvo.getB_idx().equals(b_idx) ) {
				//트루가 떨어져 같은 게시물을 찾은경우(읽었던 게시물) 조회수 증가를 하지 않도록 해야함
				chk = true;
				break; //가장 가까운 반복문 탈출
			}
		}
		//반복문 들어와서 true 떨어져서 나왔는지 다 돌고나서 나왔는지
		//그래서 boolean chk = false;
		
		//반복문을 break문에 의해 탈출한 것인지? 아닌지? 판단!
		//(chk의 값이 false일때만 조회수증가)
		if(!chk) {//chk == false
		
			//조회수 증가!!
			bbsDao.hit(b_idx);
			//vo를 미리 받아와서 vo는 여전히 증가되지 않은 조회수를
			//가진다.
			String h = vo.getHit();
			int hit = Integer.parseInt(h);
			++hit;
			
			vo.setHit(String.valueOf(hit));
			
			//r_list에 vo를 저장!!
			r_list.add(vo);
			//세션에 r_list 저장
			session.setAttribute("read_list", r_list);
		}
		
		//view.jsp로 이동할 준비
		ModelAndView mv = new ModelAndView();
		
		if (vo != null) 
			mv.addObject("vo", vo);
		
		mv.addObject("nowPage", nowPage);
		mv.addObject("ch", ch);
		
		mv.setViewName("view");
		return mv;
		
	}
}
