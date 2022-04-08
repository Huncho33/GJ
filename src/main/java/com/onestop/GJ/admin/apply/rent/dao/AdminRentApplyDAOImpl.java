//package com.onestop.GJ.admin.apply.rent.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Repository;
//
//import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
//import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
//import com.onestop.GJ.member.vo.MemberVO;
//
//@Repository("AdminRentApplyDAOImpl")
//public class AdminRentApplyDAOImpl implements AdminRentApplyDAO {
//	@Autowired
//	private SqlSession sqlSession;
//
//	// 신청 회원정보 조회
//	@Override
//	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
//		List<MemberVO> membersList = null;
//		membersList = sqlSession.selectList("mapper.adminApply.selectAllMemberList_adm", pagingMap);
//		return membersList;
//	}
//
//	// 검색창(신청)
//	@Override
//	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
//		List<ApplyRentVO> selectApplyList = sqlSession.selectList("mapper.adminApply.selectApplyBySearchMember",
//				pagingMap);
//		System.out.println("다오 apply 검색창" + selectApplyList);
//		return selectApplyList;
//	}
//
//	// 검색창(신청) 총 검색수
//	@Override
//	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
//		System.out.println("DAO selectSearchTotApply 메소드 안으로 들어옴 ");
//		int searchTotApply = sqlSession.selectOne("mapper.adminApply.selectSearchTotApply", pagingMap);
//		System.out.println("검색회원수" + searchTotApply);
//		return searchTotApply;
//	}
//
//	// 총 회원수
//	@Override
//	public int selectTotMembers() throws DataAccessException {
//		int totMembers = sqlSession.selectOne("mapper.adminApply.selectTotMembers");
//		return totMembers;
//
//	}
//
//	// 검색창 총 검색수
//	@Override
//	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
//		int searchTotMembers = sqlSession.selectOne("mapper.adminApply.selectSearchTotMembers", pagingMap);
//		System.out.println("검색회원수" + searchTotMembers);
//		return searchTotMembers;
//	}
//
////  회원 상세보기
//	@Override
//	public MemberVO selectMember(int rent_no) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectMember", rent_no);
//	}
//
////  회원 상세보기
//	@Override
//	public MemberVO selectRentApplyView(int rent_no) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectRentApplyView", rent_no);
//	}
//
//	// id로 회원 정보 불러오기
//	@Override
//	public MemberVO selectIdMember(String member_id) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectIdMember", member_id);
//	}
//
//	// 신청넘버로 신청정보 불러오기
//	@Override
//	public ApplyRentVO selectApplyRent(int rent_no) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectApplyRent", rent_no);
//	}
//
//	// 신청넘버로 신청첨부리스트 불러오기
//	@Override
//	public List selectApplyRentFile(int rent_no) throws DataAccessException {
//		List<ApplyRentFileVO> rentFileList = null;
//		rentFileList = sqlSession.selectList("mapper.adminApply.selectApplyRentFile", rent_no);
//		return rentFileList;
//	}
//
//	@Override
//	public MemberVO selectMemberId(String member_id) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectMember", member_id);
//	}
//
//	@Override
//	public int insertMember(MemberVO memberVO) throws DataAccessException {
//		int result = sqlSession.insert("mapper.adminApply.insertMember_adm", memberVO);
//		return result;
//	}
//
//	// 신청 테이블과 member 테이블 join
//	@Override
//	public List joinTable(Map pagingMap) {
//		System.out.println("조인 다오 쿼리진행");
//		List<ApplyRentVO> applyList = null;
//		System.out.println("다오  pagingMap 값들 : " + pagingMap);
//		applyList = sqlSession.selectList("mapper.adminApply.joinTable", pagingMap);
//		System.out.println("다오  applyList 값들 : " + applyList);
//		return applyList;
//
//	}
//
//	// 총 회원수
//	@Override
//	public int selectTotApply() throws DataAccessException {
//		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
//		System.out.println("DAO totMembers : " + totApply);
//		return totApply;
//
//	}
//
//	// 회원 상세정보
//	@Override
//	public int selectDetailApply() throws DataAccessException {
//		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
//		System.out.println("DAO totMembers : " + totApply);
//		return totApply;
//
//	}
//
//	// 신청상태 적용
//	@Override
//	public void modifyAdminRent(Map membersMap) throws Exception {
//		sqlSession.update("mapper.adminApply.modifyAdminRent", membersMap);
//	}
//	
//	// 신청 지급 날짜 적용
//	@Override
//	public void modifyAdminRentPay(Map membersMap) throws Exception {
//		sqlSession.update("mapper.adminApply.modifyAdminRentPay", membersMap);
//	}
//
//}
