package com.onestop.GJ.board.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.board.notice.dao.BoardNoticeDAO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;



@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardNoticeServiceImpl implements BoardNoticeService {
	@Autowired
	BoardNoticeDAO boardDAO;

	public List<BoardNoticeVO> listArticles() throws Exception {
		List<BoardNoticeVO> articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}

	@Override
	public int addNewArticle(Map<String, Object> articleMap) {
		return boardDAO.insertNewArticle(articleMap);
	}

	// 단일 파일 보이기
	@Override
	public BoardNoticeVO viewArticle(int noti_NO) throws Exception {
		BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
		return boardNoticeVO;
	}
	 
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
}
