package com.onestop.GJ.admin.apply.mon.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMonApplyService {

	Map listMembers(Map pagingMap) throws DataAccessException;

	Map searchMemberList(Map pagingMap) throws Exception;

	Map viewApplyMember(int mo_no, String member_id) throws Exception;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	Map joinTable(Map pagingMap);

	ApplyMonVO modifyAdminMon(Map membersMap) throws Exception;

	ApplyMonVO modifyAdminMonPay(Map membersMap) throws Exception;

	// 신청 회원정보 조회

}