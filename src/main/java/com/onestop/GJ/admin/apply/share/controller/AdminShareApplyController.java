package com.onestop.GJ.admin.apply.share.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public interface AdminShareApplyController {

	ModelAndView applyListMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView searchApplyList(String searchApply, String searchType, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ResponseEntity modifyAdminShare(String attribute, String value, int sh_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception;


}