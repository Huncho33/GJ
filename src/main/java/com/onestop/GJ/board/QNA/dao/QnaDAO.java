package com.onestop.GJ.board.QNA.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.board.QNA.vo.QnaVO;


public interface QnaDAO {

	List selectAllQnasList(Map pagingMap) throws Exception;
	
	int selectTotQnas() throws DataAccessException;

	int insertNewQna(Map QnaMap) throws DataAccessException;

	QnaVO selectQna(int QnaNO) throws DataAccessException;

	void deleteQna(int QnaNO) throws DataAccessException;

	void updateQna(Map QnaMap) throws DataAccessException;

	boolean checkPwd(int qna_no, int qna_pw) throws DataAccessException;

	QnaVO selectParentQna(int qnaparent_no) throws DataAccessException;

	

	

}