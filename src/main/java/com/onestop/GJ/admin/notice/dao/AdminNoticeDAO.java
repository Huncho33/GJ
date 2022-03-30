package com.onestop.GJ.admin.notice.dao;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.admin.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.admin.notice.vo.AdminNoticeVO;

public interface AdminNoticeDAO {

	List<AdminNoticeVO> selectAllArticlesList(Map pagingMap) throws Exception;

	int selectTotArticles();

	List<AdminNoticeVO> selectBoardListBySearchWord(Map pagingMap);

	int selectSearchTotArticles(Map pagingMap);

	int insertNewArticle(Map articleMap);

	void insertNewImage(Map articleMap);

	AdminNoticeVO selectArticle(int noti_NO);

	List<AdminNoticeImageVO> selectImageFileList(int noti_NO);

	void boardHits(int noti_NO) throws Exception;

	void deleteArticle(int noti_NO);

	void updateArticle(Map articleMap);

	void updateImageFile(Map articleMap);

	void insertModNewImage(Map articleMap);

	void deleteModImage(AdminNoticeImageVO adminNoticeImageVO);

	boolean checkPwd(int noti_no);

}
