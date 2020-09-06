package com.ict.service;

import java.util.List;

import com.ict.domain.CartListVO;
import com.ict.domain.CartVO;
import com.ict.domain.GoodsViewVO;
import com.ict.domain.OrderDetailVO;
import com.ict.domain.OrderListVO;
import com.ict.domain.OrderVO;
import com.ict.domain.ReplyListVO;
import com.ict.domain.ReplyVO;


public interface ShopService {
	
	// 이미지 목록
	public List<GoodsViewVO> goodsImagesList(int gdsNum) throws Exception;

	// 카테고리별 상품 리스트
	public List<GoodsViewVO> list(int cateCode, int level)  throws Exception;
	
	// 상품 조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	// 상품 댓글 작성
	public void registReply(ReplyVO reply) throws Exception;
	
	// 상품 댓글 리스트
	public List<ReplyListVO> replyList(int gdsNum) throws Exception;
	
	// 상품 댓글 삭제
	public void deleteReply(ReplyVO reply) throws Exception;
	
	// 아이디 체크
	public String idCheck(int repNum) throws Exception;
	
	// 상품 댓글 수정
	public void modifyReply(ReplyVO reply) throws Exception;
	
	// 카트 담기
	public void addCart(CartListVO cart) throws Exception;
	
	// 카트 리스트
	public List<CartListVO> cartList(String memId) throws Exception;
	
	// 카트 삭제
	public void deleteCart(CartVO cart) throws Exception;
	
	// 주문 정보
	public void orderInfo(OrderVO order) throws Exception;
	
	// 주문 상세 정보
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception;
	
	// 카트 비우기
	public void cartAllDelete(String memId) throws Exception;
	
	// 주문 목록
	public List<OrderVO> orderList(OrderVO order) throws Exception;
	
	// 특정 주문 목록
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	
} 