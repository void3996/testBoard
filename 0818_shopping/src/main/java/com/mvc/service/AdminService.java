package com.mvc.service;

import java.util.List;

import com.mvc.domain.CategoryVO;


public interface AdminService {
	//카테고리
	public List<CategoryVO> category() throws Exception;
}
