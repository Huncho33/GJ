package com.onestop.GJ.board.QNA.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.board.QNA.vo.QnaVO;

@Repository("QnaDAO")
public class QnaDAOImpl implements QnaDAO {
	@Autowired
	private SqlSession sqlSession;

	//�Խñ� ����Ʈ
	@Override
	public List selectAllQnasList(Map pagingMap) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.boardQna.selectAllQnasList", pagingMap);
		return QnasList;
	}

	//�� �Խñ� ��
	@Override
	public int selectTotQnas() throws DataAccessException {
		int totQnas = sqlSession.selectOne("mapper.boardQna.selectTotQnas");
		return totQnas;
	}

	// ���� �߰��ϱ�
	@Override
	public int insertNewQna(Map QnaMap) throws DataAccessException {
		int qna_no = selectNewQnaNO();
		QnaMap.put("qna_no", qna_no);
		sqlSession.insert("mapper.boardQna.insertNewQna", QnaMap);
		return qna_no;
	}

	// �亯���� ���� ����
	@Override
	public void updateReply(Map QnaMap) throws DataAccessException {
		sqlSession.update("mapper.boardQna.updateReply", QnaMap);
	}

	// ���� �߰��ϱ� :: �� ���� ���� �����ϱ�
	private int selectNewQnaNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardQna.selectNewQnaNO");
	}

	// �󼼱� ���� �� �� ����
	@Override
	public QnaVO selectQna(int qna_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.boardQna.selectQna", qna_no);
	}

	// �󼼱� ���� �� �� ����
	@Override
	public QnaVO selectParentQna(int _qnaparent_no) throws DataAccessException {
		System.out.println("DAO :" + _qnaparent_no);
		return sqlSession.selectOne("mapper.boardQna.selectQna", _qnaparent_no);
	}

	// �� ���� ������Ʈ
	@Override
	public void updateQna(Map QnaMap) throws DataAccessException {
		sqlSession.update("mapper.boardQna.updateQna", QnaMap);
	}

	// �� �����ϱ�
	@Override
	public void deleteQna(int qna_no) throws DataAccessException {
		sqlSession.delete("mapper.boardQna.deleteQna", qna_no);
	}

	// ��й�ȣ ��Ȯ���� ���� ��ȸ ���
	@Override
	public boolean checkPwd(int qna_no, int qna_pw) throws DataAccessException {
		boolean result = false;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("qna_no", qna_no);
		map.put("qna_pw", qna_pw);
		int count = sqlSession.selectOne("mapper.boardQna.checkPwd", map);
		if (count == 1) {
			result = true;
		}
		return result;
	}
}
