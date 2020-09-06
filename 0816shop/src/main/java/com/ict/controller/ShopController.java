package com.ict.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.domain.CartListVO;
import com.ict.domain.CartVO;
import com.ict.domain.GoodsViewVO;
import com.ict.domain.MemberVO;
import com.ict.domain.OrderDetailVO;
import com.ict.domain.OrderListVO;
import com.ict.domain.OrderVO;
import com.ict.domain.ReplyListVO;
import com.ict.domain.ReplyVO;
import com.ict.service.ShopService;

@Controller
@RequestMapping("/shop/*")
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Inject
	ShopService service;
	
	// 사진 이미지 받기(ajax)
	@RequestMapping(value = "/getImages", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<GoodsViewVO>> getImages(@RequestBody GoodsViewVO vo) throws Exception {
		logger.info("get goodsImagesList");
		Map<String, String> map = new Hashtable<String, String>();
		// 파라미터 값 받음 46
		int gdsNum = vo.getGdsNum();
		System.out.println(gdsNum);

		List<GoodsViewVO> goodsImagesList = service.goodsImagesList(gdsNum);
		
		return new ResponseEntity<List<GoodsViewVO>>(goodsImagesList, HttpStatus.OK);
		
	}
				
	// 카테고리별 상품 리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(@RequestParam("c") int cateCode,
						@RequestParam("l") int level, Model model) throws Exception {
		logger.info("get list");
		
		List<GoodsViewVO> list = null;
		list = service.list(cateCode, level);
	
		model.addAttribute("list", list);
		
	}
	
	// 상품 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("n") int gdsNum, Model model) throws Exception {
		logger.info("get view");
		
		GoodsViewVO view = service.goodsView(gdsNum);
		model.addAttribute("view", view);
		
	}
	
	// 상품 댓글 작성
	@ResponseBody
	@RequestMapping(value = "/view/registReply", method = RequestMethod.POST)
	public void registReply(ReplyVO reply, HttpSession session) throws Exception {
		logger.info("regist reply");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		reply.setMemId(member.getMemId());
		
		service.registReply(reply);
	}	
	
	// 상품 댓글 목록
	@ResponseBody
	@RequestMapping(value = "/view/replyList", method = RequestMethod.GET)
	public List<ReplyListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
		logger.info("get reply list");
				
		List<ReplyListVO> reply = service.replyList(gdsNum);
		
		return reply;
	}
	
	// 상품 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/view/deleteReply", method = RequestMethod.POST)
	public int getReplyList(ReplyVO reply,  HttpSession session) throws Exception {
		logger.info("post delete reply");

		// 정상작동 여부를 확인하기 위한 변수
		int result = 0;
		
		MemberVO member = (MemberVO)session.getAttribute("member");  // 현재 로그인한  member 세션을 가져옴
		String memId = service.idCheck(reply.getRepNum());  // 소감(댓글)을 작성한 사용자의 아이디를 가져옴
				
		// 로그인한 아이디와, 소감을 작성한 아이디를 비교
		if(member.getMemId().equals(memId)) {
			
			// 로그인한 아이디가 작성한 아이디와 같다면
			reply.setMemId(member.getMemId());  // reply에 memId 저장
			service.deleteReply(reply);  // 서비스의 deleteReply 메서드 실행
			
			// 결과값 변경
			result = 1;
		}
		
		// 정상적으로 실행되면 소감 삭제가 진행되고, result값은 1이지만
		// 비정상적으로 실행되면 소감 삭제가 안되고, result값이 0
		return result;	
	}
	
	// 상품 댓글 수정
	@ResponseBody
	@RequestMapping(value = "/view/modifyReply", method = RequestMethod.POST)
	public int modifyReply(ReplyVO reply, HttpSession session) throws Exception {
		logger.info("modify reply");
		
		int result = 0;
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = service.idCheck(reply.getRepNum());
		
		if(member.getMemId().equals(memId)) {
			
			reply.setMemId(member.getMemId());
			service.modifyReply(reply);
			result = 1;
		}
		
		return result;
		
	}
	
	// 카트 담기
	@ResponseBody
	@RequestMapping(value = "/view/addCart", method = RequestMethod.POST)
	public int addCart(CartListVO cart, HttpSession session) throws Exception {
		
		int result = 0;
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		if(member != null) {
			cart.setMemId(member.getMemId());
			service.addCart(cart);
			result = 1;
		}
		
		return result;
	}
		
	// 카트 목록
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public void getCartList(HttpSession session, Model model) throws Exception {
		logger.info("get cart list");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		List<CartListVO> cartList = service.cartList(memId);
		
		model.addAttribute("cartList", cartList);
	}
	
	// 카트 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
	public int deleteCart(HttpSession session, @RequestParam(value = "chbox[]") List<String> chArr, CartVO cart) throws Exception {
		logger.info("delete cart");
			
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
			
		int result = 0;
		int cartNum = 0;		
			
		// 로그인 여부 구분
		if(member != null) {
			cart.setMemId(memId);
				
			for(String i : chArr) {  // 에이젝스에서 받은 chArr의 갯수만큼 반복
				cartNum = Integer.parseInt(i);  // i번째 데이터를 cartNum에 저장
				cart.setCartNum(cartNum);
				service.deleteCart(cart);
			}			
			result = 1;
		}		
		return result;		
	}
	
	// 주문
	@RequestMapping(value = "/cartList", method = RequestMethod.POST)
	public String order(HttpSession session, OrderVO order, OrderDetailVO orderDetail) throws Exception {
		logger.info("order");
		
		MemberVO member = (MemberVO)session.getAttribute("member");		
		String memId = member.getMemId();
		
		// 캘린더 호출
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);  // 연도 추출
		String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);  // 월 추출
		String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));  // 일 추출
		String subNum = "";  // 랜덤 숫자를 저장할 문자열 변수
		
		for(int i = 1; i <= 6; i ++) {  // 6회 반복
			subNum += (int)(Math.random() * 10);  // 0~9까지의 숫자를 생성하여 subNum에 저장
		}
		
		String orderId = ymd + "_" + subNum;  // [연월일]_[랜덤숫자] 로 구성된 문자
		
		order.setOrderId(orderId);
		order.setMemId(memId);
			
		service.orderInfo(order);
		
		orderDetail.setOrderId(orderId);			
		service.orderInfo_Details(orderDetail);
		
		// 주문 테이블, 주문 상세 테이블에 데이터를 전송하고, 카트 비우기
		service.cartAllDelete(memId);
		
		return "redirect:/shop/orderList";		
	}
	
	// 주문 목록
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public void getOrderList(HttpSession session, OrderVO order, Model model) throws Exception {
		logger.info("get order list");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		order.setMemId(memId);
		
		List<OrderVO> orderList = service.orderList(order);
		
		model.addAttribute("orderList", orderList);
	}
	
	// 주문 상세 목록
	@RequestMapping(value = "/orderView", method = RequestMethod.GET)
	public void getOrderList(HttpSession session,
							@RequestParam("n") String orderId,
							OrderVO order, Model model) throws Exception {
		logger.info("get order view");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		order.setMemId(memId);
		order.setOrderId(orderId);
		
		List<OrderListVO> orderView = service.orderView(order);
		
		model.addAttribute("orderView", orderView);
	}

} 