package com.onestop.GJ.board.QNA.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.board.QNA.vo.QnaVO;


public interface QnaService {

	List<QnaVO> listQnas() throws Exception;

	int addNewQna(Map QnaMap) throws Exception;

	Map viewQna(int QnaNO) throws Exception;

//	void modQna(Map QnaMap) throws Exception;
//
//	void removeQna(int QnaNO) throws Exception;


}