package com.onestop.GJ.admin.apply.mon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("AdminMonApplyDAOImpl")
public class AdminMonApplyDAOImpl implements AdminMonApplyDAO {
	@Autowired
	private SqlSession sqlSession;

	// 신청 회원정보 조회
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminApply.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// 검색창(신청)
	@Override
	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
		List<ApplyMonVO> selectApplyList = sqlSession.selectList("mapper.adminApply.selectApplyBySearchMember",
				pagingMap);
		return selectApplyList;
	}

	// 검색창(신청) 총 검색수
	@Override
	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
		int searchTotApply = sqlSession.selectOne("mapper.adminApply.selectSearchTotApply", pagingMap);
		return searchTotApply;
	}

	// 총 회원수
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminApply.selectTotMembers");
		return totMembers;

	}

	// id로 회원 정보 불러오기
	@Override
	public MemberVO selectIdMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminApply.selectIdMember", member_id);
	}

	// 신청넘버로 신청정보 불러오기
	@Override
	public ApplyMonVO selectApplyMon(int mo_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminApply.selectApplyMon", mo_no);
	}

	// 신청넘버로 신청첨부리스트 불러오기
	@Override
	public List selectApplyMonFile(int mo_no) throws DataAccessException {
		List<ApplyMonFileVO> monthFileList = null;
		monthFileList = sqlSession.selectList("mapper.adminApply.selectApplyMonFile", mo_no);
		return monthFileList;
	}

	//	회원 상세 조회
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminApply.selectMember", member_id);
	}

	// 신청 테이블과 member 테이블 join
	@Override
	public List joinTable(Map pagingMap) {
		List<ApplyMonVO> applyList = null;
		applyList = sqlSession.selectList("mapper.adminApply.joinTable", pagingMap);
		return applyList;
	}

	// 총 회원수
	@Override
	public int selectTotApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
		return totApply;
	}

	// 회원 상세정보
	@Override
	public int selectDetailApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
		return totApply;
	}

	// 신청상태 적용
	@Override
	public void modifyAdminMon(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminApply.modifyAdminMon", membersMap);
	}
	
	// 신청 지급 날짜 적용
	@Override
	public void modifyAdminMonPay(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminApply.modifyAdminMonPay", membersMap);
	}
	

}
