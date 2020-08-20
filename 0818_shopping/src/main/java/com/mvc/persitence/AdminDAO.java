package com.mvc.persitence;

import java.util.List;

import com.mvc.domain.CategoryVO;
import com.mvc.domain.GoodsVO;
import com.mvc.domain.GoodsViewVO;

public interface AdminDAO {
	//카테고리
	public List<CategoryVO> category() throws Exception;
	
	//상품 등록
	public void register(GoodsVO vo) throws Exception;
	//상품 목록
	public List<GoodsVO> goodslist() throws Exception;
	//상품 조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	//상품 수정
	public void goodsModify(GoodsVO vo) throws Exception;
}
