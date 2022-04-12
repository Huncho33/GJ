package com.onestop.GJ.apply.mon23.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyMonDAO {

	int insertResult(Map articleMap) throws DataAccessException;

	void insertNewFile(Map articleMap) throws DataAccessException;

	ApplyMonVO findAll(String member_id);

	ApplyMonVO findNo(int mo_no);

	MemberVO modifyMember(MemberVO member);
}