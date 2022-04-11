package com.onestop.GJ.admin.apply.rent.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.apply.rent.dao.AdminRentApplyDAOImpl;
import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("AdminRentApplyServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminRentApplyServiceImpl implements AdminRentApplyService {
	@Autowired
	private AdminRentApplyDAOImpl adminDAO;

	// ��û ȸ������ ��ȸ
	@Override
	public Map listMembers(Map pagingMap) throws DataAccessException {

		Map membersMap = new HashMap();
		List<MemberVO> membersList = adminDAO.selectAllMemberList(pagingMap);
		int totMembers = adminDAO.selectTotMembers();
		membersMap.put("membersList", membersList);
		membersMap.put("totMembers", totMembers);
		return membersMap;
	}

	// �˻�â
	@Override
	public Map searchMemberList(Map pagingMap) throws Exception {
		Map membersMap = new HashMap();
		List<ApplyRentVO> applyList = adminDAO.selectApplyBySearchMember(pagingMap);
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
	public Map viewApplyMember(int rent_no, String member_id) throws Exception {
		Map membersMap = new HashMap();
		ApplyRentVO applyRentVO = adminDAO.selectApplyRent(rent_no);
		MemberVO memberVO = adminDAO.selectIdMember(member_id);
		List<ApplyRentFileVO> rentFileList = adminDAO.selectApplyRentFile(rent_no);
		membersMap.put("member", memberVO);
		membersMap.put("applyRent", applyRentVO);
		membersMap.put("rentFileList", rentFileList);
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
		List<ApplyRentVO> applyList = adminDAO.joinTable(pagingMap);
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
	public ApplyRentVO modifyAdminRent(Map membersMap) throws Exception {
		adminDAO.modifyAdminRent(membersMap);
		int rent_no = (Integer) membersMap.get("rent_no");
		return adminDAO.selectApplyRent(rent_no);
	}

	// ��û���� ���� (���޳�¥)
	@Override
	public ApplyRentVO modifyAdminRentPay(Map membersMap) throws Exception {
		adminDAO.modifyAdminRentPay(membersMap);
		int rent_no = (Integer) membersMap.get("rent_no");
		return adminDAO.selectApplyRent(rent_no);
	}

}
