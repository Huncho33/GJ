package com.onestop.GJ.admin.board.QNA.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.board.QNA.dao.AdminQnaDAO;
import com.onestop.GJ.admin.board.QNA.vo.AdminQnaVO;


@Service("adminQnaService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminQnaServiceImpl implements AdminQnaService {
	
	@Autowired
	private AdminQnaDAO QnaDAO;

	// 상담게시판 관리 게시글 목록
	public Map listQnas(Map pagingMap) throws Exception {
		Map qnasMap = new HashMap();
		List<AdminQnaVO> QnasList = QnaDAO.selectAllQnasList(pagingMap);
		int totQnas = QnaDAO.selectTotQnas();
		qnasMap.put("QnasList", QnasList);
		qnasMap.put("totQnas", totQnas);
		return qnasMap;
	}
	
	// 상담게시판 관리 게시글 검색  목록
    @Override
    public Map searchBoardList(Map pagingMap) throws Exception{
       Map qnasMap = new HashMap();
       List<AdminQnaDAO> QnasList=QnaDAO.selectBoardListBySearchWord(pagingMap);
       int searchTotQnas = QnaDAO.selectSearchTotArticles(pagingMap);
       int totQnas = QnaDAO.selectTotQnas();
       qnasMap.put("QnasList", QnasList);
       qnasMap.put("searchTotQnas", searchTotQnas);
       qnasMap.put("totQnas", totQnas);
       return qnasMap;
    }

 // 상담게시판 관리 게시글 추가 
	@Override
	public int addNewQna(Map QnaMap) throws Exception {
		int QnaNO = QnaDAO.insertNewQna(QnaMap);
		return QnaNO;
	}

	// 상담게시판 관리 게시글 원글 답변여부 상태 변경
	@Override
	public void updateReply(Map QnaMap) throws Exception {
		QnaDAO.updateReply(QnaMap);
	}

	// 상담게시판 관리 게시글 상세화면 
	@Override
	public Map viewQna(int qna_no) throws Exception {
		Map QnaMap = new HashMap();
		AdminQnaVO AdminQnaVO = QnaDAO.selectQna(qna_no);
		QnaMap.put("Qna", AdminQnaVO);
		return QnaMap;
	}

	// 상담게시판 관리 게시글 부모글 조회 
	@Override
	public Map selectParentQna(String qnaparent_no) throws Exception {
		System.out.println("SERVICE :" + qnaparent_no);
		int _qnaparent_no = Integer.parseInt(qnaparent_no);
		Map QnaParentMap = new HashMap();
		AdminQnaVO AdminQnaVO = QnaDAO.selectParentQna(_qnaparent_no);
		QnaParentMap.put("AdminQnaVO", AdminQnaVO);
		return QnaParentMap;
	}

	// 상담게시판 관리 게시글 수정
	@Override
	public void modQna(Map QnaMap) throws Exception {
		QnaDAO.updateQna(QnaMap);
	}

	// 상담게시판 관리 게시글 삭제 
	@Override
	public void removeQna(int qna_no) throws Exception {
		QnaDAO.deleteQna(qna_no);
	}

	// 상담게시판 관리 게시글  비밀번호 재확인 기능
	@Override
	public boolean checkPwd(int qna_no, int qna_pw) throws Exception {
		return QnaDAO.checkPwd(qna_no, qna_pw);
	}

}
