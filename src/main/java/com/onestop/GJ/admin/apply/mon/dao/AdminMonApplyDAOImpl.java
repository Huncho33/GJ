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

	// ��û ȸ������ ��ȸ
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminApply.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// �˻�â(��û)
	@Override
	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
		List<ApplyMonVO> selectApplyList = sqlSession.selectList("mapper.adminApply.selectApplyBySearchMember",
				pagingMap);
		return selectApplyList;
	}

	// �˻�â(��û) �� �˻���
	@Override
	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
		int searchTotApply = sqlSession.selectOne("mapper.adminApply.selectSearchTotApply", pagingMap);
		return searchTotApply;
	}

	// �� ȸ����
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminApply.selectTotMembers");
		return totMembers;

	}

	// id�� ȸ�� ���� �ҷ�����
	@Override
	public MemberVO selectIdMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminApply.selectIdMember", member_id);
	}

	// ��û�ѹ��� ��û���� �ҷ�����
	@Override
	public ApplyMonVO selectApplyMon(int mo_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminApply.selectApplyMon", mo_no);
	}

	// ��û�ѹ��� ��û÷�θ���Ʈ �ҷ�����
	@Override
	public List selectApplyMonFile(int mo_no) throws DataAccessException {
		List<ApplyMonFileVO> monthFileList = null;
		monthFileList = sqlSession.selectList("mapper.adminApply.selectApplyMonFile", mo_no);
		return monthFileList;
	}

	//	ȸ�� �� ��ȸ
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminApply.selectMember", member_id);
	}

	// ��û ���̺�� member ���̺� join
	@Override
	public List joinTable(Map pagingMap) {
		List<ApplyMonVO> applyList = null;
		applyList = sqlSession.selectList("mapper.adminApply.joinTable", pagingMap);
		return applyList;
	}

	// �� ȸ����
	@Override
	public int selectTotApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
		return totApply;
	}

	// ȸ�� ������
	@Override
	public int selectDetailApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminApply.selectTotApply");
		return totApply;
	}

	// ��û���� ����
	@Override
	public void modifyAdminMon(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminApply.modifyAdminMon", membersMap);
	}
	
	// ��û ���� ��¥ ����
	@Override
	public void modifyAdminMonPay(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminApply.modifyAdminMonPay", membersMap);
	}
	

}
