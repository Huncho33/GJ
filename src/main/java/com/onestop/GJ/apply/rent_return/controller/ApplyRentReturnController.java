package com.onestop.GJ.apply.rent_return.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ApplyRentReturnController {

	
	ResponseEntity modApplyInfo(String attribute, String value, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;
}