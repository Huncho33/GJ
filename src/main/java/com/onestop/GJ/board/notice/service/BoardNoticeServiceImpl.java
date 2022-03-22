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



@Service("boardNoticeService")
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
      System.out.println("�۸�� : " + articlesList);
//      articlesMap.put("totArticles", 170);
      return articlesMap;
      
      
   }
   
   //�˻�â
   @Override
	public Map searchBoardList(Map pagingMap) throws Exception{
	   Map articlesMap = new HashMap();
		List<BoardNoticeVO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
		int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("searchTotArticles", searchTotArticles);
	      articlesMap.put("totArticles", totArticles);
//	      articlesMap.put("searchTotArticles", 170);
		return articlesMap;
	}
   
   //�� �߰�
   @Override
	public int addNewArticle(Map articleMap) throws Exception {
		int noti_NO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("noti_NO", noti_NO);
		boardDAO.insertNewImage(articleMap);
		return noti_NO;
	}

//   // ���� ���� ���̱�
//   @Override
//   public BoardNoticeVO viewArticle(int noti_NO) throws Exception {
//      BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
//      return boardNoticeVO;
//   }
    
   //���� ���� ���̱�
   @Override
   public Map viewArticle(int noti_NO) throws Exception {
      Map articleMap = new HashMap();
      BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
      List<BoardNoticeImageVO> imageFileList = boardDAO.selectImageFileList(noti_NO);
      articleMap.put("article", boardNoticeVO);
      articleMap.put("imageFileList", imageFileList);
      Collection<String> value = articleMap.values();
//      System.out.println(value);
      
      //��ȸ�� �߰��ϱ�
      boardDAO.boardHits(noti_NO);
      
      return articleMap;
      
   }
   
   //�� ����
   @Override
   public void removeArticle(int noti_NO) throws Exception {
      boardDAO.deleteArticle(noti_NO);
   }
   
   //�� ����
   @Override
   public void modArticle(Map articleMap) throws Exception {
      boardDAO.updateArticle(articleMap);
      List<BoardNoticeImageVO> imageFileList = (List<BoardNoticeImageVO>)articleMap.get("imageFileList");
		List<BoardNoticeImageVO> modAddimageFileList = (List<BoardNoticeImageVO>)articleMap.get("modAddimageFileList");

		if(imageFileList != null && imageFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String)articleMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String)articleMap.get("pre_img_num"));

			if(pre_img_num < added_img_num) {  
				boardDAO.updateImageFile(articleMap);     //���� �̹����� �����ϰ� �� �̹����� �߰��� ���  
				boardDAO.insertModNewImage(articleMap);
			}else {
				boardDAO.updateImageFile(articleMap);  //������ �̹����� ������ �� ���
			}
		}else if(modAddimageFileList != null && modAddimageFileList.size() != 0) {  //�� �̹����� �߰��� ���
			boardDAO.insertModNewImage(articleMap);
		}
   }


   //�̹��� ���� (����)
	@Override
	public void removeModImage(BoardNoticeImageVO boardNoticeImageVO) throws Exception {
		boardDAO.deleteModImage(boardNoticeImageVO);
	}
	

}