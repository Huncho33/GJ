package com.onestop.GJ.board.QNA.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.board.QNA.vo.QnaVO;


public interface QnaService {

	Map listQnas(Map pagingMap) throws Exception;

	int addNewQna(Map QnaMap) throws Exception;

	Map viewQna(int QnaNO) throws Exception;

	void removeQna(int qna_no) throws Exception;

	void modQna(Map QnaMap) throws Exception;

	boolean checkPwd(int qna_no, int qna_pw) throws Exception;

	Map selectParentQna(String qnaparent_no) throws Exception;

	void updateReply(Map QnaMap) throws Exception;


}