package com.onestop.GJ.email.dao;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MailDAO {

	// --hp
	MemberVO certHp_Pw(MemberVO memberVO) throws DataAccessException;

}