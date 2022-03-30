package com.onestop.GJ.apply.back.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;

public interface ApplyBackDAO {

	ApplyBackVO selectResult(Map resultMap) throws DataAccessException;

	int insertResult(Map articleMap) throws DataAccessException;

	void insertNewFile(Map articleMap) throws DataAccessException;

	List findAll(String member_id);

	ApplyBackVO findNo(int ba_no);

}
