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

	// 신청 회원정보 조회
	@Override
	public Map listMembers(Map pagingMap) throws DataAccessException {

		Map membersMap = new HashMap();
		List<MemberVO> membersList = adminDAO.selectAllMemberList(pagingMap);
		int totMembers = adminDAO.selectTotMembers();
		membersMap.put("membersList", membersList);
		membersMap.put("totMembers", totMembers);
		return membersMap;
	}

	// 검색창
	@Override
	public Map searchMemberList(Map pagingMap) throws Exception {
		Map membersMap = new HashMap();
		List<ApplyMonVO> applyList = adminDAO.selectApplyBySearchMember(pagingMap);
		int searchTotApply = adminDAO.selectSearchTotApply(pagingMap);
		int totApply = adminDAO.selectTotApply();
		membersMap.put("applyList", applyList);
		membersMap.put("searchTotApply", searchTotApply);
		membersMap.put("totMembers", totApply);
		return membersMap;
	}

	// 회원 상세 - 해당 회원정보, 신청정보 호출
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

	// 셀렉한 멤버VO 가져오기
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return adminDAO.selectIdMember(member_id);
	}

	// 신청정보 조회
	@Override
	public Map joinTable(Map pagingMap) {
		Map applyMap = new HashMap();
		List<ApplyMonVO> applyList = adminDAO.joinTable(pagingMap);
		int totApply = adminDAO.selectTotApply();
		applyMap.put("applyList", applyList);
		applyMap.put("totApply", totApply);
		return applyMap;
	}

	// 신청상태 적용
	@Override
	public ApplyMonVO modifyAdminMon(Map membersMap) throws Exception {
		adminDAO.modifyAdminMon(membersMap);
		int mo_no = (Integer) membersMap.get("mo_no");
		return adminDAO.selectApplyMon(mo_no);
	}

	// 신청상태 적용 (지급날짜)
	@Override
	public ApplyMonVO modifyAdminMonPay(Map membersMap) throws Exception {
		adminDAO.modifyAdminMonPay(membersMap);
		int mo_no = (Integer) membersMap.get("mo_no");
		return adminDAO.selectApplyMon(mo_no);
	}
}
