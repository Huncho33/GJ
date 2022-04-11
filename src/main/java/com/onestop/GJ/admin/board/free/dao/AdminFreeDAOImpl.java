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
	
	// 자유게시판 관리 게시글 목록
	 @Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminFreeVO> articlesList = sqlSession.selectList("mapper.adminFree.selectAllArticlesList", pagingMap);
	      return articlesList;
	   }
	 
	// 자유게시판 관리 게시글  검색 목록
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminFreeVO> articlesList=sqlSession.selectList("mapper.adminFree.selectBoardListBySearchWord", pagingMap);
	 		 return articlesList;
	 	}

	 // 자유게시판 관리 게시글 추가 
	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int fr_NO = selectNewArticleNO();
	      articleMap.put("fr_NO", fr_NO);
	      sqlSession.insert("mapper.adminFree.insertNewArticle",articleMap);
	      return fr_NO;
	   }
	   
	// 자유게시판 관리 게시글 첨부파일 추가 
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
		// 자유게시판 관리 게시글 번호 생성 
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminFree.selectNewArticleNO");
	   }
	   
	// 자유게시판 관리 게시글 상세화면 
	   @Override
	   public AdminFreeVO selectArticle(int fr_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminFree.selectArticle", fr_NO);
	   }
	   

	// 자유게시판 관리 게시글 상세화면(첨부파일 보이기)
		@Override
		public List selectImageFileList(int fr_NO) throws DataAccessException {
			List<AdminFreeImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminFree.selectImageFileList",fr_NO);
			return imageFileList;
		}
		
		// 자유게시판 관리 게시글 첨부파일 번호 생성 
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminFree.selectNewImageFileNO");
		}


		// 자유게시판 관리 게시글 수정 
	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   sqlSession.update("mapper.adminFree.updateArticle", articleMap);
	      
	   }

	// 자유게시판 관리 게시글 삭제
	   @Override
	   public void deleteArticle(int fr_NO) throws DataAccessException {
	      sqlSession.delete("mapper.adminFree.deleteArticle", fr_NO);
	   }

	// 자유게시판 관리 게시글 조회수 추가 
	   @Override
		public void boardHits(int fr_NO) throws Exception {
			sqlSession.update("mapper.adminFree.boardHits", fr_NO);
		}
	   
	// 자유게시판 관리 게시글 개수 
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminFree.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	 // 자유게시판 관리 게시글 검색 개수 
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminFree.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}

	 // 자유게시판 관리 게시글 수정(첨부파일 수정)
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
				sqlSession.update("mapper.adminFree.updateImageFile", imageFileList);
			}
	   	}
			
	 // 자유게시판 관리 게시글 수정(첨부파일 추가)
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
			
		
		// 자유게시판 관리 게시글 수정(첨부파일 삭제)
	   	@Override
		public void deleteModImage(AdminFreeImageVO adminFreeImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminFree.deleteModImage", adminFreeImageVO );
			
		}
}
