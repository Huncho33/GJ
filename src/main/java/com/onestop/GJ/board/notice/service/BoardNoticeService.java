package com.onestop.GJ.board.notice.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

public interface BoardNoticeService {

	Map listArticles(Map pagingMap) throws Exception;

	Map viewArticle(int noti_NO) throws Exception;
	
	int addNewArticle(Map articleMap) throws Exception;
	
	void modArticle(Map articleMap) throws Exception;

	void removeArticle(int noti_NO) throws Exception;



	Map searchBoardList(Map pagingMap) throws Exception;








}