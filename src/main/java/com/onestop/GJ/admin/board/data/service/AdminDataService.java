package com.onestop.GJ.admin.board.data.service;

import java.util.Map;

import com.onestop.GJ.admin.board.data.vo.AdminDataImageVO;

public interface AdminDataService {

	Map listArticles(Map pagingMap) throws Exception;

	Map searchBoardList(Map pagingMap) throws Exception;

	int addNewArticle(Map articleMap) throws Exception;

	Map viewArticle(int etc_NO) throws Exception;

	void removeArticle(int etc_NO) throws Exception;

	void modArticle(Map<String, Object> articleMap) throws Exception;

	void removeModImage(AdminDataImageVO adminDataImageVO) throws Exception;

}
