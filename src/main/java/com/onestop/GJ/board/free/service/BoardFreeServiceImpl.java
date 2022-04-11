package com.onestop.GJ.board.free.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.board.data.vo.BoardDataImageVO;
import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.free.dao.BoardFreeDAO;
import com.onestop.GJ.board.free.vo.BoardFreeImageVO;
import com.onestop.GJ.board.free.vo.BoardFreeVO;

@Service("boardFreeService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardFreeServiceImpl implements BoardFreeService {
	@Autowired
	BoardFreeDAO boardDAO;

	public Map listArticles(Map pagingMap) throws Exception {
		Map articlesMap = new HashMap();
		List<BoardFreeDAO> articlesList = boardDAO.selectAllArticlesList(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		return articlesMap;
	}

	// 검색창
	@Override
	public Map searchBoardList(Map pagingMap) throws Exception {
		Map articlesMap = new HashMap();
		List<BoardFreeDAO> articlesList = boardDAO.selectBoardListBySearchWord(pagingMap);
		int searchTotArticles = boardDAO.selectSearchTotArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("searchTotArticles", searchTotArticles);
		articlesMap.put("totArticles", totArticles);
		return articlesMap;
	}

	// 글 추가
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int fr_NO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("fr_NO", fr_NO);
		boardDAO.insertNewImage(articleMap);
		return fr_NO;
	}

	// 다중 파일 보이기
	@Override
	public Map viewArticle(int fr_NO) throws Exception {
		Map articleMap = new HashMap();
		BoardFreeVO boardFreeVO = boardDAO.selectArticle(fr_NO);
		List<BoardFreeImageVO> imageFileList = boardDAO.selectImageFileList(fr_NO);
		articleMap.put("article", boardFreeVO);
		articleMap.put("imageFileList", imageFileList);

		// 조회수 추가하기
		boardDAO.boardHits(fr_NO);
		return articleMap;

	}

	// 글 삭제
	@Override
	public void removeArticle(int fr_NO) throws Exception {
		boardDAO.deleteArticle(fr_NO);
	}

	// 글 수정
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
		List<BoardFreeImageVO> imageFileList = (List<BoardFreeImageVO>) articleMap.get("imageFileList");
		List<BoardFreeImageVO> modAddimageFileList = (List<BoardFreeImageVO>) articleMap.get("modAddimageFileList");

		if (imageFileList != null && imageFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) articleMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) articleMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				boardDAO.updateImageFile(articleMap); // 기존 이미지도 수정하고 새 이미지도 추가한 경우
				boardDAO.insertModNewImage(articleMap);
			} else {
				boardDAO.updateImageFile(articleMap); // 기존의 이미지를 수정만 한 경우
			}
		} else if (modAddimageFileList != null && modAddimageFileList.size() != 0) { // 새 이미지를 추가한 경우
			boardDAO.insertModNewImage(articleMap);
		}
	}

	// 이미지 삭제 (수정)
	@Override
	public void removeModImage(BoardFreeImageVO boardFreeImageVO) throws Exception {
		boardDAO.deleteModImage(boardFreeImageVO);
	}
}
