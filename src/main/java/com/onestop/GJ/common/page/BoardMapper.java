package com.onestop.GJ.common.page;

import java.util.List;

import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

public interface BoardMapper {
	
	//게시판 등록
	public void addNewArticle(BoardNoticeVO boardNoticeVO);
	
	 /* 게시판 목록
    public List<BoardNoticeVO> getList(); */
    
    //게시판 목록(페이징 적용)
    public List<BoardNoticeVO> listArticles(Criteria cri);
    
    //게시판 조회
    public BoardNoticeVO viewArticle(int noti_NO);
    
    //게시판 수정
    public int modArticle(BoardNoticeVO boardNoticeVO);
    
    //게시판 삭제
    public int removeArticle(int noti_NO);
    

}
