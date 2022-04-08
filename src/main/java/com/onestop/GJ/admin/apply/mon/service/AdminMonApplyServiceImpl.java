package com.onestop.GJ.admin.apply.mon.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.apply.mon.dao.AdminMonApplyDAOImpl;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("AdminMonApplyServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminMonApplyServiceImpl implements AdminMonApplyService {
	@Autowired
	private AdminMonApplyDAOImpl adminDAO;

	// ��û ȸ������ ��ȸ
	@Override
	public Map listMembers(Map pagingMap) throws DataAccessException {

		Map membersMap = new HashMap();
		List<MemberVO> membersList = adminDAO.selectAllMemberList(pagingMap);
		int totMembers = adminDAO.selectTotMembers();
		membersMap.put("membersList", membersList);
		membersMap.put("totMembers", totMembers);
		return membersMap;
//		List membersList = null;
	}

	// �˻�â
	@Override
	public Map searchMemberList(Map pagingMap) throws Exception {
		Map membersMap = new HashMap();
		List<ApplyMonVO> applyList = adminDAO.selectApplyBySearchMember(pagingMap);
		System.out.println("���񽺷� ���ƿ�");
//		int searchTotMembers = adminDAO.selectSearchTotMembers(pagingMap);
		int searchTotApply = adminDAO.selectSearchTotApply(pagingMap);
		System.out.println("���� searchTotApply : " + searchTotApply);
		int totApply = adminDAO.selectTotApply();
		membersMap.put("applyList", applyList);
		membersMap.put("searchTotApply", searchTotApply);
		membersMap.put("totMembers", totApply);
//		      articlesMap.put("searchTotArticles", 170);
		System.out.println("���� searchMemberList. membersMap : " + membersMap);
		return membersMap;
	}

	// ȸ�� �� - �ش� ȸ������,��û���� ȣ��
	@Override
	public Map viewApplyMember(int mo_no, String member_id) throws Exception {
		Map membersMap = new HashMap();
		ApplyMonVO applyMonVO = adminDAO.selectApplyMon(mo_no);
		MemberVO memberVO = adminDAO.selectIdMember(member_id);
		List<ApplyMonFileVO> monthFileList = adminDAO.selectApplyMonFile(mo_no);
		membersMap.put("member", memberVO);
		membersMap.put("applyMon", applyMonVO);
		membersMap.put("monthFileList", monthFileList);
		Collection<String> value = membersMap.values();

		return membersMap;

	}

	// ������ ���VO ��������
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return adminDAO.selectIdMember(member_id);
	}

	// ��û���� ��ȸ
	@Override
	public Map joinTable(Map pagingMap) {
		Map applyMap = new HashMap();
		System.out.println("����  pagingMap ���� : " + pagingMap);
		List<ApplyMonVO> applyList = adminDAO.joinTable(pagingMap);
		int totApply = adminDAO.selectTotApply();
		applyMap.put("applyList", applyList);
		applyMap.put("totApply", totApply);
		System.out.println("���� totApply : " + totApply);
		System.out.println("���� applyList : " + applyList);
//		list.add(list);
//		list.add(totApply);
		return applyMap;
	}

	// ��û���� ����
	@Override
	public ApplyMonVO modifyAdminMon(Map membersMap) throws Exception {
		adminDAO.modifyAdminMon(membersMap);
		int mo_no = (Integer) membersMap.get("mo_no");
		return adminDAO.selectApplyMon(mo_no);
	}

	// ��û���� ���� (���޳�¥)
	@Override
	public ApplyMonVO modifyAdminMonPay(Map membersMap) throws Exception {
		adminDAO.modifyAdminMonPay(membersMap);
		int mo_no = (Integer) membersMap.get("mo_no");
		return adminDAO.selectApplyMon(mo_no);
	}

}
