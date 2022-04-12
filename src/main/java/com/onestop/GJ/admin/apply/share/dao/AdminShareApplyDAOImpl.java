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

	// ��û ȸ������ ��ȸ
	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminShareApply.selectAllMemberList_adm", pagingMap);
		return membersList;
	}

	// �˻�â(��û)
	@Override
	public List selectApplyBySearchMember(Map pagingMap) throws DataAccessException {
		List<AdminShareApplyVO> selectApplyList = sqlSession.selectList("mapper.adminShareApply.selectApplyBySearchMember",
				pagingMap);
		System.out.println("�ٿ� apply �˻�â" + selectApplyList);
		return selectApplyList;
	}

	// �˻�â(��û) �� �˻���
	@Override
	public int selectSearchTotApply(Map pagingMap) throws DataAccessException {
		System.out.println("DAO selectSearchTotApply �޼ҵ� ������ ���� ");
		int searchTotApply = sqlSession.selectOne("mapper.adminShareApply.selectSearchTotApply", pagingMap);
		System.out.println("�˻�ȸ����" + searchTotApply);
		return searchTotApply;
	}

	// �� ȸ����
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminShareApply.selectTotMembers");
		return totMembers;

	}

	// �˻�â �� �˻���
	@Override
	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException {
		int searchTotMembers = sqlSession.selectOne("mapper.adminShareApply.selectSearchTotMembers", pagingMap);
		System.out.println("�˻�ȸ����" + searchTotMembers);
		return searchTotMembers;
	}

//  ȸ�� �󼼺���
	@Override
	public MemberVO selectMember(int sh_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectMember", sh_no);
	}

//  ȸ�� �󼼺���
	@Override
	public MemberVO selectShareApplyView(int sh_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectShareApplyView",sh_no);
	}

	// id�� ȸ�� ���� �ҷ�����
	@Override
	public MemberVO selectIdMember(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectIdMember", member_id);
	}

	// ��û�ѹ��� ��û���� �ҷ�����
	@Override
	public AdminShareApplyVO selectApplyShare(int sh_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminShareApply.selectApplyShare", sh_no);
	}

	// ��û�ѹ��� ��û÷�θ���Ʈ �ҷ�����
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

	// ��û ���̺�� member ���̺� join
	@Override
	public List joinTable(Map pagingMap) {
		System.out.println("���� �ٿ� ��������");
		List<AdminShareApplyVO> applyList = null;
		System.out.println("�ٿ�  pagingMap ���� : " + pagingMap);
		applyList = sqlSession.selectList("mapper.adminShareApply.joinTable", pagingMap);
		System.out.println("�ٿ�  applyList ���� : " + applyList);
		return applyList;

	}

	// �� ȸ����
	@Override
	public int selectTotApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminShareApply.selectTotApply");
		System.out.println("DAO totMembers : " + totApply);
		return totApply;

	}

	// ȸ�� ������
	@Override
	public int selectDetailApply() throws DataAccessException {
		int totApply = sqlSession.selectOne("mapper.adminShareApply.selectTotApply");
		System.out.println("DAO totMembers : " + totApply);
		return totApply;

	}

	// ��û���� ����
	@Override
	public void modifyAdminShare(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminShareApply.modifyAdminShare", membersMap);
	}
	
	// ��û ���� ��¥ ����
	@Override
	public void modifyAdminSharePay(Map membersMap) throws Exception {
		sqlSession.update("mapper.adminShareApply.modifyAdminSharePay", membersMap);
	}

}
