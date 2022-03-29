package com.onestop.GJ.mypage.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.board.QNA.vo.QnaVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface MypageService {
	
	MemberVO pwdConfirm(MemberVO memberVO) throws Exception;
	
	int deleteMember(String member_id) throws Exception;
	
	boolean checkPwd(String member_id, String member_pw) throws Exception;

	MemberVO modifyMember(Map memberMap) throws Exception;

	List<QnaVO> selectMonthQnasList(String member_id) throws Exception;

	List<QnaVO> selectRentQnasList(String member_id) throws Exception;

	List<QnaVO> selectReturnQnasList(String member_id) throws Exception;

	List<QnaVO> selectWeddingQnasList(String member_id) throws Exception;

	List<QnaVO> selectShareQnasList(String member_id) throws Exception;

	List<QnaVO> selectMyBoardList(String member_id) throws Exception;


}