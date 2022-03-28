package com.onestop.GJ.board.QNA.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface QnaController {

	ModelAndView listQnas(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	ResponseEntity addNewQna(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity removeQna(int qna_no, HttpServletRequest request, HttpServletResponse response) throws Exception;

//	String modalPwdCheck(int qna_no, int qna_pw) throws Exception;


	String modalPwdCheck(int qna_no, int qna_pw, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity addNewReply(HttpServletRequest request, HttpServletResponse response) throws Exception;





}