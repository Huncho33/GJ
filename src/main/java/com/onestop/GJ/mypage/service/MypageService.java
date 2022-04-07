package com.onestop.GJ.mypage.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnFileVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
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

	List<ApplyMonVO> selectMonthApplyList(String member_id) throws Exception;

	List<ApplyRentVO> selectRentApplyList(String member_id) throws Exception;

	List<ApplyRentReturnVO> selectRentReturnApplyList(String member_id) throws Exception;

	List<ApplyBackVO> selectBackApplyList(String member_id) throws Exception;

	List<ApplyBackVO> selectShareApplyList(String member_id) throws Exception;

	Map selectViewMonthApply(String member_id) throws Exception;
	
	Map selectViewRentApply(String member_id) throws Exception;

	Map selectViewRetApply(String member_id) throws Exception;

	Map selectViewBackApply(String member_id) throws Exception;

	Map selectViewShareApply(String member_id) throws Exception;

	void removeApply(int mo_no) throws Exception;

	void modApply(Map ViewMonthMap) throws Exception;

	void removeRentApply(int rent_no) throws Exception;

	void removeRetApply(int ret_no) throws Exception;

	void removeBackApply(int ba_no) throws Exception;

	void removeShareApply(int sh_no) throws Exception;

	void modShareApply(Map ViewShareMap) throws Exception;

	void modBackApply(Map ViewBackMap) throws Exception;

	void modRetApply(Map ViewRetMap) throws Exception;

	void modRentApply(Map ViewRentMap) throws Exception;

	void modifyAlarm(Map ViewMap) throws Exception;



	


}