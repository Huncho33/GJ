package com.onestop.GJ.board.notice.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

@Repository("boardNoticeDAO")
public class BoardNoticeDAOImpl implements BoardNoticeDAO {
	@Autowired
	private SqlSession sqlSession;

	// 게시판 리스트 조회
	@Override
	public List selectAllArticlesList(Map pagingMap) throws Exception {
		List<BoardNoticeVO> articlesList = sqlSession.selectList("mapper.boardNotice.selectAllArticlesList", pagingMap);
		return articlesList;
	}

	// 검색창
	@Override
	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException {
		List<BoardNoticeVO> articlesList = sqlSession.selectList("mapper.boardNotice.selectBoardListBySearchWord",
				pagingMap);
		return articlesList;
	}

	// 게시글 등록
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int noti_NO = selectNewArticleNO();
		articleMap.put("noti_NO", noti_NO);
		Collection<String> value = articleMap.values();
		sqlSession.insert("mapper.boardNotice.insertNewArticle", articleMap);
		return noti_NO;
	}

	// 다중 파일 업로드
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<BoardNoticeImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
		int noti_NO = (Integer) articleMap.get("noti_NO");
		int up_fileNO = selectNewImageFileNO();
		if (imageFileList != null && imageFileList.size() != 0) {

			for (BoardNoticeImageVO boardNoticeImageVO : imageFileList) {
				boardNoticeImageVO.setUp_fileNO(++up_fileNO);
				boardNoticeImageVO.setNoti_NO(noti_NO);
			}
			sqlSession.insert("mapper.boardNotice.insertNewImage", imageFileList);
		}
	}

	// 게시글 번호생성
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardNotice.selectNewArticleNO");
	}

	// 상세보기
	@Override
	public BoardNoticeVO selectArticle(int noti_NO) throws DataAccessException {
		return sqlSession.selectOne("mapper.boardNotice.selectArticle", noti_NO);
	}

	//파일리스트 셀렉트
	@Override
	public List selectImageFileList(int noti_NO) throws DataAccessException {
		List<BoardNoticeImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.boardNotice.selectImageFileList", noti_NO);
		return imageFileList;
	}

	//파일번호 셀렉트
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardNotice.selectNewImageFileNO");
	}

	//게시글 수정
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		Collection<String> value = articleMap.values();
		sqlSession.update("mapper.boardNotice.updateArticle", articleMap);
	}

	//게시글 삭제
	@Override
	public void deleteArticle(int noti_NO) throws DataAccessException {
		sqlSession.delete("mapper.boardNotice.deleteArticle", noti_NO);
	}

	// 조회수
	@Override
	public void boardHits(int noti_NO) throws Exception {
		sqlSession.update("mapper.boardNotice.boardHits", noti_NO);
	}

	// 페이징
	@Override
	public int selectTotArticles() throws DataAccessException {
		int totArticles = sqlSession.selectOne("mapper.boardNotice.selectTotArticles");
		return totArticles;
	}

	// 검색창 페이징
	@Override
	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException {
		int searchTotArticles = sqlSession.selectOne("mapper.boardNotice.selectSearchTotArticles", pagingMap);
		return searchTotArticles;
	}

	//파일 수정
	@Override
	public void updateImageFile(Map articleMap) throws DataAccessException {

		List<BoardNoticeImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
		int noti_NO = Integer.parseInt((String) articleMap.get("noti_NO"));

		for (int i = imageFileList.size() - 1; i >= 0; i--) {
			BoardNoticeImageVO boardNoticeImageVO = imageFileList.get(i);
			String up_fileName = boardNoticeImageVO.getUp_fileName();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				imageFileList.remove(i);
			} else {
				boardNoticeImageVO.setNoti_NO(noti_NO);
			}
		}

		if (imageFileList != null && imageFileList.size() != 0) {
			System.out.println("imageFileList" + imageFileList);
			sqlSession.update("mapper.boardNotice.updateImageFile", imageFileList);
		}
	}

	//게시글 파일만 추가
	@Override
	public void insertModNewImage(Map articleMap) throws DataAccessException {
		List<BoardNoticeImageVO> modAddimageFileList = (ArrayList<BoardNoticeImageVO>) articleMap
				.get("modAddimageFileList");
		int noti_NO = Integer.parseInt((String) articleMap.get("noti_NO"));
		int up_fileNO = selectNewImageFileNO();

		for (BoardNoticeImageVO boardNoticeImageVO : modAddimageFileList) {
			boardNoticeImageVO.setNoti_NO(noti_NO);
			boardNoticeImageVO.setUp_fileNO(++up_fileNO);
		}
		sqlSession.insert("mapper.boardNotice.insertModNewImage", modAddimageFileList);

	}

	//게시글 파일만 삭제
	@Override
	public void deleteModImage(BoardNoticeImageVO boardNoticeImageVO) throws DataAccessException {
		sqlSession.delete("mapper.boardNotice.deleteModImage", boardNoticeImageVO);

	}

}