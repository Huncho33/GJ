package com.onestop.GJ.board.notice.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.board.notice.vo.BoardNoticeVO;

@Repository("boardDAO")
public class BoardNoticeDAOImpl implements BoardNoticeDAO {
   @Autowired
   private SqlSession sqlSession;

   @Override
   public List selectAllArticlesList() throws Exception {
      List<BoardNoticeVO> articlesList = sqlSession.selectList("mapper.boardNotice.selectAllArticlesList");
      return articlesList;
   }

   @Override
   public int insertNewArticle(Map articleMap) throws DataAccessException {
      int noti_NO = selectNewArticleNO();
      articleMap.put("noti_NO", noti_NO);
      Collection<String> value = articleMap.values();
      System.out.println(value);
      sqlSession.insert("mapper.boardNotice.insertNewArticle",articleMap);
      return noti_NO;
   }
    
   private int selectNewArticleNO() throws DataAccessException {
      return sqlSession.selectOne("mapper.boardNotice.selectNewArticleNO");
   }
   
//   private int selectNewImageFileNO() throws DataAccessException {
//      return sqlSession.selectOne("mapper.boardNotice.selectNewImageFileNO");
//   }


   @Override
   public BoardNoticeVO selectArticle(int noti_NO) throws DataAccessException {
      return sqlSession.selectOne("mapper.boardNotice.selectArticle", noti_NO);
   }

   @Override
   public void updateArticle(Map articleMap) throws DataAccessException {
      sqlSession.update("mapper.boardNotice.updateArticle", articleMap);
   }

   @Override
   public void deleteArticle(int noti_NO) throws DataAccessException {
      sqlSession.delete("mapper.boardNotice.deleteArticle", noti_NO);
   }






}