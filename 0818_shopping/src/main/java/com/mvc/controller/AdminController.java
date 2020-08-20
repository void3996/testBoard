package com.mvc.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.domain.CategoryVO;
import com.mvc.domain.GoodsVO;
import com.mvc.domain.GoodsViewVO;
import com.mvc.service.AdminService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	//log4j 를 이용하여 console 창에 log가 찍히게 한다 오류발생 대처 편리
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService adminService;
	
	//관리자 화면
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void getIndex() throws Exception{
		logger.info("get index");
	}
	
	//상품 등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception{
		logger.info("get goods register");
		
		//CategoryVO형태의 List 변수 category를 선언하고 
		List<CategoryVO> category = null;
		//adminService.category 호출한뒤 결과값을 category에 입력
		category = adminService.category();
		//JSONArray를 이용해 category JSON 타입으로 변경한뒤
		//"category" 라는 명칭으로 모델에 추가
		
		model.addAttribute("category", JSONArray.fromObject(category));
		//getGoodsRegister method가 호출될 때 모델을 jsp에 넘겨서 사용할 수 있다
	}
	//상품등록 post
	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsVO vo) throws Exception{
		adminService.register(vo);
		
		return "redirect:/admin/index";
	}
	//상품조회 get
	@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
	public void getGoodsList(Model model) throws Exception{
		logger.info("get goods list");
		
		List<GoodsVO> list = adminService.goodslist();
		
		model.addAttribute("list", list);
	}
	//상품보기 get
	@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
	//getGoodsview(@RequestParam("n")은 URL 주소에서 "n"의 값을 찾아서 
	//int gdsNum에 전달한다. 
	public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception{
		logger.info("get goods view");
		
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		
		model.addAttribute("goods", goods);
	}
	//상품 수정
	@RequestMapping(value = "/goods/modify", method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception{
		logger.info("get goods modify");
		
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		model.addAttribute("goods", goods);
		
		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	//상품 수정
	@RequestMapping(value = "/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo) throws Exception{
		logger.info("post goods modify");
		
		adminService.goodsModify(vo);
		
		return "redirect:/admin/index";
	}

}
