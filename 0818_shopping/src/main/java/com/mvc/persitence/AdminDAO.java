package com.mvc.persitence;

import java.util.List;

import com.mvc.domain.CategoryVO;

public interface AdminDAO {
	//카테고리
	public List<CategoryVO> category() throws Exception;
}
