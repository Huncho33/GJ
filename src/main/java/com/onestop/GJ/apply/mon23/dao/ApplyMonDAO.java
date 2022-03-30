package com.onestop.GJ.apply.mon23.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyMonDAO {

	ApplyMonVO selectResult(Map resultMap) throws DataAccessException;
	// ��û ���
	void insertNewFile(Map applymonMap) throws DataAccessException;
	int insertResult(Map mo_no) throws DataAccessException;
	int insertResultNO();
	List findAll(String member_id);
	ApplyMonVO findNo(int mo_no);
	
	






}