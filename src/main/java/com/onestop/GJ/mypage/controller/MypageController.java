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

	ResponseEntity modMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView deleteMember(@RequestParam(value = "memDel_id2") String member_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView myQna(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity removeQna(int qna_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

	String modalPwdCheck(int qna_no, int qna_pw, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView myBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity removeArticle(int fr_NO, HttpServletRequest request, HttpServletResponse response) throws Exception;

}