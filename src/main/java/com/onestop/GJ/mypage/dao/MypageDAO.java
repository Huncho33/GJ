package com.onestop.GJ.mypage.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MypageDAO {
	
	MemberVO pwdConfirm(MemberVO memberVO) throws DataAccessException;
	
	int deleteMember(String member_id) throws DataAccessException;
	
	boolean checkPwd(String member_id, String member_pw) throws DataAccessException;

	MemberVO selectMemberById(String id) throws DataAccessException;

	void updateMember(Map memberMap) throws DataAccessException;

//	void updateMember(MemberVO memberVO) throws DataAccessException;

//	int updateMember(MemberVO memberVO) throws DataAccessException;


}
