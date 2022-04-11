package com.onestop.GJ.admin.apply.mon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMonApplyDAO {

	//��û ȸ������ ��ȸ
	List selectAllMemberList(Map pagingMap) throws DataAccessException;

	int selectTotMembers() throws DataAccessException;

	int selectSearchTotMembers(Map pagingMap) throws DataAccessException;


	MemberVO selectMemberId(String member_id) throws DataAccessException;

	int insertMember(MemberVO memberVO) throws DataAccessException;


	int selectTotApply() throws DataAccessException;

	List joinTable(Map pagingMap);

	List selectApplyBySearchMember(Map pagingMap) throws DataAccessException;

// �˻�(��û)	
	int selectSearchTotApply(Map pagingMap) throws DataAccessException;

	MemberVO selectMember(int mo_no) throws DataAccessException;

	int selectDetailApply() throws DataAccessException;

	MemberVO selectIdMember(String member_id) throws DataAccessException;

	MemberVO selectMonApplyView(int mo_no) throws DataAccessException;

	ApplyMonVO selectApplyMon(int mo_no) throws DataAccessException;

	List selectApplyMonFile(int mo_no) throws DataAccessException;

	void modifyAdminMon(Map membersMap) throws Exception;

	void modifyAdminMonPay(Map membersMap) throws Exception;


}