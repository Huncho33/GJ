package com.onestop.GJ.admin.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMemberService {

	Map listMembers(Map pagingMap) throws DataAccessException;

	int addMember(MemberVO member) throws DataAccessException;

	Map searchMemberList(Map pagingMap) throws Exception;

	Map viewMember(String member_id) throws Exception;

	MemberVO modifyMember_adm(Map membersMap) throws Exception;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	void removeMember(String member_id) throws DataAccessException;






	


}
