package com.onestop.GJ.apply.mon23.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

public interface ApplyMonController {

	// 

	ResponseEntity modApplyInfo(String attribute, String value, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

//	ResponseEntity insertResult(ApplyMonVO applymonVO, HttpServletRequest request, HttpServletResponse response)
//			throws Exception;

//	ResponseEntity addResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
//			throws Exception;

	ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

//	ResponseEntity addResult(ApplyMonVO applymonVO, HttpServletRequest request, HttpServletResponse response)
//			throws Exception;

	//신청 등록

	/*
	 * ResponseEntity addResult(MultipartHttpServletRequest multipartRequest,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception;
	 */

//	ModelAndView result(Map<String, String> rMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception;

}