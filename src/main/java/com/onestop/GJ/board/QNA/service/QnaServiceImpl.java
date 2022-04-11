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
	private QnaDAO QnaDAO;

	public Map listQnas(Map pagingMap) throws Exception {
		Map qnasMap = new HashMap();
		List<QnaVO> QnasList = QnaDAO.selectAllQnasList(pagingMap);
		int totQnas = QnaDAO.selectTotQnas();
		qnasMap.put("QnasList", QnasList);
		qnasMap.put("totQnas", totQnas);
		return qnasMap;
	}

	// �� �߰��ϱ�
	@Override
	public int addNewQna(Map QnaMap) throws Exception {
		int QnaNO = QnaDAO.insertNewQna(QnaMap);
		return QnaNO;
	}

	// ���� �亯���� ���� ����
	@Override
	public void updateReply(Map QnaMap) throws Exception {
		QnaDAO.updateReply(QnaMap);
	}

	// �󼼱� ����
	@Override
	public Map viewQna(int qna_no) throws Exception {
		Map QnaMap = new HashMap();
		QnaVO QnaVO = QnaDAO.selectQna(qna_no);
		QnaMap.put("Qna", QnaVO);
		return QnaMap;
	}

	// �θ�� ��ȸ�ϱ�
	@Override
	public Map selectParentQna(String qnaparent_no) throws Exception {
		System.out.println("SERVICE :" + qnaparent_no);
		int _qnaparent_no = Integer.parseInt(qnaparent_no);
		Map QnaParentMap = new HashMap();
		QnaVO QnaVO = QnaDAO.selectParentQna(_qnaparent_no);
		QnaParentMap.put("QnaVO", QnaVO);
		return QnaParentMap;
	}

	// �� �����ϱ�
	@Override
	public void modQna(Map QnaMap) throws Exception {
		QnaDAO.updateQna(QnaMap);
	}

	// �� �����ϱ�
	@Override
	public void removeQna(int qna_no) throws Exception {
		QnaDAO.deleteQna(qna_no);
	}

	// ��й�ȣ ��Ȯ�� ���
	@Override
	public boolean checkPwd(int qna_no, int qna_pw) throws Exception {
		return QnaDAO.checkPwd(qna_no, qna_pw);
	}

}
