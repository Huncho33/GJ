package com.onestop.GJ.admin.apply.share.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.apply.share.vo.AdminShareApplyVO;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("AdminShareApplyDAO")
public class AdminShareApplyDAOImpl implements AdminShareApplyDAO {
	@Autowired
	private SqlSession sqlSession;

	// 신청 회원정보 조회
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminShareApply.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// 검색창(신청)
	@Override
	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
		List<AdminShareApplyVO> selectApplyList = sqlSession.selectList("mapper.adminShareApply.selectApplyBySearchMember",
				pagingMap);
		System.out.println("다오 apply 검색창" + selectApplyList);
		return selectApplyList;
	}

	// 검색창(신청) 총 검색수
	@Override
	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
		System.out.println("DAO selectSearchTotApply 메소드 안으로 들어옴 ");
		int searchTotApply = sqlSession.selectOne("mapper.adminShareApply.selectSearchTotApply", pagingMap);
		System.out.println("검색회원수" + searchTotApply);
		return searchTotApply;
	}

	// 총 회원수
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminShareApply.selectTotMembers");
		return totMembers;

	}

	// 검색창 총 검색수
	@Override
	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
		int searchTotMembers = sqlSession.selectOne("mapper.adminShareApply.selectSearchTotMembers", pagingMap);
		System.out.println("검색회원수" + searchTotMembers);
		return searchTotMembers;
	}

//  회원 상세보기
	@Override
	public MemberVO selectMember(int sh_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectMember", sh_no);
	}

//  회원 상세보기
	@Override
	public MemberVO selectShareApplyView(int sh_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectShareApplyView",sh_no);
	}

	// id로 회원 정보 불러오기
	@Override
	public MemberVO selectIdMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectIdMember", member_id);
	}

	// 신청넘버로 신청정보 불러오기
	@Override
	public AdminShareApplyVO selectApplyShare(int sh_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectApplyShare", sh_no);
	}

	// 신청넘버로 신청첨부리스트 불러오기
	@Override
	public List selectApplyShareFile(int sh_no) throws DataAccessException {
		List<ApplyShareFileVO> shareFileList = null;
		shareFileList = sqlSession.selectList("mapper.adminShareApply.selectApplyShareFile", sh_no);
		return shareFileList;
	}

	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectMember", member_id);
	}

	// 신청 테이블과 member 테이블 join
	@Override
	public List joinTable(Map pagingMap) {
		System.out.println("조인 다오 쿼리진행");
		List<AdminShareApplyVO> applyList = null;
		System.out.println("다오  pagingMap 값들 : " + pagingMap);
		applyList = sqlSession.selectList("mapper.adminShareApply.joinTable", pagingMap);
		System.out.println("다오  applyList 값들 : " + applyList);
		return applyList;

	}

	// 총 회원수
	@Override
	public int selectTotApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminShareApply.selectTotApply");
		System.out.println("DAO totMembers : " + totApply);
		return totApply;

	}

	// 회원 상세정보
	@Override
	public int selectDetailApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminShareApply.selectTotApply");
		System.out.println("DAO totMembers : " + totApply);
		return totApply;

	}

	// 신청상태 적용
	@Override
	public void modifyAdminShare(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminShareApply.modifyAdminShare", membersMap);
	}
	
	// 신청 지급 날짜 적용
	@Override
	public void modifyAdminSharePay(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminShareApply.modifyAdminSharePay", membersMap);
	}

}
