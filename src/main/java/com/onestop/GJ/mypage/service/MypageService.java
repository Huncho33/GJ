package com.onestop.GJ.mypage.service;

import java.util.Map;

import com.onestop.GJ.member.vo.MemberVO;

public interface MypageService {
	
	MemberVO pwdConfirm(MemberVO memberVO) throws Exception;
	
	int deleteMember(String member_id) throws Exception;
	
	boolean checkPwd(String member_id, String member_pw) throws Exception;

	MemberVO modifyMember(Map memberMap) throws Exception;

//	void updateMember(MemberVO memberVO) throws Exception;

//	MemberVO selectMemberById(String member_id) throws Exception;

//	int updateMember(MemberVO memberVO) throws Exception;
	

}