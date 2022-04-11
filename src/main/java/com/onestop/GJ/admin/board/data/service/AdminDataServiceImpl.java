package com.onestop.GJ.admin.board.data.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.board.data.dao.AdminDataDAO;
import com.onestop.GJ.admin.board.data.vo.AdminDataImageVO;
import com.onestop.GJ.admin.board.data.vo.AdminDataVO;


@Service("adminDataService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminDataServiceImpl implements AdminDataService {
	
	 @Autowired
	   AdminDataDAO boardDAO;
	 
	 // 기타자료실 관리 게시글 목록 
	 public Map listArticles(Map pagingMap) throws Exception {
		  Map articlesMap = new HashMap();
	      List<AdminDataVO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
	      int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("totArticles", totArticles);
	      return articlesMap;
	   }
	 
	// 기타자료실 관리 게시글 검색 목록 
	   @Override
		public Map searchBoardList(Map pagingMap) throws Exception{
		   Map articlesMap = new HashMap();
			List<AdminDataVO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
			int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
			int totArticles = boardDAO.selectTotArticles();
		      articlesMap.put("articlesList", articlesList);
		      articlesMap.put("searchTotArticles", searchTotArticles);
		      articlesMap.put("totArticles", totArticles);
			return articlesMap;
		}
	   
	// 기타자료실 관리 게시글 추가 
	   @Override
		public int addNewArticle(Map articleMap) throws Exception {
			int etc_NO = boardDAO.insertNewArticle(articleMap);
			articleMap.put("etc_NO", etc_NO);
			boardDAO.insertNewImage(articleMap);
			return etc_NO;
		}
	   
	// 기타자료실 관리 게시글 상세 화면 
	   @Override
	   public Map viewArticle(int etc_NO) throws Exception {
	      Map articleMap = new HashMap();
	      AdminDataVO adminDataVO = boardDAO.selectArticle(etc_NO);
	      List<AdminDataImageVO> imageFileList = boardDAO.selectImageFileList(etc_NO);
	      articleMap.put("article", adminDataVO);
	      articleMap.put("imageFileList", imageFileList);
	      Collection<String> value = articleMap.values();;
	      System.out.println(value);
	      
	    // 기타자료실 관리 게시글 조회수 추가 
	      boardDAO.boardHits(etc_NO);
	      
	      return articleMap;
	      
	   }
	   
	// 기타자료실 관리 게시글 삭제
	   @Override
	   public void removeArticle(int etc_NO) throws Exception {
	      boardDAO.deleteArticle(etc_NO);
	   }
	   
	// 기타자료실 관리 게시글 수정
	   @Override
	   public void modArticle(Map articleMap) throws Exception {
	      boardDAO.updateArticle(articleMap);
	      List<AdminDataImageVO> imageFileList = (List<AdminDataImageVO>)articleMap.get("imageFileList");
			List<AdminDataImageVO> modAddimageFileList = (List<AdminDataImageVO>)articleMap.get("modAddimageFileList");

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


	// 기타자료실 관리 게시글 수정(첨부파일 삭제)
		@Override
		public void removeModImage(AdminDataImageVO adminDataImageVO) throws Exception {
			boardDAO.deleteModImage(adminDataImageVO);
		}
}
