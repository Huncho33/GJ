package com.onestop.GJ.admin.QNA.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.QNA.vo.AdminQnaVO;

@Repository("adminQnaDAO")
public class AdminQnaDAOImpl implements AdminQnaDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllQnasList(Map pagingMap) throws Exception {
		List<AdminQnaVO> QnasList = sqlSession.selectList("mapper.adminQna.selectAllQnasList", pagingMap);
		return QnasList;
	}

	@Override
	public int selectTotQnas() throws DataAccessException {
		int totQnas = sqlSession.selectOne("mapper.adminQna.selectTotQnas");
		return totQnas;

	}
	//검색창
    @Override
    public List selectBoardListBySearchWord(Map pagingMap) throws DataAccessException{
    	System.out.println("target"+ pagingMap);
       List<AdminQnaVO> QnasList=sqlSession.selectList("mapper.adminQna.selectBoardListBySearchWord", pagingMap);
       System.out.println("list : "+ QnasList.toString());
       System.out.println("검색"+ QnasList.size());
        return QnasList;
    }
    
  //검색창 페이징
    @Override
    public int selectSearchTotArticles(Map pagingMap) throws DataAccessException{
       int searchTotArticles = sqlSession.selectOne("mapper.adminQna.selectSearchTotArticles", pagingMap);
       return searchTotArticles;
    }

	// 상담글 추가하기
	@Override
	public int insertNewQna(Map QnaMap) throws DataAccessException {
		int qna_no = selectNewQnaNO();
		QnaMap.put("qna_no", qna_no);
		sqlSession.insert("mapper.adminQna.insertNewQna", QnaMap);
		return qna_no;
	}

	// 답변여부 상태 변경
	@Override
	public void updateReply(Map QnaMap) throws DataAccessException {
		sqlSession.update("mapper.adminQna.updateReply", QnaMap);
	}

	// 상담글 추가하기 :: 새 상담글 내용 셀렉하기
	private int selectNewQnaNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.adminQna.selectNewQnaNO");
	}

	// 상세글 보기 할 글 고르기
	@Override
	public AdminQnaVO selectQna(int qna_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.adminQna.selectQna", qna_no);
	}

	// 상세글 보기 할 글 고르기
	@Override
	public AdminQnaVO selectParentQna(int _qnaparent_no) throws DataAccessException {
		System.out.println("DAO :" + _qnaparent_no);
		return sqlSession.selectOne("mapper.adminQna.selectQna", _qnaparent_no);
	}

	// 글 수정 업데이트
	@Override
	public void updateQna(Map QnaMap) throws DataAccessException {
		sqlSession.update("mapper.adminQna.updateQna", QnaMap);
	}

	// 글 삭제하기
	@Override
	public void deleteQna(int qna_no) throws DataAccessException {
		sqlSession.delete("mapper.adminQna.deleteQna", qna_no);
	}

	// 비밀번호 재확인을 위한 조회 기능
	@Override
	public boolean checkPwd(int qna_no, int qna_pw) throws DataAccessException {
		boolean result = false;
		System.out.println("dao qna_no:" + qna_no + " / dao qna_pw:" + qna_pw);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("qna_no", qna_no);
		map.put("qna_pw", qna_pw);
		int count = sqlSession.selectOne("mapper.adminQna.checkPwd", map);
		if (count == 1) {
			result = true;
		}
		return result;
	}

}
