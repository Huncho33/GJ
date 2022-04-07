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

//  �湮�� ��
   @Override
   public Map getTotCnt(Map visitMap) {
      int getVisitTotCnt = statsDAO.getVisitTotCnt(visitMap);
      List<AdminStatsVO> getAddrTotVisit = statsDAO.getAddrTotVisit(visitMap);
      List<AdminStatsVO> getAgeTotVisit = statsDAO.getAgeTotVisit(visitMap);

      List getGenderCnt = statsDAO.getGenderCnt(visitMap);

      visitMap.put("getVisitTotCnt", getVisitTotCnt);
      visitMap.put("getAddrTotVisit", getAddrTotVisit);
      visitMap.put("getAgeTotVisit", getAgeTotVisit);
      visitMap.put("getGenderCnt", getGenderCnt);//���� ��

      return visitMap;
   }

//   �湮�� �˻�
   @Override
   public Map searchVisit(Map dateMap) {
      Map searchMap = new HashMap();
      System.out.println("���� dateMap : " + dateMap);
      int searchVisitTotCnt = statsDAO.searchVisitTotCnt(dateMap);
      List<AdminStatsVO> searchList = statsDAO.selectVisitListBySearchVisit(dateMap);
      List<AdminStatsVO> searchAddrList = statsDAO.getSearchAddrTotList(dateMap);
      List<AdminStatsVO> searchAgeTotVisit = statsDAO.searchAgeTotVisit(dateMap);
      System.out.println("���� searchAddrList : " + searchAddrList);

      List searchGenderCnt = statsDAO.searchGenderCnt(dateMap);

      searchMap.put("searchList", searchList);
      searchMap.put("searchVisitTotCnt", searchVisitTotCnt);//�˻� �� �湮�� ��
      searchMap.put("searchAddrList", searchAddrList); //���� �˻���
      searchMap.put("searchAgeTotVisit", searchAgeTotVisit); //���ɺ� �˻��� 
      searchMap.put("searchGenderCnt", searchGenderCnt);//���� �˻���

      return searchMap;
   }

}