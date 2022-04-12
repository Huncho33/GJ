package com.onestop.GJ.admin.apply.mon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMonApplyDAO {

	List selectAllMemberList(Map pagingMap) throws DataAccessException;

	List selectApplyBySearchMember(Map pagingMap) throws DataAccessException;

	int selectSearchTotApply(Map pagingMap) throws DataAccessException;

	int selectTotMembers() throws DataAccessException;

	MemberVO selectIdMember(String member_id) throws DataAccessException;

	ApplyMonVO selectApplyMon(int mo_no) throws DataAccessException;

	List selectApplyMonFile(int mo_no) throws DataAccessException;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	List joinTable(Map pagingMap);

	int selectTotApply() throws DataAccessException;

	int selectDetailApply() throws DataAccessException;

	void modifyAdminMon(Map membersMap) throws Exception;

	void modifyAdminMonPay(Map membersMap) throws Exception;
}