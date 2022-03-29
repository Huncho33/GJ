package com.onestop.GJ.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface MypageDAO {
	
	MemberVO pwdConfirm(MemberVO memberVO) throws DataAccessException;
	
	int deleteMember(String member_id) throws DataAccessException;
	
	boolean checkPwd(String member_id, String member_pw) throws DataAccessException;

	MemberVO selectMemberById(String id) throws DataAccessException;

	void updateMember(Map memberMap) throws DataAccessException;

	List selectMonthQnasList(String member_id) throws Exception;

	List selectRentQnasList(String member_id) throws Exception;

	List selectReturnQnasList(String member_id) throws Exception;

	List selectWeddingQnasList(String member_id) throws Exception;

	List selectShareQnasList(String member_id) throws Exception;

	List selectMyBoardList(String member_id) throws Exception;


}
