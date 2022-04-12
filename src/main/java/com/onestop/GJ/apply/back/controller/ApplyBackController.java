package com.onestop.GJ.apply.back.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyBackController {



	ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	ModelAndView modApplyInfo(MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
