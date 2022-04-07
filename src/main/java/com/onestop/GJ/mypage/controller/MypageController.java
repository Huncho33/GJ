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

	ModelAndView monthApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView rentApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView shareApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception;

//	ResponseEntity removeApply(int mo_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView viewMonthApply(int mo_no, String removeCompleted, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ModelAndView modMonthApplyView(String removeCompleted, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView viewShareApply(int sh_no, String removeCompleted, HttpServletRequest request,
			HttpServletResponse response) throws Exception;


	ModelAndView viewRentApply(int rent_no, String removeCompleted, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ModelAndView viewRetApply(int ret_no, String removeCompleted, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ModelAndView viewBackApply(int ba_no, String removeCompleted, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ResponseEntity removeMonthApply(int mo_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity removeShareApply(int sh_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity removeBackApply(int ba_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity removeRetApply(int ret_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity removeRentApply(int rent_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView modRentApplyView(String removeCompleted, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView modRetApplyView(String removeCompleted, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView modBackApplyView(String removeCompleted, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView modShareApplyView(String removeCompleted, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}