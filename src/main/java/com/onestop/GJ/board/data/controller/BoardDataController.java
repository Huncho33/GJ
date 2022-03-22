package com.onestop.GJ.board.data.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardDataController {

	ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView searchBoardList(String searchWord, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	ResponseEntity removeArticle(int etc_NO, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
