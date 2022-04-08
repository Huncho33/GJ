package com.onestop.GJ.apply.mon23.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

public interface ApplyMonDAO {

	ApplyMonVO selectResult(Map resultMap) throws DataAccessException;
	// 신청 등록
	void insertNewFile(Map applymonMap) throws DataAccessException;
	int insertResult(Map mo_no) throws DataAccessException;
	int insertResultNO();
	ApplyMonVO findAll(String member_id);
	ApplyMonVO findNo(int mo_no);
	
	






}