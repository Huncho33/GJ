package com.onestop.GJ.admin.apply.mon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public interface AdminMonApplyController {

	// 회원정보리스트

//	ModelAndView findAll(HttpServletRequest request, String id);

	ModelAndView applyListMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView searchApplyList(String searchApply, String searchType, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ResponseEntity modifyAdminMon(String attribute, String value, int mo_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}