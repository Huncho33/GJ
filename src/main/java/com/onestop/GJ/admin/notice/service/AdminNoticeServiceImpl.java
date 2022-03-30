package com.onestop.GJ.admin.notice.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.notice.dao.AdminNoticeDAO;
import com.onestop.GJ.admin.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.admin.notice.vo.AdminNoticeVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

@Service("adminNoticeService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminNoticeServiceImpl implements AdminNoticeService {
	@Autowired
	AdminNoticeDAO boardDAO;


	public Map listArticles(Map pagingMap) throws Exception {
		  Map articlesMap = new HashMap();
	      List<AdminNoticeVO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
	      int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("totArticles", totArticles);
	      System.out.println("글목록 : " + articlesList);
//	      articlesMap.put("totArticles", 170);
	      return articlesMap;
	      
	      
	   }
	   
	   //검색창
	   @Override
		public Map searchBoardList(Map pagingMap) throws Exception{
		   Map articlesMap = new HashMap();
			List<AdminNoticeVO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
			int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
			int totArticles = boardDAO.selectTotArticles();
		      articlesMap.put("articlesList", articlesList);
		      articlesMap.put("searchTotArticles", searchTotArticles);
		      articlesMap.put("totArticles", totArticles);
//		      articlesMap.put("searchTotArticles", 170);
			return articlesMap;
		}
	   
	   //글 추가
	   @Override
		public int addNewArticle(Map articleMap) throws Exception {
			int noti_NO = boardDAO.insertNewArticle(articleMap);
			articleMap.put("noti_NO", noti_NO);
			boardDAO.insertNewImage(articleMap);
			return noti_NO;
		}

	//   // 단일 파일 보이기
	//   @Override
	//   public BoardNoticeVO viewArticle(int noti_NO) throws Exception {
//	      BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
//	      return boardNoticeVO;
	//   }
	    
	   //다중 파일 보이기
	   @Override
	   public Map viewArticle(int noti_NO) throws Exception {
	      Map articleMap = new HashMap();
	      AdminNoticeVO adminNoticeVO = boardDAO.selectArticle(noti_NO);
	      List<AdminNoticeImageVO> imageFileList = boardDAO.selectImageFileList(noti_NO);
	      articleMap.put("article", adminNoticeVO);
	      articleMap.put("imageFileList", imageFileList);
	      Collection<String> value = articleMap.values();
//	      System.out.println(value);
	      
	      //조회수 추가하기
	      boardDAO.boardHits(noti_NO);
	      
	      return articleMap;
	      
	   }
	   
	   //글 삭제
	   @Override
	   public void removeArticle(int noti_NO) throws Exception {
	      boardDAO.deleteArticle(noti_NO);
	   }
	   
	   //글 수정
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


	   //이미지 삭제 (수정)
		@Override
		public void removeModImage(AdminNoticeImageVO adminNoticeImageVO) throws Exception {
			boardDAO.deleteModImage(adminNoticeImageVO);
		}

		@Override
		public boolean modalCheck(int noti_no) throws Exception {
			System.out.println("noti_no:" + noti_no );
			return boardDAO.checkPwd(noti_no);
		}

	}
