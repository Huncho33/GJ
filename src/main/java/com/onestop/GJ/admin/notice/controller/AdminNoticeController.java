package com.onestop.GJ.admin.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface AdminNoticeController {

	ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	ResponseEntity removeArticle(int noti_NO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;


	ResponseEntity modalCheck(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	ModelAndView searchBoardList(String searchWord, String searchType_notice, HttpServletRequest request,
			HttpServletResponse response) throws Exception;


}
