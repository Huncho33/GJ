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
//	// ��û ȸ������ ��ȸ
//	@Override
//	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
//		List<MemberVO> membersList = null;
//		membersList = sqlSession.selectList("mapper.adminApply.selectAllMemberList_adm", pagingMap);
//		return membersList;
//	}
//
//	// �˻�â(��û)
//	@Override
//	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
//		List<ApplyRentVO> selectApplyList = sqlSession.selectList("mapper.adminApply.selectApplyBySearchMember",
//				pagingMap);
//		System.out.println("�ٿ� apply �˻�â" + selectApplyList);
//		return selectApplyList;
//	}
//
//	// �˻�â(��û) �� �˻���
//	@Override
//	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
//		System.out.println("DAO selectSearchTotApply �޼ҵ� ������ ���� ");
//		int searchTotApply = sqlSession.selectOne("mapper.adminApply.selectSearchTotApply", pagingMap);
//		System.out.println("�˻�ȸ����" + searchTotApply);
//		return searchTotApply;
//	}
//
//	// �� ȸ����
//	@Override
//	public int selectTotMembers() throws DataAccessException {
//		int totMembers = sqlSession.selectOne("mapper.adminApply.selectTotMembers");
//		return totMembers;
//
//	}
//
//	// �˻�â �� �˻���
//	@Override
//	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
//		int searchTotMembers = sqlSession.selectOne("mapper.adminApply.selectSearchTotMembers", pagingMap);
//		System.out.println("�˻�ȸ����" + searchTotMembers);
//		return searchTotMembers;
//	}
//
////  ȸ�� �󼼺���
//	@Override
//	public MemberVO selectMember(int rent_no) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectMember", rent_no);
//	}
//
////  ȸ�� �󼼺���
//	@Override
//	public MemberVO selectRentApplyView(int rent_no) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectRentApplyView", rent_no);
//	}
//
//	// id�� ȸ�� ���� �ҷ�����
//	@Override
//	public MemberVO selectIdMember(String member_id) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectIdMember", member_id);
//	}
//
//	// ��û�ѹ��� ��û���� �ҷ�����
//	@Override
//	public ApplyRentVO selectApplyRent(int rent_no) throws DataAccessException {
//		return sqlSession.selectOne("mapper.adminApply.selectApplyRent", rent_no);
//	}
//
//	// ��û�ѹ��� ��û÷�θ���Ʈ �ҷ�����
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
//	// ��û ���̺�� member ���̺� join
//	@Override
//	public List joinTable(Map pagingMap) {
//		System.out.println("���� �ٿ� ��������");
//		List<ApplyRentVO> applyList = null;
//		System.out.println("�ٿ�  pagingMap ���� : " + pagingMap);
//		applyList = sqlSession.selectList("mapper.adminApply.joinTable", pagingMap);
//		System.out.println("�ٿ�  applyList ���� : " + applyList);
//		return applyList;
//
//	}
//
//	// �� ȸ����
//	@Override
//	public int selectTotApply() throws DataAccessException {
//		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
//		System.out.println("DAO totMembers : " + totApply);
//		return totApply;
//
//	}
//
//	// ȸ�� ������
//	@Override
//	public int selectDetailApply() throws DataAccessException {
//		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
//		System.out.println("DAO totMembers : " + totApply);
//		return totApply;
//
//	}
//
//	// ��û���� ����
//	@Override
//	public void modifyAdminRent(Map membersMap) throws Exception {
//		sqlSession.update("mapper.adminApply.modifyAdminRent", membersMap);
//	}
//	
//	// ��û ���� ��¥ ����
//	@Override
//	public void modifyAdminRentPay(Map membersMap) throws Exception {
//		sqlSession.update("mapper.adminApply.modifyAdminRentPay", membersMap);
//	}
//
//}
