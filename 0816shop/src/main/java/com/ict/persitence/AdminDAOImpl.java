package com.ict.persitence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ict.domain.CategoryVO;
import com.ict.domain.GoodsAttachVO;
import com.ict.domain.GoodsVO;
import com.ict.domain.GoodsViewVO;
import com.ict.domain.MemberVO;
import com.ict.domain.OrderListVO;
import com.ict.domain.OrderVO;
import com.ict.domain.ReplyListVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	private SqlSession sql;
	
	// 매퍼 
	private static String namespace = "com.ict.mappers.adminMapper";
	private static String namespace2 = "com.ict.mappers.BoardAttachMapper";

	// 카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		return sql.selectList(namespace + ".category");
	}
	
	// 상품등록
	@Override
	public void register(GoodsVO vo) throws Exception {
		sql.insert(namespace + ".register", vo);

	}
	// 이미지 등록
	@Override
	public void registerImage(GoodsAttachVO vo) throws Exception {
		sql.insert(namespace2+ ".insert", vo);
		
	}
	// 상품목록
	@Override
	public List<GoodsViewVO> goodslist() throws Exception {
		return sql.selectList(namespace + ".goodslist");
	}
	// 이미지 리스트
	@Override
	public List<GoodsViewVO> goodsImagesList(int gdsNum) throws Exception {
		
		return sql.selectList(namespace + ".goodsImagesList",gdsNum);
	}

	// 상품조회 + 카테고리 조인
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return sql.selectOne(namespace + ".goodsView", gdsNum);
	}
	
	// 상품 수정
	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		sql.update(namespace + ".goodsModify", vo);
	}
	
	// 상품 삭제
	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		sql.delete(namespace + ".goodsDelete", gdsNum);
	}
	// 상품 삭제2
	@Override
	public void goodsDelete2(int gdsNum) throws Exception {
		sql.delete(namespace + ".goodsDelete2", gdsNum);	
	}
	// 주문 목록
	@Override
	public List<OrderVO> orderList() throws Exception {
		return sql.selectList(namespace + ".orderList");
	}

	// 특정 주문 목록 
	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		return sql.selectList(namespace + ".orderView", order);
	}
	
	// 배송 상태
	@Override
	public void delivery(OrderVO order) throws Exception {
		sql.update(namespace + ".delivery", order);
	}
	
	// 상품 수량 조절
	@Override
	public void changeStock(GoodsVO goods) throws Exception {		
		sql.update(namespace + ".changeStock", goods);
	}
	
	// 댓글 목록 보기(관리자)
	@Override
	public List<ReplyListVO> allReply() throws Exception {
		return sql.selectList(namespace + ".allReply");
	}

	// 댓글 삭제 (관리자)
	@Override
	public void deleteReply(int repNum) throws Exception {
		sql.delete(namespace + ".deleteReply", repNum);
	}

	// 모든 회원 목록
	@Override
	public List<MemberVO> memlist() throws Exception {
		return sql.selectList(namespace + ".memlist");
	}
	
	// 관리자 목록
	@Override
	public List<MemberVO> admlist() throws Exception {
		return sql.selectList(namespace + ".admlist");
	}

	

	
} 