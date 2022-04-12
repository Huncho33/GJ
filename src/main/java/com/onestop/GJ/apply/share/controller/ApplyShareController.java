package com.onestop.GJ.apply.share.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyShareController {


	// ��û���
	ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;
	// ��û ���� ���� 
	ModelAndView modApplyInfo(MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}