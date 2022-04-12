package com.onestop.GJ.admin.apply.share.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.admin.apply.share.vo.AdminShareApplyVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminShareApplyDAO {

	List selectAllMemberList(Map pagingMap) throws DataAccessException;

	List selectApplyBySearchMember(Map pagingMap) throws DataAccessException;

	int selectSearchTotApply(Map pagingMap) throws DataAccessException;

	int selectTotMembers() throws DataAccessException;

	int selectSearchTotMembers(Map pagingMap) throws DataAccessException;

	MemberVO selectMember(int sh_no) throws DataAccessException;

	MemberVO selectShareApplyView(int sh_no) throws DataAccessException;

	MemberVO selectIdMember(String member_id) throws DataAccessException;

	
	List selectApplyShareFile(int sh_no) throws DataAccessException;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	List joinTable(Map pagingMap);

	int selectTotApply() throws DataAccessException;

	int selectDetailApply() throws DataAccessException;

	void modifyAdminShare(Map membersMap) throws Exception;

	void modifyAdminSharePay(Map membersMap) throws Exception;

	AdminShareApplyVO selectApplyShare(int sh_no) throws DataAccessException;



}