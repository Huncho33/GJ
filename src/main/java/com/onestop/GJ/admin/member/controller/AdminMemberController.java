package com.onestop.GJ.admin.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.admin.member.vo.AdminMemberVO;

public interface AdminMemberController {

	ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView removeMember(String member_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView addMember(AdminMemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView searchMemberList(String searchMember, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity modMemberInfo(String attribute, String value, String sltmember_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
