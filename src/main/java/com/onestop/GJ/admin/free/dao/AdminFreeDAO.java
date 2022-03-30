package com.onestop.GJ.admin.free.dao;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.admin.free.vo.AdminFreeImageVO;
import com.onestop.GJ.admin.free.vo.AdminFreeVO;

public interface AdminFreeDAO {

	List<AdminFreeDAO> selectAllArticlesList(Map pagingMap) throws Exception;

	int selectTotArticles();

	List<AdminFreeDAO> selectBoardListBySearchWord(Map pagingMap);

	int selectSearchTotArticles(Map pagingMap);

	int insertNewArticle(Map articleMap);

	void insertNewImage(Map articleMap);

	AdminFreeVO selectArticle(int fr_NO);

	List<AdminFreeImageVO> selectImageFileList(int fr_NO);

	void boardHits(int fr_NO) throws Exception;

	void deleteArticle(int fr_NO);

	void updateArticle(Map articleMap);

	void updateImageFile(Map articleMap);

	void insertModNewImage(Map articleMap);

	void deleteModImage(AdminFreeImageVO adminFreeImageVO);

}
