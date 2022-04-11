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

	//게시글 리스트
	@Override
	public List selectAllQnasList(Map pagingMap) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.boardQna.selectAllQnasList", pagingMap);
		return QnasList;
	}

	//총 게시글 수
	@Override
	public int selectTotQnas() throws DataAccessException {
		int totQnas = sqlSession.selectOne("mapper.boardQna.selectTotQnas");
		return totQnas;
	}

	// 상담글 추가하기
	@Override
	public int insertNewQna(Map QnaMap) throws DataAccessException {
		int qna_no = selectNewQnaNO();
		QnaMap.put("qna_no", qna_no);
		sqlSession.insert("mapper.boardQna.insertNewQna", QnaMap);
		return qna_no;
	}

	// 답변여부 상태 변경
	@Override
	public void updateReply(Map QnaMap) throws DataAccessException {
		sqlSession.update("mapper.boardQna.updateReply", QnaMap);
	}

	// 상담글 추가하기 :: 새 상담글 내용 셀렉하기
	private int selectNewQnaNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.boardQna.selectNewQnaNO");
	}

	// 상세글 보기 할 글 고르기
	@Override
	public QnaVO selectQna(int qna_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.boardQna.selectQna", qna_no);
	}

	// 상세글 보기 할 글 고르기
	@Override
	public QnaVO selectParentQna(int _qnaparent_no) throws DataAccessException {
		System.out.println("DAO :" + _qnaparent_no);
		return sqlSession.selectOne("mapper.boardQna.selectQna", _qnaparent_no);
	}

	// 글 수정 업데이트
	@Override
	public void updateQna(Map QnaMap) throws DataAccessException {
		sqlSession.update("mapper.boardQna.updateQna", QnaMap);
	}

	// 글 삭제하기
	@Override
	public void deleteQna(int qna_no) throws DataAccessException {
		sqlSession.delete("mapper.boardQna.deleteQna", qna_no);
	}

	// 비밀번호 재확인을 위한 조회 기능
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
