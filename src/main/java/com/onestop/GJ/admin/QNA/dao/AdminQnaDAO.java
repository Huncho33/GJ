package com.onestop.GJ.admin.QNA.dao;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.admin.QNA.vo.AdminQnaVO;

public interface AdminQnaDAO {

	List<AdminQnaVO> selectAllQnasList(Map pagingMap) throws Exception;

	int selectTotQnas();

	int insertNewQna(Map qnaMap);

	void updateReply(Map qnaMap);

	AdminQnaVO selectQna(int qna_no);

	AdminQnaVO selectParentQna(int _qnaparent_no);

	void updateQna(Map qnaMap);

	void deleteQna(int qna_no);

	boolean checkPwd(int qna_no, int qna_pw);

	List<AdminQnaDAO> selectBoardListBySearchWord(Map pagingMap);

	int selectSearchTotArticles(Map pagingMap);


}
