package com.onestop.GJ.board.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

public interface BoardNoticeDAO {

	List selectAllArticlesList(Map pagingMap) throws Exception;

	BoardNoticeVO selectArticle(int noti_NO) throws DataAccessException;

	int insertNewArticle(Map articleMap) throws DataAccessException;

	List selectImageFileList(int noti_NO) throws DataAccessException;

	void insertNewImage(Map articleMap) throws DataAccessException;
	
	void updateArticle(Map articleMap) throws DataAccessException;

	void deleteArticle(int noti_NO) throws DataAccessException;

	void boardHits(int noti_NO) throws Exception;

	int selectTotArticles() throws DataAccessException;



	List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException;




	


}