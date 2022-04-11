package com.onestop.GJ.admin.board.notice.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.board.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.admin.board.notice.vo.AdminNoticeVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

@Repository("adminNoticeDAO")
public class AdminNoticeDAOImpl implements AdminNoticeDAO {
	
	@Autowired
	   private SqlSession sqlSession;
	
	// �������� ���� �Խñ� ���
	@Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminNoticeVO> articlesList = sqlSession.selectList("mapper.adminNotice.selectAllArticlesList", pagingMap);
	      return articlesList;
	   }
	   
	// �������� ���� �Խñ� �˻� ��� 
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminNoticeVO> articlesList=sqlSession.selectList("mapper.adminNotice.selectBoardListBySearchWord", pagingMap);
	 		 return articlesList;
	 	}

	 // �������� ���� �Խñ� �߰� 
	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int noti_NO = selectNewArticleNO();
	      articleMap.put("noti_NO", noti_NO);
	      sqlSession.insert("mapper.adminNotice.insertNewArticle",articleMap);
	      return noti_NO;
	   }
	   
	// �������� ���� �Խñ� ÷������ �߰� 
		@Override
		public void insertNewImage(Map articleMap) throws DataAccessException {
			List<AdminNoticeImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
			int noti_NO = (Integer) articleMap.get("noti_NO");
			int up_fileNO = selectNewImageFileNO();
			 if (imageFileList != null && imageFileList.size() != 0) {

			for (AdminNoticeImageVO adminNoticeImageVO : imageFileList) {
				adminNoticeImageVO.setUp_fileNO(++up_fileNO);
				adminNoticeImageVO.setNoti_NO(noti_NO);
			}
			sqlSession.insert("mapper.adminNotice.insertNewImage", imageFileList);
		}
	}
		// �������� ���� �Խñ� ��ȣ ���� 	
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminNotice.selectNewArticleNO");
	   }
	   
	// �������� ���� �Խñ� ��ȭ��
	   @Override
	   public AdminNoticeVO selectArticle(int noti_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminNotice.selectArticle", noti_NO);
	   }
	   

	// �������� ���� �Խñ� ��ȭ�� (÷������)
		@Override
		public List selectImageFileList(int noti_NO) throws DataAccessException {
			List<AdminNoticeImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminNotice.selectImageFileList",noti_NO);
			return imageFileList;
		}
		
		// �������� ���� �Խñ� ÷������ ��ȣ ����  
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminNotice.selectNewImageFileNO");
		}


		// �������� ���� �Խñ� ���� 
	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   sqlSession.update("mapper.adminNotice.updateArticle", articleMap);
	      
	   }
	   
	   // �������� ���� �Խñ� ���� 
	   @Override
	   public void deleteArticle(int noti_NO) throws DataAccessException {
	      sqlSession.delete("mapper.adminNotice.deleteArticle", noti_NO);
	   }

	// �������� ���� �Խñ� ��ȸ�� �߰� 
	   @Override
		public void boardHits(int noti_NO) throws Exception {
			sqlSession.update("mapper.adminNotice.boardHits", noti_NO);
		}
	   
	   // �������� ���� �Խñ� ���� 
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminNotice.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	 // �������� ���� �Խñ� �˻� ���� 
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminNotice.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}

	 // �������� ���� �Խñ� ����(÷������ ����)
	   	@Override
		public void updateImageFile(Map articleMap) throws DataAccessException {
			
			List<AdminNoticeImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
			int noti_NO = Integer.parseInt((String)articleMap.get("noti_NO"));
			
			for(int i = imageFileList.size()-1; i >= 0; i--){
				AdminNoticeImageVO adminNoticeImageVO = imageFileList.get(i);
				String up_fileName = adminNoticeImageVO.getUp_fileName();
				if(up_fileName == null) {  //������ �̹����� �������� �ʴ� ��� ���ϸ��� null �̹Ƿ�  ������ �ʿ䰡 ����.
					imageFileList.remove(i);
				}else {
					adminNoticeImageVO.setNoti_NO(noti_NO);
				}
			}
			
			if(imageFileList != null && imageFileList.size() != 0) {
				System.out.println("imageFileList"+ imageFileList);
				sqlSession.update("mapper.adminNotice.updateImageFile", imageFileList);
			}
	   	}
			
	 // �������� ���� �Խñ� ����(÷������ �߰�)
			@Override
			public void insertModNewImage(Map articleMap) throws DataAccessException {
				List<AdminNoticeImageVO> modAddimageFileList = (ArrayList<AdminNoticeImageVO>)articleMap.get("modAddimageFileList");
				int noti_NO = Integer.parseInt((String)articleMap.get("noti_NO"));
				int up_fileNO = selectNewImageFileNO();
				
				for(AdminNoticeImageVO adminNoticeImageVO : modAddimageFileList){
					adminNoticeImageVO.setNoti_NO(noti_NO);
					adminNoticeImageVO.setUp_fileNO(++up_fileNO);
				}
				sqlSession.insert("mapper.adminNotice.insertModNewImage", modAddimageFileList );
			}
			
		
		// �������� ���� �Խñ� ����(÷������ ����)
	   	@Override
		public void deleteModImage(AdminNoticeImageVO adminNoticeImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminNotice.deleteModImage", adminNoticeImageVO );
			
		}
	   	
}
