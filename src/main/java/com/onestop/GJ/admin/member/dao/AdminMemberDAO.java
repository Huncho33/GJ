package com.onestop.GJ.admin.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMemberDAO {


	int insertMember(MemberVO memberVO) throws DataAccessException;


	List selectAllMemberList(Map pagingMap) throws DataAccessException;

	int selectTotMembers() throws DataAccessException;

	List selectMemberListBySearchMember(Map pagingMap) throws DataAccessException;

	int selectSearchTotMembers(Map pagingMap) throws DataAccessException;

	MemberVO selectMember(String member_id) throws DataAccessException;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

}
