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

	// ���� �̹��� �߰��ϱ�
	/*
	 * @Override public int addNewArticle(Map articleMap) throws Exception{ return
	 * boardDAO.insertNewArticle(articleMap); }
	 */
	// ���� �̹��� �߰��ϱ�
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int noti_NO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("noti_NO", noti_NO);
		boardDAO.insertNewImage(articleMap);
		return noti_NO;
	}

	//���� ���� ���̱�
	   @Override
	   public Map viewArticle(int noti_NO) throws Exception {
	      Map articleMap = new HashMap();
	      BoardNoticeVO boardNoticeVO = boardDAO.selectArticle(noti_NO);
//	      List<BoardNoticeImageVO> imageFileList = boardDAO.selectImageFileList(noti_NO);
	      articleMap.put("article", boardNoticeVO);
//	      articleMap.put("imageFileList", imageFileList);
	      return articleMap;
	   }

	// ���� ���� ���̱�
	/*
	 * @Override public ArticleVO viewArticle(int articleNO) throws Exception {
	 * ArticleVO articleVO = boardDAO.selectArticle(articleNO); return articleVO; }
	 */
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
}
