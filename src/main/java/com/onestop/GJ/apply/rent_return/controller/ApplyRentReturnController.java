package com.onestop.GJ.apply.rent_return.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyRentReturnController {


	ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	ModelAndView modApplyInfo(MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
