package com.onestop.GJ.admin.QNA.service;

import java.util.Map;

public interface AdminQnaService {

	Map listQnas(Map pagingMap) throws Exception;

	int addNewQna(Map qnaMap) throws Exception;

	void updateReply(Map qnaMap) throws Exception;

	Map viewQna(int qna_no) throws Exception;

	boolean checkPwd(int qna_no, int qna_pw) throws Exception;

	void modQna(Map<String, Object> qnaMap) throws Exception;

	Map selectParentQna(String qnaparent_no) throws Exception;

	void removeQna(int qna_no) throws Exception;

	Map searchBoardList(Map pagingMap) throws Exception;

}
