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

	// ���Խ��� ���� �Խñ� ���
	public Map listQnas(Map pagingMap) throws Exception {
		Map qnasMap = new HashMap();
		List<AdminQnaVO> QnasList = QnaDAO.selectAllQnasList(pagingMap);
		int totQnas = QnaDAO.selectTotQnas();
		qnasMap.put("QnasList", QnasList);
		qnasMap.put("totQnas", totQnas);
		return qnasMap;
	}
	
	// ���Խ��� ���� �Խñ� �˻�  ���
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

 // ���Խ��� ���� �Խñ� �߰� 
	@Override
	public int addNewQna(Map QnaMap) throws Exception {
		int QnaNO = QnaDAO.insertNewQna(QnaMap);
		return QnaNO;
	}

	// ���Խ��� ���� �Խñ� ���� �亯���� ���� ����
	@Override
	public void updateReply(Map QnaMap) throws Exception {
		QnaDAO.updateReply(QnaMap);
	}

	// ���Խ��� ���� �Խñ� ��ȭ�� 
	@Override
	public Map viewQna(int qna_no) throws Exception {
		Map QnaMap = new HashMap();
		AdminQnaVO AdminQnaVO = QnaDAO.selectQna(qna_no);
		QnaMap.put("Qna", AdminQnaVO);
		return QnaMap;
	}

	// ���Խ��� ���� �Խñ� �θ�� ��ȸ 
	@Override
	public Map selectParentQna(String qnaparent_no) throws Exception {
		System.out.println("SERVICE :" + qnaparent_no);
		int _qnaparent_no = Integer.parseInt(qnaparent_no);
		Map QnaParentMap = new HashMap();
		AdminQnaVO AdminQnaVO = QnaDAO.selectParentQna(_qnaparent_no);
		QnaParentMap.put("AdminQnaVO", AdminQnaVO);
		return QnaParentMap;
	}

	// ���Խ��� ���� �Խñ� ����
	@Override
	public void modQna(Map QnaMap) throws Exception {
		QnaDAO.updateQna(QnaMap);
	}

	// ���Խ��� ���� �Խñ� ���� 
	@Override
	public void removeQna(int qna_no) throws Exception {
		QnaDAO.deleteQna(qna_no);
	}

	// ���Խ��� ���� �Խñ�  ��й�ȣ ��Ȯ�� ���
	@Override
	public boolean checkPwd(int qna_no, int qna_pw) throws Exception {
		return QnaDAO.checkPwd(qna_no, qna_pw);
	}

}
