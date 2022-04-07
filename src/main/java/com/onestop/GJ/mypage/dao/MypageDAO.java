package com.onestop.GJ.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnFileVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
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

	List selectMonthApplyList(String member_id) throws Exception;

	List selectRentApplyList(String member_id) throws Exception;

	List selectRentReturnApplyList(String member_id) throws Exception;

	List selectBackApplyList(String member_id) throws Exception;

	List selectShareApplyList(String member_id) throws Exception;

	ApplyMonVO selectViewMonthApply(String member_id) throws Exception;
	
	ApplyRentReturnVO selectViewRetApply(String member_id) throws Exception;

	ApplyRentVO selectViewRentApply(String member_id) throws Exception;

	ApplyBackVO selectViewBackApply(String member_id) throws Exception;

	ApplyShareVO selectViewShareApply(String member_id) throws Exception;

	List selectMonthFileList(int mo_no) throws Exception;

	void removeApply(int mo_no) throws DataAccessException;

	void updateImageFile(Map ViewMonthMap) throws DataAccessException;

	void updateApply(Map ViewMonthMap) throws DataAccessException;

	void insertModNewImage(Map articleMap) throws DataAccessException;

	List selectRentFileList(int rent_no) throws Exception;

	List selectRetFileList(int ret_no) throws Exception;

	List selectBackFileList(int ba_no) throws Exception;

	List selectShareFileList(int sh_no) throws Exception;

	void removeRentApply(int rent_no) throws DataAccessException;

	void removeRetApply(int ret_no) throws DataAccessException;

	void removeBackApply(int ba_no) throws DataAccessException;

	void removeShareApply(int sh_no) throws DataAccessException;

	void updateRentApply(Map ViewRentMap) throws DataAccessException;

	void updateRentFile(Map ViewRentMap) throws DataAccessException;

	void insertModNewRentFile(Map ViewRetMap) throws DataAccessException;

	void updateRetApply(Map ViewRetMap) throws DataAccessException;

	void insertModNewRetFile(Map ViewRetMap) throws DataAccessException;

	void updateRetFile(Map ViewRetMap) throws DataAccessException;

	void updateBackApply(Map ViewBackMap) throws DataAccessException;

	void updateBackFile(Map ViewBackMap) throws DataAccessException;

	void insertModNewBackFile(Map articleMap) throws DataAccessException;

	void updateShareApply(Map ViewShareMap) throws DataAccessException;

	void updateShareFile(Map ViewShareMap) throws DataAccessException;

	void insertModNewShareFile(Map articleMap) throws DataAccessException;

	void modifyAlarm(Map ViewMap) throws Exception;


	

	



}
