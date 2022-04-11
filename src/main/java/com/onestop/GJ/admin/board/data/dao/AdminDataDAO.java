package com.onestop.GJ.admin.board.data.dao;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.admin.board.data.vo.AdminDataImageVO;
import com.onestop.GJ.admin.board.data.vo.AdminDataVO;

public interface AdminDataDAO {
	

	List<AdminDataVO> selectAllArticlesList(Map pagingMap) throws Exception;

	int selectTotArticles();

	List<AdminDataVO> selectBoardListBySearchWord(Map pagingMap);

	int selectSearchTotArticles(Map pagingMap);

	int insertNewArticle(Map articleMap);

	void insertNewImage(Map articleMap);

	AdminDataVO selectArticle(int etc_NO);

	List<AdminDataImageVO> selectImageFileList(int etc_NO);

	void boardHits(int etc_NO) throws Exception;

	void deleteArticle(int etc_NO);

	void updateArticle(Map articleMap);

	void updateImageFile(Map articleMap);

	void insertModNewImage(Map articleMap);

	void deleteModImage(AdminDataImageVO adminDataImageVO);

}
