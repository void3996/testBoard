package com.ict.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.CategoryVO;
import com.ict.domain.GoodsAttachVO;
import com.ict.domain.GoodsVO;
import com.ict.domain.GoodsViewVO;
import com.ict.domain.MemberVO;
import com.ict.domain.OrderListVO;
import com.ict.domain.OrderVO;
import com.ict.domain.ReplyListVO;
import com.ict.persitence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO dao;

	// 카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		return dao.category();
	}
	
	// 상품등록
	@Transactional
	@Override
	public void register(GoodsVO vo) throws Exception {
		dao.register(vo);		

		System.out.println("들어가기전");
		if (vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			System.out.println("들어옴");
			return;
		}
		System.out.println("나온후");
		
		vo.getAttachList().forEach(attach -> {

			attach.setGdsNum(vo.getGdsNum());
				try {
					dao.registerImage(attach);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		});
	}
	
	// 상품목록
	@Override
	public List<GoodsViewVO> goodslist() throws Exception {
		return dao.goodslist();
	}
	
	// 상품조회 + 카테고리 조인
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return dao.goodsView(gdsNum);
	}
	
	// 이미지 리스트
	@Override
	public List<GoodsViewVO> goodsImagesList(int gdsNum) throws Exception {		
	return dao.goodsImagesList(gdsNum);
	}
		
	// 상품 수정
	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		dao.goodsModify(vo);
	}
	
	// 상품 삭제
	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		dao.goodsDelete(gdsNum);
	}
	// 상품 삭제2
	@Transactional
	@Override
	public void goodsDelete2(int gdsNum) throws Exception {
		
		
		System.out.println("======================================");
		System.out.println(gdsNum);
		//
		dao.goodsDelete(gdsNum);
		
		dao.goodsDelete2(gdsNum);
	
	}
	// 주문 목록
	@Override
	public List<OrderVO> orderList() throws Exception {
		return dao.orderList();
	}

	// 특정 주문
	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		return dao.orderView(order);
	}
	
	// 배송 상태
	@Override
	public void delivery(OrderVO order) throws Exception {
		dao.delivery(order);
	}
	
	// 상품 수량 조절
	@Override
	public void changeStock(GoodsVO goods) throws Exception {		
		dao.changeStock(goods);
	}
	
	// 댓글 목록 보기(관리자)
	@Override
	public List<ReplyListVO> allReply() throws Exception {
		return dao.allReply();
	}

	// 댓글 삭제(관리자)
	@Override
	public void deleteReply(int repNum) throws Exception {
		dao.deleteReply(repNum);
	}
	
	// 이미지 등록
	@Override
	public void registerImage(GoodsAttachVO vo) throws Exception {
		dao.registerImage(vo);		
	}

	// 모든 회원 목록
	@Override
	public List<MemberVO> memlist() throws Exception {
		return dao.memlist();
	}
	
	// 관리자 목록
	@Override
	public List<MemberVO> admlist() throws Exception {
		return dao.admlist();
	}

	
	

}