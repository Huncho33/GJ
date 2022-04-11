package com.onestop.GJ.admin.apply.share.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.admin.apply.share.vo.AdminShareApplyVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface AdminShareApplyService {

	Map listMembers(Map pagingMap) throws DataAccessException;

	Map searchMemberList(Map pagingMap) throws Exception;

	Map viewApplyMember(int sh_no, String member_id) throws Exception;

	MemberVO selectMemberId(String member_id) throws DataAccessException;

	Map joinTable(Map pagingMap);

	AdminShareApplyVO modifyAdminShare(Map membersMap) throws Exception;

	AdminShareApplyVO modifyAdminSharePay(Map membersMap) throws Exception;

	
	
	

}