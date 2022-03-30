package com.onestop.GJ.admin.data.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.data.dao.AdminDataDAO;
import com.onestop.GJ.admin.data.vo.AdminDataImageVO;
import com.onestop.GJ.admin.data.vo.AdminDataVO;


@Service("adminDataService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminDataServiceImpl implements AdminDataService {
	
	 @Autowired
	   AdminDataDAO boardDAO;
	 
	 public Map listArticles(Map pagingMap) throws Exception {
		  Map articlesMap = new HashMap();
	      List<AdminDataVO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
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
			List<AdminDataVO> articlesList=boardDAO.selectBoardListBySearchWord(pagingMap);
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
			int etc_NO = boardDAO.insertNewArticle(articleMap);
			articleMap.put("etc_NO", etc_NO);
			boardDAO.insertNewImage(articleMap);
			return etc_NO;
		}
	   
	 //���� ���� ���̱�
	   @Override
	   public Map viewArticle(int etc_NO) throws Exception {
	      Map articleMap = new HashMap();
	      AdminDataVO adminDataVO = boardDAO.selectArticle(etc_NO);
	      List<AdminDataImageVO> imageFileList = boardDAO.selectImageFileList(etc_NO);
	      articleMap.put("article", adminDataVO);
	      articleMap.put("imageFileList", imageFileList);
	      Collection<String> value = articleMap.values();;
	      System.out.println(value);
	      
	      //��ȸ�� �߰��ϱ�
	      boardDAO.boardHits(etc_NO);
	      
	      return articleMap;
	      
	   }
	   
	   //�� ����
	   @Override
	   public void removeArticle(int etc_NO) throws Exception {
	      boardDAO.deleteArticle(etc_NO);
	   }
	   
	   //�� ����
	   @Override
	   public void modArticle(Map articleMap) throws Exception {
	      boardDAO.updateArticle(articleMap);
	      List<AdminDataImageVO> imageFileList = (List<AdminDataImageVO>)articleMap.get("imageFileList");
			List<AdminDataImageVO> modAddimageFileList = (List<AdminDataImageVO>)articleMap.get("modAddimageFileList");

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
		public void removeModImage(AdminDataImageVO adminDataImageVO) throws Exception {
			boardDAO.deleteModImage(adminDataImageVO);
		}
}
