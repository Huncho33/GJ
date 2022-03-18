package com.onestop.GJ.board.QNA.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.board.QNA.dao.QnaDAO;
import com.onestop.GJ.board.QNA.vo.QnaVO;


@Service("QnaService")
@Transactional(propagation = Propagation.REQUIRED)
public class QnaServiceImpl implements QnaService {
	@Autowired
	QnaDAO QnaDAO;

	public List<QnaVO> listQnas() throws Exception {
		List<QnaVO> QnasList = QnaDAO.selectAllQnasList();
		return QnasList;
	}

	
	// 글 추가하기
	@Override
	public int addNewQna(Map QnaMap) throws Exception {
		int QnaNO = QnaDAO.insertNewQna(QnaMap);
		return QnaNO;
	}
	
	//다중 파일 보이기
	   @Override
	   public Map viewQna(int qna_no) throws Exception {
	      Map QnaMap = new HashMap();
	      QnaVO QnaVO = QnaDAO.selectQna(qna_no);
	      QnaMap.put("Qna", QnaVO);
	      return QnaMap;
	   }

	   
	   
//	@Override
//	public void modQna(Map QnaMap) throws Exception {
//		boardDAO.updateQna(QnaMap);
//	}
//
//	@Override
//	public void removeQna(int QnaNO) throws Exception {
//		boardDAO.deleteQna(QnaNO);
//	}
}
