package com.onestop.GJ.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface MypageController {

	String myInfo(@RequestParam(value = "pwdCfm_id", required = false) String member_id,
			@RequestParam(value = "pwdCfm_pwd", required = false) String member_pw, Model model) throws Exception;

	ModelAndView confirmPwdView(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;

//	ModelAndView modMyInfo(@RequestParam("member_id") String member_id,
//			HttpServletRequest request, HttpServletResponse response) throws Exception;

//	ModelAndView modMyInfo(@RequestParam(value = "member_id2") String member_id,
//			@ModelAttribute(value = "member") MemberVO memberVO, Model model, 
//			HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity modMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView deleteMember(@RequestParam(value = "memDel_id2") String member_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

//	ModelAndView modMyInfo(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
//			throws Exception;
}