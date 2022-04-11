package com.onestop.GJ.admin.apply.rent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface AdminRentApplyController {

	ResponseEntity modifyAdminRent(String attribute, String value, int rent_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ModelAndView applyListMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView searchApplyList(String searchApply, String searchType, HttpServletRequest request,
			HttpServletResponse response) throws Exception;




}