package com.onestop.GJ.admin.apply.rent.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminRentApplyService {

	Map listMembers(Map pagingMap) throws DataAccessException;

	Map searchMemberList(Map pagingMap) throws Exception;

	Map viewApplyMember(int rent_no, String member_id) throws Exception;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	Map joinTable(Map pagingMap);

	ApplyRentVO modifyAdminRent(Map membersMap) throws Exception;

	ApplyRentVO modifyAdminRentPay(Map membersMap) throws Exception;


}