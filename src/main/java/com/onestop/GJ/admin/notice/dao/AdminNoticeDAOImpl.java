package com.onestop.GJ.admin.notice.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.admin.notice.vo.AdminNoticeVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

@Repository("adminNoticeDAO")
public class AdminNoticeDAOImpl implements AdminNoticeDAO {
	
	@Autowired
	   private SqlSession sqlSession;
	
	@Override
	   public List selectAllArticlesList(Map pagingMap) throws Exception {
	      List<AdminNoticeVO> articlesList = sqlSession.selectList("mapper.adminNotice.selectAllArticlesList", pagingMap);
	      System.out.println("총 게시판 수 " +articlesList.size());
	      return articlesList;
	   }
	   
	 //검색창
	 	@Override
	 	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
	 		List<AdminNoticeVO> articlesList=sqlSession.selectList("mapper.adminNotice.selectBoardListBySearchWord", pagingMap);
	 	     System.out.println("검색"+ articlesList.size());
	 		 return articlesList;
	 	}

	   @Override
	   public int insertNewArticle(Map articleMap) throws DataAccessException {
	      int noti_NO = selectNewArticleNO();
	      articleMap.put("noti_NO", noti_NO);
	      Collection<String> value = articleMap.values();
	      System.out.println(value);
	      sqlSession.insert("mapper.adminNotice.insertNewArticle",articleMap);
	      return noti_NO;
	   }
	   
	// 다중 파일 업로드
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
			System.out.println("imageFileList"+ imageFileList);
			sqlSession.insert("mapper.adminNotice.insertNewImage", imageFileList);
		}
	}
	    		
	   private int selectNewArticleNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminNotice.selectNewArticleNO");
	   }
	   
	//   상세보기
	   @Override
	   public AdminNoticeVO selectArticle(int noti_NO) throws DataAccessException {
	      return sqlSession.selectOne("mapper.adminNotice.selectArticle", noti_NO);
	   }
	   

	   
		@Override
		public List selectImageFileList(int noti_NO) throws DataAccessException {
			List<AdminNoticeImageVO> imageFileList = null;
			imageFileList = sqlSession.selectList("mapper.adminNotice.selectImageFileList",noti_NO);
			return imageFileList;
		}
		
		private int selectNewImageFileNO() throws DataAccessException {
			return sqlSession.selectOne("mapper.adminNotice.selectNewImageFileNO");
		}



	   @Override
	   public void updateArticle(Map articleMap) throws DataAccessException {
		   Collection<String> value = articleMap.values();
		      System.out.println(value);
		   sqlSession.update("mapper.adminNotice.updateArticle", articleMap);
	      
	   }

	   @Override
	   public void deleteArticle(int noti_NO) throws DataAccessException {
		   System.out.println("noti3 : "+ noti_NO);
	      sqlSession.delete("mapper.adminNotice.deleteArticle", noti_NO);
	   }

	   //조회수
	   @Override
		public void boardHits(int noti_NO) throws Exception {
			sqlSession.update("mapper.adminNotice.boardHits", noti_NO);
		}
	   
	   //페이징
	   	@Override
	   	public int selectTotArticles() throws DataAccessException{
	   		int totArticles = sqlSession.selectOne("mapper.adminNotice.selectTotArticles");
	   		return totArticles;
	   	}
	   	
	    //검색창 페이징
	   	@Override
	   	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
	   		int searchTotArticles = sqlSession.selectOne("mapper.adminNotice.selectSearchTotArticles", pagingMap);
	   		return searchTotArticles;
	   	}

	   	@Override
		public void updateImageFile(Map articleMap) throws DataAccessException {
			
			List<AdminNoticeImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
			int noti_NO = Integer.parseInt((String)articleMap.get("noti_NO"));
			
			for(int i = imageFileList.size()-1; i >= 0; i--){
				AdminNoticeImageVO adminNoticeImageVO = imageFileList.get(i);
				String up_fileName = adminNoticeImageVO.getUp_fileName();
				if(up_fileName == null) {  //기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로  수정할 필요가 없다.
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
			
			@Override
			public void insertModNewImage(Map articleMap) throws DataAccessException {
				List<AdminNoticeImageVO> modAddimageFileList = (ArrayList<AdminNoticeImageVO>)articleMap.get("modAddimageFileList");
				int noti_NO = Integer.parseInt((String)articleMap.get("noti_NO"));
				System.out.println("modAddimageFileList"+ modAddimageFileList);
				int up_fileNO = selectNewImageFileNO();
				
				for(AdminNoticeImageVO adminNoticeImageVO : modAddimageFileList){
					adminNoticeImageVO.setNoti_NO(noti_NO);
					adminNoticeImageVO.setUp_fileNO(++up_fileNO);
				}
				
				sqlSession.insert("mapper.adminNotice.insertModNewImage", modAddimageFileList );
				
			}
			
		
	   	
	   	@Override
		public void deleteModImage(AdminNoticeImageVO adminNoticeImageVO) throws DataAccessException {
			sqlSession.delete("mapper.adminNotice.deleteModImage", adminNoticeImageVO );
			
		}
	   	
	   	@Override
		public boolean checkPwd(int noti_no) throws DataAccessException {
			boolean result = false;
			System.out.println("dao noti_no:" + noti_no);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("noti_no", noti_no);
			int count = sqlSession.selectOne("mapper.adminNotice.checkPwd", map);
			if (count == 1) {
				result = true;
			}
			return result;
		}
	   	

}
