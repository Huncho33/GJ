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

//  방문자 수
	@Override
	public Map getTotCnt(Map visitMap) {
		int getVisitTotCnt = statsDAO.getVisitTotCnt(visitMap);
		int getSuguTotCnt = statsDAO.getSuguTotCnt(visitMap);
		int getJungguTotCnt = statsDAO.getJungguTotCnt(visitMap);
		int getDongguTotCnt = statsDAO.getDongguTotCnt(visitMap);
		int getNamguTotCnt = statsDAO.getNamguTotCnt(visitMap);
		int getBukguTotCnt = statsDAO.getBukguTotCnt(visitMap);
		int getDalsungTotCnt = statsDAO.getDalsungTotCnt(visitMap);
		int getDalsunggunTotCnt = statsDAO.getDalsunggunTotCnt(visitMap);
		int getSusungTotCnt = statsDAO.getSusungTotCnt(visitMap);
		
		visitMap.put("getVisitTotCnt", getVisitTotCnt);
		visitMap.put("getSuguTotCnt", getSuguTotCnt);
		visitMap.put("getJungguTotCnt", getJungguTotCnt);
		visitMap.put("getDongguTotCnt", getDongguTotCnt);
		visitMap.put("getNamguTotCnt", getNamguTotCnt);
		visitMap.put("getBukguTotCnt", getBukguTotCnt);
		visitMap.put("getDalsungTotCnt", getDalsungTotCnt);
		visitMap.put("getDalsunggunTotCnt", getDalsunggunTotCnt);
		visitMap.put("getSusungTotCnt", getSusungTotCnt);
		
		return visitMap;
	}

//	방문자 검색
	@Override
	public Map searchVisit(Map dateMap) {
		Map searchMap = new HashMap();
		System.out.println("서비스 dateMap : " + dateMap);
		List<AdminStatsVO> searchList = statsDAO.selectVisitListBySearchVisit(dateMap);
		int searchTotVisit = statsDAO.selectSearchTotVisit(dateMap);
		int searchTotSuguVisit = statsDAO.searchTotSuguVisit(dateMap);
		int searchTotJungguVisit = statsDAO.searchTotJungguVisit(dateMap);
		int searchTotDongguVisit = statsDAO.searchTotDongguVisit(dateMap);
		int searchTotNamguVisit = statsDAO.searchTotNamguVisit(dateMap);
		int searchTotBukguVisit = statsDAO.searchTotBukguVisit(dateMap);
		int searchTotDalsungVisit = statsDAO.searchTotDalsungVisit(dateMap);
		int searchTotDalsunggunVisit = statsDAO.searchTotDalsunggunVisit(dateMap);
		int searchTotSusungVisit = statsDAO.searchTotSusungVisit(dateMap);
		searchMap.put("searchList", searchList);
		searchMap.put("searchTotVisit", searchTotVisit);
		searchMap.put("searchTotSuguVisit", searchTotSuguVisit);
		searchMap.put("searchTotJungguVisit", searchTotJungguVisit);
		searchMap.put("searchTotDongguVisit", searchTotDongguVisit);
		searchMap.put("searchTotNamguVisit", searchTotNamguVisit);
		searchMap.put("searchTotBukguVisit", searchTotBukguVisit);
		searchMap.put("searchTotDalsungVisit", searchTotDalsungVisit);
		searchMap.put("searchTotDalsunggunVisit", searchTotDalsunggunVisit);
		searchMap.put("searchTotSusungVisit", searchTotSusungVisit);

		return searchMap;
	}

}