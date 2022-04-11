package com.onestop.GJ.board.free.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.board.data.vo.BoardDataImageVO;
import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.free.vo.BoardFreeImageVO;
import com.onestop.GJ.board.free.vo.BoardFreeVO;

@Repository("boardFreeDAO")
public class BoardFreeDAOImpl implements BoardFreeDAO {
	@Autowired
	private SqlSession sqlSession;

	// 게시글 리스트 셀렉트
	@Override
	public List selectAllArticlesList(Map pagingMap) throws Exception {
		List<BoardFreeVO> articlesList = sqlSession.selectList("mapper.boardFree.selectAllArticlesList", pagingMap);
		return articlesList;
	}

	// 검색창
	@Override
	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException {
		List<BoardFreeVO> articlesList = sqlSession.selectList("mapper.boardFree.selectBoardListBySearchWord",
				pagingMap);
		return articlesList;
	}

	// 게시글 등록
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int fr_NO = selectNewArticleNO();
		articleMap.put("fr_NO", fr_NO);
		sqlSession.insert("mapper.boardFree.insertNewArticle", articleMap);
		return fr_NO;
	}

	// 다중 파일 업로드
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<BoardFreeImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
		int fr_NO = (Integer) articleMap.get("fr_NO");
		int up_fileNO = selectNewImageFileNO();
		if (imageFileList != null && imageFileList.size() != 0) {

			for (BoardFreeImageVO boardFreeImageVO : imageFileList) {
				boardFreeImageVO.setUp_fileNO(++up_fileNO);
				boardFreeImageVO.setFr_NO(fr_NO);
			}
			sqlSession.insert("mapper.boardFree.insertNewImage", imageFileList);
		}
	}

	// 게시글 번호 생성
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardFree.selectNewArticleNO");
	}

	// 상세보기
	@Override
	public BoardFreeVO selectArticle(int fr_NO) throws DataAccessException {
		return sqlSession.selectOne("mapper.boardFree.selectArticle", fr_NO);
	}

	// 파일리스트 셀렉트
	@Override
	public List selectImageFileList(int fr_NO) throws DataAccessException {
		List<BoardFreeImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.boardFree.selectImageFileList", fr_NO);
		return imageFileList;
	}

	// 파일번호 생성
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardFree.selectNewImageFileNO");
	}

	// 게시글 수정
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.boardFree.updateArticle", articleMap);
	}

	// 게시글 삭제
	@Override
	public void deleteArticle(int fr_NO) throws DataAccessException {
		sqlSession.delete("mapper.boardFree.deleteArticle", fr_NO);
	}

	// 조회수
	@Override
	public void boardHits(int fr_NO) throws Exception {
		sqlSession.update("mapper.boardFree.boardHits", fr_NO);
	}

	// 페이징
	@Override
	public int selectTotArticles() throws DataAccessException {
		int totArticles = sqlSession.selectOne("mapper.boardFree.selectTotArticles");
		return totArticles;
	}

	// 검색창 페이징
	@Override
	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException {
		int searchTotArticles = sqlSession.selectOne("mapper.boardFree.selectSearchTotArticles", pagingMap);
		return searchTotArticles;
	}

	// 파일 수정
	@Override
	public void updateImageFile(Map articleMap) throws DataAccessException {

		List<BoardFreeImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
		int fr_NO = Integer.parseInt((String) articleMap.get("fr_NO"));

		for (int i = imageFileList.size() - 1; i >= 0; i--) {
			BoardFreeImageVO boardFreeImageVO = imageFileList.get(i);
			String up_fileName = boardFreeImageVO.getUp_fileName();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				imageFileList.remove(i);
			} else {
				boardFreeImageVO.setFr_NO(fr_NO);
			}
		}

		if (imageFileList != null && imageFileList.size() != 0) {
			sqlSession.update("mapper.boardFree.updateImageFile", imageFileList);
		}
	}

	// 파일만 수정
	@Override
	public void insertModNewImage(Map articleMap) throws DataAccessException {
		List<BoardFreeImageVO> modAddimageFileList = (ArrayList<BoardFreeImageVO>) articleMap
				.get("modAddimageFileList");
		int fr_NO = Integer.parseInt((String) articleMap.get("fr_NO"));
		int up_fileNO = selectNewImageFileNO();

		for (BoardFreeImageVO boardFreeImageVO : modAddimageFileList) {
			boardFreeImageVO.setFr_NO(fr_NO);
			boardFreeImageVO.setUp_fileNO(++up_fileNO);
		}
		sqlSession.insert("mapper.boardFree.insertModNewImage", modAddimageFileList);

	}

	// 파일만 삭제
	@Override
	public void deleteModImage(BoardFreeImageVO boardFreeImageVO) throws DataAccessException {
		sqlSession.delete("mapper.boardFree.deleteModImage", boardFreeImageVO);
	}

}
