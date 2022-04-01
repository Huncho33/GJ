package com.onestop.GJ.admin.stats.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.stats.dao.AdminStatsDAO;
import com.onestop.GJ.admin.stats.vo.AdminStatsVO;



@Service("adminStatsService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminStatsServiceImpl implements AdminStatsService {
   @Autowired
   private AdminStatsDAO statsDAO;
   
   
   @Override
   public List listStats() {
      List visitList = null;
      visitList = statsDAO.selectAllVisitList();
      return visitList;
   }


   @Override
   public void insertVisit(Map visitMap) {
      statsDAO.insertVisit(visitMap);
      
   }


   @Override
   public Map getTotCnt(Map visitMap) {
      int getVisitTotCnt = statsDAO.getVisitTotCnt(visitMap);
      visitMap.put("getVisitTotCnt", getVisitTotCnt);
      
      return visitMap;
   }


   @Override
   public Map searchVisit(Map dateMap) {
      Map searchMap = new HashMap();
      System.out.println("¼­ºñ½º dateMap : "+ dateMap);
      List<AdminStatsVO> searchList=statsDAO.selectVisitListBySearchVisit(dateMap);
      int searchTotVisit = statsDAO.selectSearchTotVisit(dateMap);
      searchMap.put("searchList", searchList);
      searchMap.put("searchTotVisit", searchTotVisit);
      
      return searchMap;
   }

}