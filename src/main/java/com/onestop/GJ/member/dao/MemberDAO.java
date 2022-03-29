package com.onestop.GJ.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MemberDAO {

	List selectAllMemberList() throws DataAccessException;

	void insertMember(MemberVO memberVO) throws DataAccessException;

	int deleteMember(String id) throws DataAccessException;

	MemberVO loginById(MemberVO memberVO) throws DataAccessException;

	public MemberVO login(Map loginMap) throws DataAccessException;

	int update_pw(MemberVO member) throws Exception;

	String selectOverlappedID(String string) throws DataAccessException;

	MemberVO SearchById(MemberVO memberVO) throws DataAccessException;
	
	//ID Ã£±â
	MemberVO certHp_Id(MemberVO memberVO) throws DataAccessException;

	void last_logOn(String member_id) throws DataAccessException;


	


}