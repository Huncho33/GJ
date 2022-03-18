package com.onestop.GJ.board.QNA.dao;

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

	@Override
	public List selectAllQnasList() throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.boardQna.selectAllQnasList");
		return QnasList;
	}

	// 상담글 추가하기
	@Override
	public int insertNewQna(Map QnaMap) throws DataAccessException {
		int qna_no = selectNewQnaNO();
		QnaMap.put("qna_no", qna_no);
		sqlSession.insert("mapper.boardQna.insertNewQna", QnaMap);
		return qna_no;
	}

	// 상담글 추가하기 :: 새 상담글 내용 셀렉하기
	private int selectNewQnaNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardQna.selectNewQnaNO");
	}
	

	@Override
	public QnaVO selectQna(int qna_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.boardQna.selectQna", qna_no);
	}

//	@Override
//	public void updateQna(Map QnaMap) throws DataAccessException {
//		sqlSession.update("mapper.board.updateQna", QnaMap);
//	}
//
//	@Override
//	public void deleteQna(int QnaNO) throws DataAccessException {
//		sqlSession.delete("mapper.board.deleteQna", QnaNO);
//	}
}
