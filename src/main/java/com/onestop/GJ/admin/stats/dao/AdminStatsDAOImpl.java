package com.onestop.GJ.admin.stats.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.stats.vo.AdminStatsVO;


@Repository("adminStatsDAO")
public class AdminStatsDAOImpl implements AdminStatsDAO {
   @Autowired
   private SqlSession sqlSession;
   @Override
   public List selectAllVisitList() {
      List<AdminStatsVO> visitList = null;
      visitList = sqlSession.selectList("mapper.adminStats.selectAllVisitList");
      return visitList;
   }
   @Override
   public void insertVisit(Map visitMap) {
      sqlSession.insert("mapper.adminStats.insertVisit", visitMap);
      
   }
   @Override
   public int getVisitTotCnt(Map visitMap) {
      int getVisitTotCnt = sqlSession.selectOne("mapper.adminStats.getVisitTotCnt",visitMap );
      return getVisitTotCnt;
   }
   @Override
   public List<AdminStatsVO> selectVisitListBySearchVisit(Map dateMap) {
      System.out.println("�ٿ� 11dateMap : "+ dateMap);
      List<AdminStatsVO> searchList=sqlSession.selectList("mapper.adminStats.selectVisitListBySearchVisit", dateMap);
      System.out.println("�Ⱓ�� �˻�" + searchList);
      return searchList;
   }
   @Override
   public int selectSearchTotVisit(Map dateMap) {
      System.out.println("dateMap : "+ dateMap);
      int searchTotVisit = sqlSession.selectOne("mapper.adminStats.selectSearchTotVisit", dateMap);
      System.out.println("�˻� �湮�ڼ�" + searchTotVisit);
      return searchTotVisit;
   }

}