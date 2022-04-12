package com.onestop.GJ.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MemberDAO {

	void insertMember(MemberVO memberVO) throws DataAccessException;

	MemberVO loginById(MemberVO memberVO) throws DataAccessException;

	void last_logOn(String member_id) throws DataAccessException;

	String selectOverlappedID(String id) throws DataAccessException;

	MemberVO certHp_Id(MemberVO memberVO) throws DataAccessException;

	int update_pw(MemberVO member) throws Exception;

	List selectNotiList() throws Exception;

	List selectDataList() throws Exception;

	void insertVisit(Map visitMap);

	int getVisitTotCnt(Map visitMap);

	MemberVO SearchById(MemberVO memberVO) throws DataAccessException;


	


}