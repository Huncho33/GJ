package com.onestop.GJ.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MemberDAO {

	List selectAllMemberList() throws DataAccessException;

	int insertMember(MemberVO memberVO) throws DataAccessException;

	int deleteMember(String id) throws DataAccessException;

	MemberVO loginById(MemberVO memberVO) throws DataAccessException;

}