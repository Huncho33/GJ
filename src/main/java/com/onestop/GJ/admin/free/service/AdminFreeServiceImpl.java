package com.onestop.GJ.admin.free.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.free.dao.AdminFreeDAO;
import com.onestop.GJ.admin.free.vo.AdminFreeImageVO;
import com.onestop.GJ.admin.free.vo.AdminFreeVO;


@Service("adminFreeService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminFreeServiceImpl implements AdminFreeService {
	
	@Autowired
	   AdminFreeDAO boardDAO;
	public Map listArticles(Map pagingMap) throws Exception {
		  Map articlesMap = new HashMap();
		  System.out.println("hi");
	      List<AdminFreeDAO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
	      System.out.println("free.service : "+ articlesList);
	      int totArticles = boardDAO.selectTotArticles();
	      articlesMap.put("articlesList", articlesList);
	      articlesMap.put("totArticles", totArticles);
	      
//	      articlesMap.put("totArticles", 170);
	      return articlesMap;
	   }
	 
	//�˻�â
	   @Override
		public Map searchBoardList(Map pagingMap) throws Exception{
		   Map articlesMap = new HashMap();
			List<AdminFreeDAO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
			int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
			int totArticles = boardDAO.selectTotArticles();
		      articlesMap.put("articlesList", articlesList);
		      articlesMap.put("searchTotArticles", searchTotArticles);
		      articlesMap.put("totArticles", totArticles);
//		      articlesMap.put("searchTotArticles", 170);
			return articlesMap;
		}
	   
	 //�� �߰�
	   @Override
		public int addNewArticle(Map articleMap) throws Exception {
			int fr_NO = boardDAO.insertNewArticle(articleMap);
			articleMap.put("fr_NO", fr_NO);
			boardDAO.insertNewImage(articleMap);
			return fr_NO;
		}
	   
	 //���� ���� ���̱�
	   @Override
	   public Map viewArticle(int fr_NO) throws Exception {
	      Map articleMap = new HashMap();
	      AdminFreeVO adminFreeVO = boardDAO.selectArticle(fr_NO);
	      List<AdminFreeImageVO> imageFileList = boardDAO.selectImageFileList(fr_NO);
	      articleMap.put("article", adminFreeVO);
	      articleMap.put("imageFileList", imageFileList);
	      Collection<String> value = articleMap.values();;
	      System.out.println(value);
	      
	      //��ȸ�� �߰��ϱ�
	      boardDAO.boardHits(fr_NO);
	      
	      return articleMap;
	      
	   }
	   
	   //�� ����
	   @Override
	   public void removeArticle(int fr_NO) throws Exception {
	      boardDAO.deleteArticle(fr_NO);
	   }
	   
	   //�� ����
	   @Override
	   public void modArticle(Map articleMap) throws Exception {
	      boardDAO.updateArticle(articleMap);
	      List<AdminFreeImageVO> imageFileList = (List<AdminFreeImageVO>)articleMap.get("imageFileList");
			List<AdminFreeImageVO> modAddimageFileList = (List<AdminFreeImageVO>)articleMap.get("modAddimageFileList");

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
		public void removeModImage(AdminFreeImageVO adminFreeImageVO) throws Exception {
			boardDAO.deleteModImage(adminFreeImageVO);
		}
}
