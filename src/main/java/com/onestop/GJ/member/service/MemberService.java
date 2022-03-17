package com.onestop.GJ.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MemberService {

	List listMembers() throws DataAccessException;

	public void addMember(MemberVO memberVO) throws DataAccessException;
	int removeMember(String id) throws DataAccessException;

	MemberVO login(MemberVO memberVO) throws Exception;

	String overlapped(String id) throws Exception;
// pw ã�� ���� ���� ������
	void send_PwMail(MemberVO member) throws Exception;
// pw ã�� ����
	void find_pw(HttpServletResponse response, MemberVO member) throws Exception;
// ID ã��
	MemberVO findId_hp(MemberVO memberVO) throws Exception;
	
}
