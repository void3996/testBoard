/*
 * package com.ict.bbs;
 * 
 * import java.util.Hashtable; import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import mybatis.dao.BbsDAO; import mybatis.vo.BbsVO;
 * 
 * @Controller public class UpdateController {
 * 
 * @Autowired private BbsDAO bbsDao; //수정 뷰
 * 
 * @RequestMapping(value = "/edit", method = RequestMethod.POST) public
 * ModelAndView updateView(String b_idx, String nowPage, String bname) {
 * Map<String, String> map = new Hashtable<String, String>();
 * 
 * map.put("b_idx", b_idx); map.put("bname", bname);
 * 
 * BbsVO vo = bbsDao.getBbs(map);
 * 
 * ModelAndView mv = new ModelAndView();
 * 
 * if(vo != null) mv.addObject("vo", vo);
 * 
 * mv.addObject("nowPage", nowPage); mv.setViewName("edit");
 * 
 * return mv; } //수정
 * 
 * @RequestMapping(value = "/update") public void edit() {
 * 
 * } }
 */