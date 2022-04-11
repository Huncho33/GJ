package com.onestop.GJ.admin.board.notice.service;

import java.util.Map;

import com.onestop.GJ.admin.board.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;

public interface AdminNoticeService {

	Map listArticles(Map pagingMap) throws Exception;

	Map searchBoardList(Map pagingMap) throws Exception;

	int addNewArticle(Map articleMap) throws Exception;

	Map viewArticle(int noti_NO) throws Exception;

	void removeArticle(int noti_NO) throws Exception;

	void modArticle(Map<String, Object> articleMap) throws Exception;


	void removeModImage(AdminNoticeImageVO adminNoticeImageVO) throws Exception;


}
