package com.onestop.GJ.admin.member.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.admin.member.vo.AdminMemberVO;

public interface AdminMemberService {

	Map listMembers(Map pagingMap) throws DataAccessException;

	int removeMember(String member_id) throws DataAccessException;


	int addMember(AdminMemberVO member) throws DataAccessException;

	Map searchMemberList(Map pagingMap) throws Exception;

	Map viewMember(String member_id) throws Exception;

	AdminMemberVO modifyMember_adm(Map membersMap) throws Exception;





	


}
