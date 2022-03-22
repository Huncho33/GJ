package com.onestop.GJ.board.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.board.data.vo.BoardDataImageVO;
import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;



public interface BoardDataDAO {

	List selectAllArticlesList(Map pagingMap) throws Exception;
	
	public BoardDataVO selectArticle(int etc_NO) throws DataAccessException;
	
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	
	public List<BoardDataImageVO> selectImageFileList(int etc_NO)throws DataAccessException;
	
	void insertNewImage(Map articleMap) throws DataAccessException;
	
	void updateArticle(Map articleMap) throws DataAccessException;

	void deleteArticle(int etc_NO) throws DataAccessException;

	void boardHits(int etc_NO) throws Exception;

	int selectTotArticles() throws DataAccessException;

	List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException;

	int selectSearchTotArticles(Map pagingMap) throws DataAccessException;

	void updateImageFile(Map articleMap) throws DataAccessException;

	void insertModNewImage(Map articleMap) throws DataAccessException;

	void deleteModImage(BoardDataImageVO boardDataImageVO) throws DataAccessException;


	

}
