package com.onestop.GJ.admin.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.member.vo.MemberVO;

public interface AdminMemberController {

	ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView addMember(MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView searchMemberList(String searchMember, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity modMemberInfo(String attribute, String value, String sltmember_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
