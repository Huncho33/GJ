package com.onestop.GJ.admin.board.QNA.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface AdminQnaController {

	ModelAndView listQnas(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity addNewQna(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity addNewReply(HttpServletRequest request, HttpServletResponse response) throws Exception;



	ResponseEntity removeQna(int qna_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView searchBoardList(String searchWord, String searchType_qna, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	String modalPwdCheck(int qna_no, int qna_pw, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
