package com.onestop.GJ.board.data.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.board.data.dao.BoardDataDAO;
import com.onestop.GJ.board.data.vo.BoardDataImageVO;
import com.onestop.GJ.board.data.vo.BoardDataVO;

@Service("boardDataService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardDataServiceImpl implements BoardDataService {
	@Autowired
	BoardDataDAO boardDAO;

	public Map listArticles(Map pagingMap) throws Exception {
		Map articlesMap = new HashMap();
		List<BoardDataVO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		return articlesMap;
	}

	// �˻�â
	@Override
	public Map searchBoardList(Map pagingMap) throws Exception {
		Map articlesMap = new HashMap();
		List<BoardDataVO> articlesList = boardDAO.selectBoardListBySearchWord(pagingMap);
		int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("searchTotArticles", searchTotArticles);
		articlesMap.put("totArticles", totArticles);
		return articlesMap;
	}

	// �� �߰�
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int etc_NO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("etc_NO", etc_NO);
		boardDAO.insertNewImage(articleMap);
		return etc_NO;
	}

	// ���� ���� ���̱�
	@Override
	public Map viewArticle(int etc_NO) throws Exception {
		Map articleMap = new HashMap();
		BoardDataVO boardDataVO = boardDAO.selectArticle(etc_NO);
		List<BoardDataImageVO> imageFileList = boardDAO.selectImageFileList(etc_NO);
		articleMap.put("article", boardDataVO);
		articleMap.put("imageFileList", imageFileList);

	// ��ȸ�� �߰��ϱ�
	boardDAO.boardHits(etc_NO);
	return articleMap;
	}

	// �� ����
	@Override
	public void removeArticle(int etc_NO) throws Exception {
		boardDAO.deleteArticle(etc_NO);
	}

	// �� ����
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
		List<BoardDataImageVO> imageFileList = (List<BoardDataImageVO>) articleMap.get("imageFileList");
		List<BoardDataImageVO> modAddimageFileList = (List<BoardDataImageVO>) articleMap.get("modAddimageFileList");

		if (imageFileList != null && imageFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) articleMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) articleMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				boardDAO.updateImageFile(articleMap); // ���� �̹����� �����ϰ� �� �̹����� �߰��� ���
				boardDAO.insertModNewImage(articleMap);
			} else {
				boardDAO.updateImageFile(articleMap); // ������ �̹����� ������ �� ���
			}
		} else if (modAddimageFileList != null && modAddimageFileList.size() != 0) { // �� �̹����� �߰��� ���
			boardDAO.insertModNewImage(articleMap);
		}
	}

	// �̹��� ���� (����)
	@Override
	public void removeModImage(BoardDataImageVO boardDataImageVO) throws Exception {
		boardDAO.deleteModImage(boardDataImageVO);
	}
}
