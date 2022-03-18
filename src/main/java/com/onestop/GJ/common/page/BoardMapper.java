package com.onestop.GJ.common.page;

import java.util.List;

import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

public interface BoardMapper {
	
	//�Խ��� ���
	public void addNewArticle(BoardNoticeVO boardNoticeVO);
	
	 /* �Խ��� ���
    public List<BoardNoticeVO> getList(); */
    
    //�Խ��� ���(����¡ ����)
    public List<BoardNoticeVO> listArticles(Criteria cri);
    
    //�Խ��� ��ȸ
    public BoardNoticeVO viewArticle(int noti_NO);
    
    //�Խ��� ����
    public int modArticle(BoardNoticeVO boardNoticeVO);
    
    //�Խ��� ����
    public int removeArticle(int noti_NO);
    

}
