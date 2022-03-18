package com.onestop.GJ.board.QNA.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.board.QNA.vo.QnaVO;


public interface QnaDAO {

	List selectAllQnasList() throws Exception;

	int insertNewQna(Map QnaMap) throws DataAccessException;

	QnaVO selectQna(int QnaNO) throws DataAccessException;

//	void updateQna(Map QnaMap) throws DataAccessException;
//
//	void deleteQna(int QnaNO) throws DataAccessException;
//
//	void insertNewImage(Map QnaMap) throws DataAccessException;
//
//	List selectImageFileList(int QnaNO) throws DataAccessException;


}