package com.onestop.GJ.board.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardNoticeController {

//	ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;


	ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;


	ResponseEntity removeArticle(int noti_NO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;


	ModelAndView searchBoardList(String searchWord, HttpServletRequest request, HttpServletResponse response)
			throws Exception;








}