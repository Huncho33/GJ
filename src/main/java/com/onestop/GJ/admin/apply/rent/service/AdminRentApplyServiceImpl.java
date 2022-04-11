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
		List<ApplyRentVO> applyList = adminDAO.selectApplyBySearchMember(pagingMap);
		System.out.println("서비스로 돌아옴");
//		int searchTotMembers = adminDAO.selectSearchTotMembers(pagingMap);
		int searchTotApply = adminDAO.selectSearchTotApply(pagingMap);
		System.out.println("서비스 searchTotApply : " + searchTotApply);
		int totApply = adminDAO.selectTotApply();
		membersMap.put("applyList", applyList);
		membersMap.put("searchTotApply", searchTotApply);
		membersMap.put("totMembers", totApply);
//		      articlesMap.put("searchTotArticles", 170);
		System.out.println("서비스 searchMemberList. membersMap : " + membersMap);
		return membersMap;
	}

	// 회원 상세 - 해당 회원정보,신청정보 호출
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

	// 셀렉한 멤버VO 가져오기
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return adminDAO.selectIdMember(member_id);
	}

	// 신청정보 조회
	@Override
	public Map joinTable(Map pagingMap) {
		Map applyMap = new HashMap();
		System.out.println("서비스  pagingMap 값들 : " + pagingMap);
		List<ApplyRentVO> applyList = adminDAO.joinTable(pagingMap);
		int totApply = adminDAO.selectTotApply();
		applyMap.put("applyList", applyList);
		applyMap.put("totApply", totApply);
		System.out.println("서비스 totApply : " + totApply);
		System.out.println("서비스 applyList : " + applyList);
//		list.add(list);
//		list.add(totApply);
		return applyMap;
	}

	// 신청상태 적용
	@Override
	public ApplyRentVO modifyAdminRent(Map membersMap) throws Exception {
		adminDAO.modifyAdminRent(membersMap);
		int rent_no = (Integer) membersMap.get("rent_no");
		return adminDAO.selectApplyRent(rent_no);
	}

	// 신청상태 적용 (지급날짜)
	@Override
	public ApplyRentVO modifyAdminRentPay(Map membersMap) throws Exception {
		adminDAO.modifyAdminRentPay(membersMap);
		int rent_no = (Integer) membersMap.get("rent_no");
		return adminDAO.selectApplyRent(rent_no);
	}

}
