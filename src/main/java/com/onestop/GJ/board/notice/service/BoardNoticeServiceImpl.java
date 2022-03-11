package com.onestop.GJ.board.notice.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.board.notice.dao.BoardNoticeDAO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;



@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardNoticeServiceImpl implements BoardNoticeService {
   @Autowired
   BoardNoticeDAO boardDAO;

   public Map listArticles(Map pagingMap) throws Exception {
	  Map articlesMap = new HashMap();
      List<BoardNoticeVO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
      int totArticles = boardDAO.selectTotArticles();
      articlesMap.put("articlesList", articlesList);
      articlesMap.put("totArticles", totArticles);
      articlesMap.put("totArticles", 170);
      return articlesMap;
   }
   
   //검색창
   @Override
	public Map searchBoardList(Map pagingMap) throws Exception{
	   Map articlesMap = new HashMap();
		List<BoardNoticeVO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("totArticles", totArticles);
	      articlesMap.put("totArticles", 170);
		return articlesMap;
	}

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
//      BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
//      return boardNoticeVO;
//   }
    
   //다중 파일 보이기
   @Override
   public Map viewArticle(int noti_NO) throws Exception {
      Map articleMap = new HashMap();
      BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
      List<BoardNoticeImageVO> imageFileList = boardDAO.selectImageFileList(noti_NO);
      articleMap.put("article", boardNoticeVO);
      articleMap.put("imageFileList", imageFileList);
      Collection<String> value = articleMap.values();
      System.out.println(value);
      //조회수 추가하기
      boardDAO.boardHits(noti_NO);
      
      return articleMap;
      
   }

   
   @Override
   public void modArticle(Map articleMap) throws Exception {
      boardDAO.updateArticle(articleMap);
   }

   @Override
   public void removeArticle(int noti_NO) throws Exception {
      boardDAO.deleteArticle(noti_NO);
   }
   
   //키워드
//	public List<String> keywordSearch(String keyword) throws Exception {
//		List<String> list=goodsDAO.selectKeywordSearch(keyword);
//		return list;
//	}
	

   

   
}