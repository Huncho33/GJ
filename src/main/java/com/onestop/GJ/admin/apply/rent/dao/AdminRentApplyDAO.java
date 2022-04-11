package com.onestop.GJ.admin.apply.rent.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminRentApplyDAO {

	List selectAllMemberList(Map pagingMap) throws DataAccessException;

	List selectApplyBySearchMember(Map pagingMap) throws DataAccessException;

	int selectSearchTotApply(Map pagingMap) throws DataAccessException;

	int selectTotMembers() throws DataAccessException;

	int selectSearchTotMembers(Map pagingMap) throws DataAccessException;

	MemberVO selectMember(int rent_no) throws DataAccessException;

	MemberVO selectRentApplyView(int rent_no) throws DataAccessException;

	MemberVO selectIdMember(String member_id) throws DataAccessException;

	ApplyRentVO selectApplyRent(int rent_no) throws DataAccessException;

	List selectApplyRentFile(int rent_no) throws DataAccessException;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	int insertMember(MemberVO memberVO) throws DataAccessException;

	List joinTable(Map pagingMap);

	int selectTotApply() throws DataAccessException;

	int selectDetailApply() throws DataAccessException;

	void modifyAdminRent(Map membersMap) throws Exception;

	void modifyAdminRentPay(Map membersMap) throws Exception;



}