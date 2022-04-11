package com.onestop.GJ.admin.board.free.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.board.free.vo.AdminFreeImageVO;
import com.onestop.GJ.admin.board.free.vo.AdminFreeVO;


@Repository("adminFreeDAO")
public class AdminFreeDAOImpl implements AdminFreeDAO {
	@Autowired
	  private SqlSession sqlSession;
	
	// �����Խ��� ���� �Խñ� ���
	 @Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminFreeVO> articlesList = sqlSession.selectList("mapper.adminFree.selectAllArticlesList", pagingMap);
	      return articlesList;
	   }
	 
	// �����Խ��� ���� �Խñ�  �˻� ���
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminFreeVO> articlesList=sqlSession.selectList("mapper.adminFree.selectBoardListBySearchWord", pagingMap);
	 		 return articlesList;
	 	}

	 // �����Խ��� ���� �Խñ� �߰� 
	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int fr_NO = selectNewArticleNO();
	      articleMap.put("fr_NO", fr_NO);
	      sqlSession.insert("mapper.adminFree.insertNewArticle",articleMap);
	      return fr_NO;
	   }
	   
	// �����Խ��� ���� �Խñ� ÷������ �߰� 
		@Override
		public void insertNewImage(Map articleMap) throws DataAccessException {
			List<AdminFreeImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
			int fr_NO = (Integer) articleMap.get("fr_NO");
			int up_fileNO = selectNewImageFileNO();
			 if (imageFileList != null && imageFileList.size() != 0) {

			for (AdminFreeImageVO adminFreeImageVO : imageFileList) {
				adminFreeImageVO.setUp_fileNO(++up_fileNO);
				adminFreeImageVO.setFr_NO(fr_NO);
			}
			sqlSession.insert("mapper.adminFree.insertNewImage", imageFileList);
		}
	}
		// �����Խ��� ���� �Խñ� ��ȣ ���� 
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminFree.selectNewArticleNO");
	   }
	   
	// �����Խ��� ���� �Խñ� ��ȭ�� 
	   @Override
	   public AdminFreeVO selectArticle(int fr_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminFree.selectArticle", fr_NO);
	   }
	   

	// �����Խ��� ���� �Խñ� ��ȭ��(÷������ ���̱�)
		@Override
		public List selectImageFileList(int fr_NO) throws DataAccessException {
			List<AdminFreeImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminFree.selectImageFileList",fr_NO);
			return imageFileList;
		}
		
		// �����Խ��� ���� �Խñ� ÷������ ��ȣ ���� 
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminFree.selectNewImageFileNO");
		}


		// �����Խ��� ���� �Խñ� ���� 
	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   sqlSession.update("mapper.adminFree.updateArticle", articleMap);
	      
	   }

	// �����Խ��� ���� �Խñ� ����
	   @Override
	   public void deleteArticle(int fr_NO) throws DataAccessException {
	      sqlSession.delete("mapper.adminFree.deleteArticle", fr_NO);
	   }

	// �����Խ��� ���� �Խñ� ��ȸ�� �߰� 
	   @Override
		public void boardHits(int fr_NO) throws Exception {
			sqlSession.update("mapper.adminFree.boardHits", fr_NO);
		}
	   
	// �����Խ��� ���� �Խñ� ���� 
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminFree.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	 // �����Խ��� ���� �Խñ� �˻� ���� 
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminFree.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}

	 // �����Խ��� ���� �Խñ� ����(÷������ ����)
	   	@Override
		public void updateImageFile(Map articleMap) throws DataAccessException {
			
			List<AdminFreeImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
			int fr_NO = Integer.parseInt((String)articleMap.get("fr_NO"));
			
			for(int i = imageFileList.size()-1; i >= 0; i--){
				AdminFreeImageVO adminFreeImageVO = imageFileList.get(i);
				String up_fileName = adminFreeImageVO.getUp_fileName();
				if(up_fileName == null) {  //������ �̹����� �������� �ʴ� ��� ���ϸ��� null �̹Ƿ�  ������ �ʿ䰡 ����.
					imageFileList.remove(i);
				}else {
					adminFreeImageVO.setFr_NO(fr_NO);
				}
			}
			if(imageFileList != null && imageFileList.size() != 0) {
				sqlSession.update("mapper.adminFree.updateImageFile", imageFileList);
			}
	   	}
			
	 // �����Խ��� ���� �Խñ� ����(÷������ �߰�)
			@Override
			public void insertModNewImage(Map articleMap) throws DataAccessException {
				List<AdminFreeImageVO> modAddimageFileList = (ArrayList<AdminFreeImageVO>)articleMap.get("modAddimageFileList");
				int fr_NO = Integer.parseInt((String)articleMap.get("fr_NO"));
				int up_fileNO = selectNewImageFileNO();
				
				for(AdminFreeImageVO adminFreeImageVO : modAddimageFileList){
					adminFreeImageVO.setFr_NO(fr_NO);
					adminFreeImageVO.setUp_fileNO(++up_fileNO);
				}
				
				sqlSession.insert("mapper.adminFree.insertModNewImage", modAddimageFileList );
				
			}
			
		
		// �����Խ��� ���� �Խñ� ����(÷������ ����)
	   	@Override
		public void deleteModImage(AdminFreeImageVO adminFreeImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminFree.deleteModImage", adminFreeImageVO );
			
		}
}
