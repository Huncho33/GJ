package com.onestop.GJ.apply.back.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyBackDAO {

	int insertResult(Map articleMap) throws DataAccessException;

	void insertNewFile(Map articleMap) throws DataAccessException;

	ApplyBackVO findAll(String member_id);

	ApplyBackVO findNo(int ba_no);

	MemberVO modifyMember(MemberVO member);

}
