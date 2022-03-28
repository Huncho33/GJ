package com.onestop.GJ.admin.member.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.admin.member.vo.AdminMemberVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMemberService {
	Map listMembers(Map pagingMap) throws DataAccessException;
	
	Map searchMemberList(Map pagingMap) throws Exception;

	Map viewMember(String member_id) throws Exception;

	int addMember(AdminMemberVO member) throws DataAccessException;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	MemberVO modifyMember_adm(Map membersMap) throws Exception;

	int removeMember(String member_id) throws DataAccessException;

	

	




	


}
