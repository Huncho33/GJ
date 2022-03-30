package com.onestop.GJ.admin.free.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.free.vo.AdminFreeImageVO;
import com.onestop.GJ.admin.free.vo.AdminFreeVO;


@Repository("adminFreeDAO")
public class AdminFreeDAOImpl implements AdminFreeDAO {
	@Autowired
	  private SqlSession sqlSession;
	
	 @Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminFreeVO> articlesList = sqlSession.selectList("mapper.adminFree.selectAllArticlesList", pagingMap);
	      System.out.println("총 게시판 수 " +articlesList.size());
	      return articlesList;
	   }
	 
	//검색창
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminFreeVO> articlesList=sqlSession.selectList("mapper.adminFree.selectBoardListBySearchWord", pagingMap);
	 	     System.out.println("검색"+ articlesList.size());
	 		 return articlesList;
	 	}

	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int fr_NO = selectNewArticleNO();
	      articleMap.put("fr_NO", fr_NO);
	      Collection<String> value = articleMap.values();
	      System.out.println(value);
	      sqlSession.insert("mapper.adminFree.insertNewArticle",articleMap);
	      return fr_NO;
	   }
	   
	// 다중 파일 업로드
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
			System.out.println("imageFileList"+ imageFileList);
			sqlSession.insert("mapper.adminFree.insertNewImage", imageFileList);
		}
	}
	    		
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminFree.selectNewArticleNO");
	   }
	   
	//   상세보기
	   @Override
	   public AdminFreeVO selectArticle(int fr_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminFree.selectArticle", fr_NO);
	   }
	   

	   
		@Override
		public List selectImageFileList(int fr_NO) throws DataAccessException {
			List<AdminFreeImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminFree.selectImageFileList",fr_NO);
			return imageFileList;
		}
		
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminFree.selectNewImageFileNO");
		}



	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   Collection<String> value = articleMap.values();
		      System.out.println(value);
		   sqlSession.update("mapper.adminFree.updateArticle", articleMap);
	      
	   }

	   @Override
	   public void deleteArticle(int fr_NO) throws DataAccessException {
	      sqlSession.delete("mapper.adminFree.deleteArticle", fr_NO);
	   }

	   //조회수
	   @Override
		public void boardHits(int fr_NO) throws Exception {
		   
			sqlSession.update("mapper.adminFree.boardHits", fr_NO);
		}
	   
	   //페이징
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminFree.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	    //검색창 페이징
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminFree.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}

	   	@Override
		public void updateImageFile(Map articleMap) throws DataAccessException {
			
			List<AdminFreeImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
			int fr_NO = Integer.parseInt((String)articleMap.get("fr_NO"));
			
			for(int i = imageFileList.size()-1; i >= 0; i--){
				AdminFreeImageVO adminFreeImageVO = imageFileList.get(i);
				String up_fileName = adminFreeImageVO.getUp_fileName();
				if(up_fileName == null) {  //기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로  수정할 필요가 없다.
					imageFileList.remove(i);
				}else {
					adminFreeImageVO.setFr_NO(fr_NO);
				}
			}
			
			if(imageFileList != null && imageFileList.size() != 0) {
				System.out.println("imageFileList"+ imageFileList);
				sqlSession.update("mapper.adminFree.updateImageFile", imageFileList);
			}
	   	}
			
			@Override
			public void insertModNewImage(Map articleMap) throws DataAccessException {
				List<AdminFreeImageVO> modAddimageFileList = (ArrayList<AdminFreeImageVO>)articleMap.get("modAddimageFileList");
				int fr_NO = Integer.parseInt((String)articleMap.get("fr_NO"));
				System.out.println("modAddimageFileList"+ modAddimageFileList);
				int up_fileNO = selectNewImageFileNO();
				
				for(AdminFreeImageVO adminFreeImageVO : modAddimageFileList){
					adminFreeImageVO.setFr_NO(fr_NO);
					adminFreeImageVO.setUp_fileNO(++up_fileNO);
				}
				
				sqlSession.insert("mapper.adminFree.insertModNewImage", modAddimageFileList );
				
			}
			
		
	   	
	   	@Override
		public void deleteModImage(AdminFreeImageVO adminFreeImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminFree.deleteModImage", adminFreeImageVO );
			
		}
}
