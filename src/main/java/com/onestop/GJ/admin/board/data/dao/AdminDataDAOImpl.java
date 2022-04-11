package com.onestop.GJ.admin.board.data.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.board.data.vo.AdminDataImageVO;
import com.onestop.GJ.admin.board.data.vo.AdminDataVO;


@Repository("adminDataDAO")

public class AdminDataDAOImpl implements AdminDataDAO {
	 @Autowired
	  private SqlSession sqlSession;
	
	 // ��Ÿ�ڷ�� ���� �Խñ� ���
	 @Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminDataVO> articlesList = sqlSession.selectList("mapper.adminData.selectAllArticlesList", pagingMap);
	      return articlesList;
	   }
	 
	 // ��Ÿ�ڷ�� ���� �Խñ� �˻� ���
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminDataVO> articlesList=sqlSession.selectList("mapper.adminData.selectBoardListBySearchWord", pagingMap);
	 		 return articlesList;
	 	}

	 	// ��Ÿ�ڷ�� ���� �Խñ� �߰� 
	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int etc_NO = selectNewArticleNO();
	      articleMap.put("etc_NO", etc_NO);
	      sqlSession.insert("mapper.adminData.insertNewArticle",articleMap);
	      return etc_NO;
	   }
	   
	   // ��Ÿ�ڷ�� ���� �Խñ� ÷������ �߰�
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
			sqlSession.insert("mapper.adminData.insertNewImage", imageFileList);
		}
	}
		// ��Ÿ�ڷ�� ���� �Խñ� ��ȣ ����		
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminData.selectNewArticleNO");
	   }
	   
	   // ��Ÿ�ڷ�� ���� �Խñ� ��ȭ��
	   @Override
	   public AdminDataVO selectArticle(int etc_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminData.selectArticle", etc_NO);
	   }
	   

	   // ��Ÿ�ڷ�� ���� �Խñ� ��ȭ�� ÷������ �ҷ�����
		@Override
		public List selectImageFileList(int etc_NO) throws DataAccessException {
			List<AdminDataImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminData.selectImageFileList",etc_NO);
			return imageFileList;
		}
		
		// ��Ÿ�ڷ�� ���� ÷������ ��ȣ ���� 
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminData.selectNewImageFileNO");
		}


		// ��Ÿ�ڷ�� ���� �Խñ� ���� 
	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   sqlSession.update("mapper.adminData.updateArticle", articleMap);
	      
	   }

	   // ��Ÿ�ڷ�� �Խñ� ���� 
	   @Override
	   public void deleteArticle(int etc_NO) throws DataAccessException {
	      sqlSession.delete("mapper.adminData.deleteArticle", etc_NO);
	   }

	// ��Ÿ�ڷ�� ���� �Խñ�  ��ȸ�� 
	   @Override
		public void boardHits(int etc_NO) throws Exception {
		   
			sqlSession.update("mapper.adminData.boardHits", etc_NO);
		}
	   
	   //��Ÿ�ڷ�� ��ü �Խù� ���� Ȯ�� 
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminData.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	    //��Ÿ�ڷ�� �˻� �Խù� ���� Ȯ�� 
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminData.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}
	   	
	   	// ��Ÿ�ڷ�� �Խñ� ÷������ ���� 
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
				sqlSession.update("mapper.adminData.updateImageFile", imageFileList);
			}
	   	}
			//��Ÿ�ڷ�� �Խñ� ÷������ ����(�߰�)
			@Override
			public void insertModNewImage(Map articleMap) throws DataAccessException {
				List<AdminDataImageVO> modAddimageFileList = (ArrayList<AdminDataImageVO>)articleMap.get("modAddimageFileList");
				int etc_NO = Integer.parseInt((String)articleMap.get("etc_NO"));
				int up_fileNO = selectNewImageFileNO();
				
				for(AdminDataImageVO adminDataImageVO : modAddimageFileList){
					adminDataImageVO.setEtc_NO(etc_NO);
					adminDataImageVO.setUp_fileNO(++up_fileNO);
				}
				
				sqlSession.insert("mapper.adminData.insertModNewImage", modAddimageFileList );
				
			}
			
		
	   	// ��Ÿ�ڷ�� ���� �Խñ� ÷������ ����(����)
	   	@Override
		public void deleteModImage(AdminDataImageVO adminDataImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminData.deleteModImage", adminDataImageVO );
			
		}



}
