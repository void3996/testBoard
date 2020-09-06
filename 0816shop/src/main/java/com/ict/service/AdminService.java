package com.ict.service;

import java.util.List;


import com.ict.domain.CategoryVO;
import com.ict.domain.GoodsAttachVO;
import com.ict.domain.GoodsVO;
import com.ict.domain.GoodsViewVO;
import com.ict.domain.MemberVO;
import com.ict.domain.OrderListVO;
import com.ict.domain.OrderVO;
import com.ict.domain.ReplyListVO;

public interface AdminService {

	// 카테고리 
	public List<CategoryVO> category() throws Exception;
	
	// 상품등록
	public void register(GoodsVO vo) throws Exception;
	
	// 이미지 등록
	public void registerImage(GoodsAttachVO vo) throws Exception;
	
	// 상품목록
	public List<GoodsViewVO> goodslist() throws Exception;
	
	// 이미지 목록
	public List<GoodsViewVO> goodsImagesList(int gdsNum) throws Exception;
	
	// 상품조회 + 카테고리 조인
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	// 상품 수정
	public void goodsModify(GoodsVO vo) throws Exception;
	
	// 상품 삭제
	public void goodsDelete(int gdsNum) throws Exception;
	
	// 상품 삭제2
	public void goodsDelete2(int gdsNum) throws Exception;
		
	// 주문 목록
	public List<OrderVO> orderList() throws Exception;
	
	// 특정 주문 목록
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	
	// 배송 상태
	public void delivery(OrderVO order) throws Exception;
	
	// 상품 수량 조절
	public void changeStock(GoodsVO goods) throws Exception;
	
	// 댓글 목록 보기(관리자)
	public List<ReplyListVO> allReply() throws Exception;
	
	// 댓글 삭제 (관리자)
	public void deleteReply(int repNum) throws Exception;
	
	// 모든 회원 목록
	public List<MemberVO> memlist() throws Exception;
	
	// 관리자 목록
	public List<MemberVO> admlist() throws Exception;
	
} 