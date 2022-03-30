package com.onestop.GJ.admin.data.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.data.vo.AdminDataImageVO;
import com.onestop.GJ.admin.data.vo.AdminDataVO;


@Repository("adminDataDAO")

public class AdminDataDAOImpl implements AdminDataDAO {
	 @Autowired
	  private SqlSession sqlSession;
	
	 @Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminDataVO> articlesList = sqlSession.selectList("mapper.adminData.selectAllArticlesList", pagingMap);
	      System.out.println("�� �Խ��� �� " +articlesList.size());
	      return articlesList;
	   }
	 
	//�˻�â
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminDataVO> articlesList=sqlSession.selectList("mapper.adminData.selectBoardListBySearchWord", pagingMap);
	 	     System.out.println("�˻�"+ articlesList.size());
	 		 return articlesList;
	 	}

	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int etc_NO = selectNewArticleNO();
	      articleMap.put("etc_NO", etc_NO);
	      Collection<String> value = articleMap.values();
	      System.out.println(value);
	      sqlSession.insert("mapper.adminData.insertNewArticle",articleMap);
	      return etc_NO;
	   }
	   
	// ���� ���� ���ε�
		@Override
		public void insertNewImage(Map articleMap) throws DataAccessException {
			List<AdminDataImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
			int etc_NO = (Integer) articleMap.get("etc_NO");
			int up_fileNO = selectNewImageFileNO();
			 if (imageFileList != null && imageFileList.size() != 0) {

			for (AdminDataImageVO adminDataImageVO : imageFileList) {
				adminDataImageVO.setUp_fileNO(++up_fileNO);
				adminDataImageVO.setEtc_NO(etc_NO);
			}
			System.out.println("imageFileList"+ imageFileList);
			sqlSession.insert("mapper.adminData.insertNewImage", imageFileList);
		}
	}
	    		
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminData.selectNewArticleNO");
	   }
	   
	//   �󼼺���
	   @Override
	   public AdminDataVO selectArticle(int etc_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminData.selectArticle", etc_NO);
	   }
	   

	   
		@Override
		public List selectImageFileList(int etc_NO) throws DataAccessException {
			List<AdminDataImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminData.selectImageFileList",etc_NO);
			return imageFileList;
		}
		
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminData.selectNewImageFileNO");
		}



	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   Collection<String> value = articleMap.values();
		      System.out.println(value);
		   sqlSession.update("mapper.adminData.updateArticle", articleMap);
	      
	   }

	   @Override
	   public void deleteArticle(int etc_NO) throws DataAccessException {
	      sqlSession.delete("mapper.adminData.deleteArticle", etc_NO);
	   }

	   //��ȸ��
	   @Override
		public void boardHits(int etc_NO) throws Exception {
		   
			sqlSession.update("mapper.adminData.boardHits", etc_NO);
		}
	   
	   //����¡
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminData.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	    //�˻�â ����¡
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminData.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}

	   	@Override
		public void updateImageFile(Map articleMap) throws DataAccessException {
			
			List<AdminDataImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
			int etc_NO = Integer.parseInt((String)articleMap.get("etc_NO"));
			
			for(int i = imageFileList.size()-1; i >= 0; i--){
				AdminDataImageVO adminDataImageVO = imageFileList.get(i);
				String up_fileName = adminDataImageVO.getUp_fileName();
				if(up_fileName == null) {  //������ �̹����� �������� �ʴ� ��� ���ϸ��� null �̹Ƿ�  ������ �ʿ䰡 ����.
					imageFileList.remove(i);
				}else {
					adminDataImageVO.setEtc_NO(etc_NO);
				}
			}
			
			if(imageFileList != null && imageFileList.size() != 0) {
				System.out.println("imageFileList"+ imageFileList);
				sqlSession.update("mapper.adminData.updateImageFile", imageFileList);
			}
	   	}
			
			@Override
			public void insertModNewImage(Map articleMap) throws DataAccessException {
				List<AdminDataImageVO> modAddimageFileList = (ArrayList<AdminDataImageVO>)articleMap.get("modAddimageFileList");
				int etc_NO = Integer.parseInt((String)articleMap.get("etc_NO"));
				System.out.println("modAddimageFileList"+ modAddimageFileList);
				int up_fileNO = selectNewImageFileNO();
				
				for(AdminDataImageVO adminDataImageVO : modAddimageFileList){
					adminDataImageVO.setEtc_NO(etc_NO);
					adminDataImageVO.setUp_fileNO(++up_fileNO);
				}
				
				sqlSession.insert("mapper.adminData.insertModNewImage", modAddimageFileList );
				
			}
			
		
	   	
	   	@Override
		public void deleteModImage(AdminDataImageVO adminDataImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminData.deleteModImage", adminDataImageVO );
			
		}



}
