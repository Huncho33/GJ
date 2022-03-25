package com.onestop.GJ.admin.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.admin.member.vo.AdminMemberVO;

public interface AdminMemberDAO {


	int insertMember(AdminMemberVO adminMemberVO) throws DataAccessException;

	int deleteMember(String member_id) throws DataAccessException;

	List selectAllMemberList(Map pagingMap) throws DataAccessException;

	int selectTotMembers() throws DataAccessException;

	List selectMemberListBySearchMember(Map pagingMap) throws DataAccessException;

	int selectSearchTotMembers(Map pagingMap) throws DataAccessException;

	AdminMemberVO selectMember(String member_id) throws DataAccessException;

}
