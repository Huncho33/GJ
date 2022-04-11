package com.onestop.GJ.admin.board.notice.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.board.notice.dao.AdminNoticeDAO;
import com.onestop.GJ.admin.board.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.admin.board.notice.vo.AdminNoticeVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

@Service("adminNoticeService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminNoticeServiceImpl implements AdminNoticeService {
	@Autowired
	AdminNoticeDAO boardDAO;

	// 공지사항 관리 게시글 목록
	public Map listArticles(Map pagingMap) throws Exception {
		  Map articlesMap = new HashMap();
	      List<AdminNoticeVO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
	      int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("totArticles", totArticles);
	      return articlesMap;
	      
	      
	   }
	   
	// 공지사항 관리 게시글 검색 목록
	   @Override
		public Map searchBoardList(Map pagingMap) throws Exception{
		   Map articlesMap = new HashMap();
			List<AdminNoticeVO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
			int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
			int totArticles = boardDAO.selectTotArticles();
		      articlesMap.put("articlesList", articlesList);
		      articlesMap.put("searchTotArticles", searchTotArticles);
		      articlesMap.put("totArticles", totArticles);
			return articlesMap;
		}
	   
	// 공지사항 관리 게시글 추가
	   @Override
		public int addNewArticle(Map articleMap) throws Exception {
			int noti_NO = boardDAO.insertNewArticle(articleMap);
			articleMap.put("noti_NO", noti_NO);
			boardDAO.insertNewImage(articleMap);
			return noti_NO;
		}

	// 공지사항 관리 게시글 상세화면 
	   @Override
	   public Map viewArticle(int noti_NO) throws Exception {
	      Map articleMap = new HashMap();
	      AdminNoticeVO adminNoticeVO = boardDAO.selectArticle(noti_NO);
	      List<AdminNoticeImageVO> imageFileList = boardDAO.selectImageFileList(noti_NO);
	      articleMap.put("article", adminNoticeVO);
	      articleMap.put("imageFileList", imageFileList);

	   // 공지사항 관리 게시글 조회수 추가 
	      boardDAO.boardHits(noti_NO);
	      
	      return articleMap;
	      
	   }
	   
	// 공지사항 관리 게시글 삭제
	   @Override
	   public void removeArticle(int noti_NO) throws Exception {
	      boardDAO.deleteArticle(noti_NO);
	   }
	   
	// 공지사항 관리 게시글 수정 
	   @Override
	   public void modArticle(Map articleMap) throws Exception {
	      boardDAO.updateArticle(articleMap);
	      List<AdminNoticeImageVO> imageFileList = (List<AdminNoticeImageVO>)articleMap.get("imageFileList");
			List<AdminNoticeImageVO> modAddimageFileList = (List<AdminNoticeImageVO>)articleMap.get("modAddimageFileList");

			if(imageFileList != null && imageFileList.size() != 0) {
				int added_img_num = Integer.parseInt((String)articleMap.get("added_img_num"));
				int pre_img_num = Integer.parseInt((String)articleMap.get("pre_img_num"));

				if(pre_img_num < added_img_num) {  
					boardDAO.updateImageFile(articleMap);     //기존 이미지도 수정하고 새 이미지도 추가한 경우  
					boardDAO.insertModNewImage(articleMap);
				}else {
					boardDAO.updateImageFile(articleMap);  //기존의 이미지를 수정만 한 경우
				}
			}else if(modAddimageFileList != null && modAddimageFileList.size() != 0) {  //새 이미지를 추가한 경우
				boardDAO.insertModNewImage(articleMap);
			}
	   }


	// 공지사항 관리 게시글 수정(첨부파일 삭제)
		@Override
		public void removeModImage(AdminNoticeImageVO adminNoticeImageVO) throws Exception {
			boardDAO.deleteModImage(adminNoticeImageVO);
		}

	}
