package com.onestop.GJ.apply.rent_return.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;

public interface ApplyRentReturnDAO {

	
	ApplyRentReturnVO selectResult(Map resultMap) throws DataAccessException;
	// 신청 등록
	void insertNewFile(Map applyrentReturnMap) throws DataAccessException;
	int insertResult(Map return_no) throws DataAccessException;
	int insertResultNO();
	ApplyRentReturnVO findAll(String member_id);
	ApplyRentReturnVO findNo(int return_no);
}