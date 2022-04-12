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

	// ��û ȸ������ ��ȸ
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminRentApply.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// �˻�â(��û)
	@Override
	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
		List<ApplyRentVO> selectApplyList = sqlSession.selectList("mapper.adminRentApply.selectApplyBySearchMember",
				pagingMap);
		return selectApplyList;
	}

	// �˻�â(��û) �� �˻���
	@Override
	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
		int searchTotApply = sqlSession.selectOne("mapper.adminRentApply.selectSearchTotApply", pagingMap);
		return searchTotApply;
	}

	// �� ȸ����
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminRentApply.selectTotMembers");
		return totMembers;

	}

	// �˻�â �� �˻���
	@Override
	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
		int searchTotMembers = sqlSession.selectOne("mapper.adminRentApply.selectSearchTotMembers", pagingMap);
		return searchTotMembers;
	}

//  ȸ�� �󼼺���
	@Override
	public MemberVO selectMember(int rent_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectMember", rent_no);
	}

//  ȸ�� �󼼺���
	@Override
	public MemberVO selectRentApplyView(int rent_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectRentApplyView", rent_no);
	}

	// id�� ȸ�� ���� �ҷ�����
	@Override
	public MemberVO selectIdMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectIdMember", member_id);
	}

	// ��û�ѹ��� ��û���� �ҷ�����
	@Override
	public ApplyRentVO selectApplyRent(int rent_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminRentApply.selectApplyRent", rent_no);
	}

	// ��û�ѹ��� ��û÷�θ���Ʈ �ҷ�����
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

	// ��û ���̺�� member ���̺� join
	@Override
	public List joinTable(Map pagingMap) {
		List<ApplyRentVO> applyList = null;
		applyList = sqlSession.selectList("mapper.adminRentApply.joinTable", pagingMap);
		return applyList;

	}

	// �� ȸ����
	@Override
	public int selectTotApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminRentApply.selectTotApply");
		return totApply;

	}

	// ȸ�� ������
	@Override
	public int selectDetailApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminRentApply.selectTotApply");
		return totApply;

	}

	// ��û���� ����
	@Override
	public void modifyAdminRent(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminRentApply.modifyAdminRent", membersMap);
	}
	
	// ��û ���� ��¥ ����
	@Override
	public void modifyAdminRentPay(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminRentApply.modifyAdminRentPay", membersMap);
	}

}
