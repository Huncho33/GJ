package com.onestop.GJ.admin.board.free.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.board.free.dao.AdminFreeDAO;
import com.onestop.GJ.admin.board.free.vo.AdminFreeImageVO;
import com.onestop.GJ.admin.board.free.vo.AdminFreeVO;


@Service("adminFreeService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminFreeServiceImpl implements AdminFreeService {
	
	@Autowired
	   AdminFreeDAO boardDAO;
	
	// 자유게시판 관리 게시글 목록
	public Map listArticles(Map pagingMap) throws Exception {
		  Map articlesMap = new HashMap();
	      List<AdminFreeDAO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
	      int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("totArticles", totArticles);
	      return articlesMap;
	   }
	 
	// 자유게시판 관리 게시글 검색 목록 
	   @Override
		public Map searchBoardList(Map pagingMap) throws Exception{
		   Map articlesMap = new HashMap();
			List<AdminFreeDAO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
			int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
			int totArticles = boardDAO.selectTotArticles();
		      articlesMap.put("articlesList", articlesList);
		      articlesMap.put("searchTotArticles", searchTotArticles);
		      articlesMap.put("totArticles", totArticles);
			return articlesMap;
		}
	   
	// 자유게시판 관리 게시글 추가
	   @Override
		public int addNewArticle(Map articleMap) throws Exception {
			int fr_NO = boardDAO.insertNewArticle(articleMap);
			articleMap.put("fr_NO", fr_NO);
			boardDAO.insertNewImage(articleMap);
			return fr_NO;
		}
	   
	// 자유게시판 관리 게시글 상세화면 
	   @Override
	   public Map viewArticle(int fr_NO) throws Exception {
	      Map articleMap = new HashMap();
	      AdminFreeVO adminFreeVO = boardDAO.selectArticle(fr_NO);
	      List<AdminFreeImageVO> imageFileList = boardDAO.selectImageFileList(fr_NO);
	      articleMap.put("article", adminFreeVO);
	      articleMap.put("imageFileList", imageFileList);
	      
	   // 자유게시판 관리 게시글 조회수 추가 
	      boardDAO.boardHits(fr_NO);
	      
	      return articleMap;
	      
	   }
	   
	// 자유게시판 관리 게시글 삭제
	   @Override
	   public void removeArticle(int fr_NO) throws Exception {
	      boardDAO.deleteArticle(fr_NO);
	   }
	   
	// 자유게시판 관리 게시글 목수정 
	   @Override
	   public void modArticle(Map articleMap) throws Exception {
	      boardDAO.updateArticle(articleMap);
	      List<AdminFreeImageVO> imageFileList = (List<AdminFreeImageVO>)articleMap.get("imageFileList");
			List<AdminFreeImageVO> modAddimageFileList = (List<AdminFreeImageVO>)articleMap.get("modAddimageFileList");

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


	// 자유게시판 관리 게시글 수정(첨부파일 삭제)
		@Override
		public void removeModImage(AdminFreeImageVO adminFreeImageVO) throws Exception {
			boardDAO.deleteModImage(adminFreeImageVO);
		}
}
