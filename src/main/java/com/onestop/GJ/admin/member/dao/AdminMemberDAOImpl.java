package com.onestop.GJ.admin.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.member.vo.MemberVO;

@Repository("adminMemberDAO")
public class AdminMemberDAOImpl implements AdminMemberDAO {
	@Autowired
	private SqlSession sqlSession;

	// 회원 리스트 조회
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminMember.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// 검색창
	@Override
	public List selectMemberListBySearchMember(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = sqlSession.selectList("mapper.adminMember.selectMemberListBySearchMember",
				pagingMap);
		return membersList;
	}

	// 총 회원수
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminMember.selectTotMembers");
		return totMembers;

	}

	// 검색창 총 검색수
	@Override
	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
		int searchTotMembers = sqlSession.selectOne("mapper.adminMember.selectSearchTotMembers", pagingMap);
		return searchTotMembers;
	}

	//회원 상세보기
	@Override
	public MemberVO selectMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminMember.selectMember", member_id);
	}

	//회원 아이디 셀렉트
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminMember.selectMember", member_id);
	}
	
	//회원 추가
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.adminMember.insertMember_adm", memberVO);
		return result;
	}

	// 회원 정보 수정
	public void updateMember_adm(Map membersMap) throws DataAccessException {
		sqlSession.update("mapper.adminMember.updateMember_adm", membersMap);
	}
	
	//회원 삭제
	public void deleteMember(String member_id) throws DataAccessException {
		sqlSession.delete("mapper.adminMember.deleteMember", member_id);
	}

}
