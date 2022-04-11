package com.onestop.GJ.admin.apply.share.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.apply.share.dao.AdminShareApplyDAOImpl;
import com.onestop.GJ.admin.apply.share.vo.AdminShareApplyVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("AdminShareApplyService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminShareApplyServiceImpl implements AdminShareApplyService {
	@Autowired
	private AdminShareApplyDAOImpl adminDAO;

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
		List<AdminShareApplyVO> applyList = adminDAO.selectApplyBySearchMember(pagingMap);
		System.out.println("���񽺷� ���ƿ�");
		int searchTotApply = adminDAO.selectSearchTotApply(pagingMap);
		System.out.println("���� searchTotApply : " + searchTotApply);
		int totApply = adminDAO.selectTotApply();
		membersMap.put("applyList", applyList);
		membersMap.put("searchTotApply", searchTotApply);
		membersMap.put("totMembers", totApply);
		System.out.println("���� searchMemberList. membersMap : " + membersMap);
		return membersMap;
	}

	// ȸ�� �� - �ش� ȸ������,��û���� ȣ��
	@Override
	public Map viewApplyMember(int sh_no, String member_id) throws Exception {
		Map membersMap = new HashMap();
		AdminShareApplyVO applyShareVO = adminDAO.selectApplyShare(sh_no);
		MemberVO memberVO = adminDAO.selectIdMember(member_id);
		List<ApplyShareFileVO> shareFileList = adminDAO.selectApplyShareFile(sh_no);
		membersMap.put("member", memberVO);
		membersMap.put("applyShare", applyShareVO);
		membersMap.put("shareFileList", shareFileList);
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
		List<AdminShareApplyVO> applyList = adminDAO.joinTable(pagingMap);
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
	public AdminShareApplyVO modifyAdminShare(Map membersMap) throws Exception {
		adminDAO.modifyAdminShare(membersMap);
		int sh_no = (Integer) membersMap.get("sh_no");
		return adminDAO.selectApplyShare(sh_no);
	}

	// ��û���� ���� (���޳�¥)
	@Override
	public AdminShareApplyVO modifyAdminSharePay(Map membersMap) throws Exception {
		adminDAO.modifyAdminSharePay(membersMap);
		int sh_no = (Integer) membersMap.get("sh_no");
		return adminDAO.selectApplyShare(sh_no);
	}

}
