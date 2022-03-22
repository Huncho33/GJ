package com.onestop.GJ.board.free.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.board.data.vo.BoardDataImageVO;
import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.free.vo.BoardFreeImageVO;
import com.onestop.GJ.board.free.vo.BoardFreeVO;

public interface BoardFreeDAO {

	List selectAllArticlesList(Map pagingMap) throws Exception;

	List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException;

	int selectTotArticles();

	int selectSearchTotArticles(Map pagingMap);

	int insertNewArticle(Map articleMap);

	void insertNewImage(Map articleMap);

	BoardFreeVO selectArticle(int fr_NO);

	List<BoardFreeImageVO> selectImageFileList(int fr_NO);

	void boardHits(int fr_NO) throws Exception;

	void deleteArticle(int fr_NO);

	void updateArticle(Map articleMap);

	void updateImageFile(Map articleMap);

	void insertModNewImage(Map articleMap);

	void deleteModImage(BoardFreeImageVO boardFreeImageVO);

}
