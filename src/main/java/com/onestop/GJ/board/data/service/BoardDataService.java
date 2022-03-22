package com.onestop.GJ.board.data.service;

import java.util.Map;

import com.onestop.GJ.board.data.vo.BoardDataImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;


public interface BoardDataService {

	Map listArticles(Map pagingMap) throws Exception;

	Map viewArticle(int etc_NO) throws Exception;
	
	int addNewArticle(Map articleMap) throws Exception;
	
	void modArticle(Map articleMap) throws Exception;

	void removeArticle(int etc_NO) throws Exception;



	Map searchBoardList(Map pagingMap) throws Exception;

	void removeModImage(BoardDataImageVO boardDataImageVO) throws Exception;
}
