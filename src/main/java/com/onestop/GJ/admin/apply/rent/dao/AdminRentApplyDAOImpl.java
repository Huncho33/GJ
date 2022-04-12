package com.onestop.GJ.admin.apply.rent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("AdminRentApplyDAOImpl")
public class AdminRentApplyDAOImpl implements AdminRentApplyDAO {
	@Autowired
	private SqlSession sqlSession;

	// 신청 회원정보 조회
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminRentApply.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// 검색창(신청)
	@Override
	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
		List<ApplyRentVO> selectApplyList = sqlSession.selectList("mapper.adminRentApply.selectApplyBySearchMember",
				pagingMap);
		return selectApplyList;
	}

	// 검색창(신청) 총 검색수
	@Override
	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
		int searchTotApply = sqlSession.selectOne("mapper.adminRentApply.selectSearchTotApply", pagingMap);
		return searchTotApply;
	}

	// 총 회원수
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminRentApply.selectTotMembers");
		return totMembers;

	}

	// 검색창 총 검색수
	@Override
	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
		int searchTotMembers = sqlSession.selectOne("mapper.adminRentApply.selectSearchTotMembers", pagingMap);
		return searchTotMembers;
	}

//  회원 상세보기
	@Override
	public MemberVO selectMember(int rent_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectMember", rent_no);
	}

//  회원 상세보기
	@Override
	public MemberVO selectRentApplyView(int rent_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectRentApplyView", rent_no);
	}

	// id로 회원 정보 불러오기
	@Override
	public MemberVO selectIdMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectIdMember", member_id);
	}

	// 신청넘버로 신청정보 불러오기
	@Override
	public ApplyRentVO selectApplyRent(int rent_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectApplyRent", rent_no);
	}

	// 신청넘버로 신청첨부리스트 불러오기
	@Override
	public List selectApplyRentFile(int rent_no) throws DataAccessException {
		List<ApplyRentFileVO> rentFileList = null;
		rentFileList = sqlSession.selectList("mapper.adminRentApply.selectApplyRentFile", rent_no);
		return rentFileList;
	}

	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectMember", member_id);
	}

	// 신청 테이블과 member 테이블 join
	@Override
	public List joinTable(Map pagingMap) {
		List<ApplyRentVO> applyList = null;
		applyList = sqlSession.selectList("mapper.adminRentApply.joinTable", pagingMap);
		return applyList;

	}

	// 총 회원수
	@Override
	public int selectTotApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminRentApply.selectTotApply");
		return totApply;

	}

	// 회원 상세정보
	@Override
	public int selectDetailApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminRentApply.selectTotApply");
		return totApply;

	}

	// 신청상태 적용
	@Override
	public void modifyAdminRent(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminRentApply.modifyAdminRent", membersMap);
	}
	
	// 신청 지급 날짜 적용
	@Override
	public void modifyAdminRentPay(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminRentApply.modifyAdminRentPay", membersMap);
	}

}
