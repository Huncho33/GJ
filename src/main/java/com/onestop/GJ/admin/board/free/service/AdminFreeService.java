package com.onestop.GJ.admin.board.free.service;

import java.util.Map;

import com.onestop.GJ.admin.board.free.vo.AdminFreeImageVO;
import com.onestop.GJ.board.free.vo.BoardFreeImageVO;

public interface AdminFreeService {

	Map listArticles(Map pagingMap) throws Exception;

	Map searchBoardList(Map pagingMap) throws Exception;

	int addNewArticle(Map articleMap) throws Exception;

	Map viewArticle(int fr_NO) throws Exception;

	void removeArticle(int fr_NO) throws Exception;

	void modArticle(Map<String, Object> articleMap) throws Exception;

	void removeModImage(AdminFreeImageVO adminFreeImageVO) throws Exception;




}
