package com.onestop.GJ.apply.mon23.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

public interface ApplyMonService {

	ApplyMonVO selectResult(Map resultMap) throws Exception;


	int addResult(Map mo_no) throws Exception;

	List findAll(String id);


	ApplyMonVO findNo(int no);


	


	// 신청등록
//	public void insertResult(ApplyMonVO applymonVO) throws DataAccessException;

	

	
}
