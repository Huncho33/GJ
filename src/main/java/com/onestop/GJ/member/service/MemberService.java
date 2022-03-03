package com.onestop.GJ.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MemberService {

	List listMembers() throws DataAccessException;

//	int addMember(MemberVO memberVO) throws DataAccessException;
	public void addMember(MemberVO memberVO) throws DataAccessException;
	int removeMember(String id) throws DataAccessException;

	MemberVO login(MemberVO memberVO) throws Exception;

	String overlapped(String id) throws Exception;


	
}
