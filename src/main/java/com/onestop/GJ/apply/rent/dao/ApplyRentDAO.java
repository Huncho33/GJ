package com.onestop.GJ.apply.rent.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.rent.vo.ApplyRentVO;

public interface ApplyRentDAO {
	void insertNewFile(Map applyrentMap) throws DataAccessException;
	int insertResult(Map rent_no) throws DataAccessException;
	int insertResultNO();
	ApplyRentVO findAll(String member_id);
	ApplyRentVO findNo(int rent_no);
}
