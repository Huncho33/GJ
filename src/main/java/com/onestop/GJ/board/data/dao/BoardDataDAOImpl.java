package com.onestop.GJ.board.data.dao;

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

@Repository("boardDataDAO")
public class BoardDataDAOImpl implements BoardDataDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllArticlesList(Map pagingMap) throws Exception {
		List<BoardDataVO> articlesList = sqlSession.selectList("mapper.boardData.selectAllArticlesList", pagingMap);
		return articlesList;
	}

	// �˻�â
	@Override
	public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException {
		List<BoardDataVO> articlesList = sqlSession.selectList("mapper.boardData.selectBoardListBySearchWord",
				pagingMap);
		return articlesList;
	}

	// �Խñ� ���
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int etc_NO = selectNewArticleNO();
		articleMap.put("etc_NO", etc_NO);
		sqlSession.insert("mapper.boardData.insertNewArticle", articleMap);
		return etc_NO;
	}

	// ���� ���� ���ε�
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<BoardDataImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
		int etc_NO = (Integer) articleMap.get("etc_NO");
		int up_fileNO = selectNewImageFileNO();
		if (imageFileList != null && imageFileList.size() != 0) {
			for (BoardDataImageVO boardDataImageVO : imageFileList) {
				boardDataImageVO.setUp_fileNO(++up_fileNO);
				boardDataImageVO.setEtc_NO(etc_NO);
			}
			sqlSession.insert("mapper.boardData.insertNewImage", imageFileList);
		}
	}

	// �Խñ� ��ȣ ����Ʈ
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardData.selectNewArticleNO");
	}

	// �󼼺���
	@Override
	public BoardDataVO selectArticle(int etc_NO) throws DataAccessException {
		return sqlSession.selectOne("mapper.boardData.selectArticle", etc_NO);
	}

	// ���ϸ���Ʈ ����Ʈ
	@Override
	public List selectImageFileList(int etc_NO) throws DataAccessException {
		List<BoardDataImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.boardData.selectImageFileList", etc_NO);
		return imageFileList;
	}

	// ���� ��ȣ ����Ʈ
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardData.selectNewImageFileNO");
	}

	// �Խñ� ����
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.boardData.updateArticle", articleMap);

	}

	// �Խñ� ����
	@Override
	public void deleteArticle(int etc_NO) throws DataAccessException {
		sqlSession.delete("mapper.boardData.deleteArticle", etc_NO);
	}

	// ��ȸ��
	@Override
	public void boardHits(int etc_NO) throws Exception {

		sqlSession.update("mapper.boardData.boardHits", etc_NO);
	}

	// ����¡
	@Override
	public int selectTotArticles() throws DataAccessException {
		int totArticles = sqlSession.selectOne("mapper.boardData.selectTotArticles");
		return totArticles;
	}

	// �˻�â ����¡
	@Override
	public int selectSearchTotArticles(Map pagingMap) throws DataAccessException {
		int searchTotArticles = sqlSession.selectOne("mapper.boardData.selectSearchTotArticles", pagingMap);
		return searchTotArticles;
	}

	// ���� ����
	@Override
	public void updateImageFile(Map articleMap) throws DataAccessException {
		List<BoardDataImageVO> imageFileList = (ArrayList) articleMap.get("imageFileList");
		int etc_NO = Integer.parseInt((String) articleMap.get("etc_NO"));
		for (int i = imageFileList.size() - 1; i >= 0; i--) {
			BoardDataImageVO boardDataImageVO = imageFileList.get(i);
			String up_fileName = boardDataImageVO.getUp_fileName();
			if (up_fileName == null) { // ������ �̹����� �������� �ʴ� ��� ���ϸ��� null �̹Ƿ� ������ �ʿ䰡 ����.
				imageFileList.remove(i);
			} else {
				boardDataImageVO.setEtc_NO(etc_NO);
			}
		}

		if (imageFileList != null && imageFileList.size() != 0) {
			sqlSession.update("mapper.boardData.updateImageFile", imageFileList);
		}
	}

	// ������ ���ϸ� ���
	@Override
	public void insertModNewImage(Map articleMap) throws DataAccessException {
		List<BoardDataImageVO> modAddimageFileList = (ArrayList<BoardDataImageVO>) articleMap.get("modAddimageFileList");
		int etc_NO = Integer.parseInt((String) articleMap.get("etc_NO"));
		int up_fileNO = selectNewImageFileNO();

		for (BoardDataImageVO boardDataImageVO : modAddimageFileList) {
			boardDataImageVO.setEtc_NO(etc_NO);
			boardDataImageVO.setUp_fileNO(++up_fileNO);
		}
		sqlSession.insert("mapper.boardData.insertModNewImage", modAddimageFileList);
	}

	// ������ ���ϸ� ����
	@Override
	public void deleteModImage(BoardDataImageVO boardDataImageVO) throws DataAccessException {
		sqlSession.delete("mapper.boardData.deleteModImage", boardDataImageVO);
	}

}
