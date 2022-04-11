package com.onestop.GJ.admin.stats.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.stats.dao.AdminStatsDAO;
import com.onestop.GJ.admin.stats.vo.AdminStatsVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

@Service("adminStatsService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminStatsServiceImpl implements AdminStatsService {
	@Autowired
	private AdminStatsDAO statsDAO;

	@Override
	public Map listStats(Map pagingMap) {
		Map visitMap = new HashMap();
		List<AdminStatsVO> visitList = statsDAO.selectAllVisitList(pagingMap);
		visitMap.put("visitList", visitList);
		return visitMap;
	}

	@Override
	public void insertVisit(Map visitMap) {
		statsDAO.insertVisit(visitMap);
	}

//  방문자 수
	@Override
	public Map getTotCnt(Map visitMap) {
		int getVisitTotCnt = statsDAO.getVisitTotCnt(visitMap);
		List<AdminStatsVO> getAddrTotVisit = statsDAO.getAddrTotVisit(visitMap);
		List<AdminStatsVO> getAgeTotVisit = statsDAO.getAgeTotVisit(visitMap);
		List<AdminStatsVO> totVisit = statsDAO.totVisit(visitMap);
		List<AdminStatsVO> totVisitDate = statsDAO.totVisitDate(visitMap);

		List getGenderCnt = statsDAO.getGenderCnt(visitMap);

		visitMap.put("getVisitTotCnt", getVisitTotCnt);
		visitMap.put("getAddrTotVisit", getAddrTotVisit);
		visitMap.put("getAgeTotVisit", getAgeTotVisit);
		visitMap.put("getGenderCnt", getGenderCnt);// 성별 수
		visitMap.put("totVisit", totVisit);
		visitMap.put("totVisitDate", totVisitDate);

		return visitMap;
	}

	// 방문자 검색
	@Override
	public Map searchVisit(Map dateMap) {
		Map searchMap = new HashMap();
		int searchVisitTotCnt = statsDAO.searchVisitTotCnt(dateMap);
		List<AdminStatsVO> searchList = statsDAO.selectVisitListBySearchVisit(dateMap);
		List<AdminStatsVO> searchAddrList = statsDAO.getSearchAddrTotList(dateMap);
		List<AdminStatsVO> searchAgeTotVisit = statsDAO.searchAgeTotVisit(dateMap);
		List<AdminStatsVO> searchTotVisit = statsDAO.searchTotVisit(dateMap);
		List<AdminStatsVO> searchTotVisitDate = statsDAO.searchTotVisitDate(dateMap);

		// 신청별 검색 수
		int searchMonApply = statsDAO.searchMonApply(dateMap);
		int searchBackApply = statsDAO.searchBackApply(dateMap);
		int searchRentApply = statsDAO.searchRentApply(dateMap);
		int searchReturnApply = statsDAO.searchReturnApply(dateMap);
		int searchShareApply = statsDAO.searchShareApply(dateMap);

		List searchGenderCnt = statsDAO.searchGenderCnt(dateMap);

		searchMap.put("searchList", searchList);
		searchMap.put("searchVisitTotCnt", searchVisitTotCnt);// 검색 총 방문자 수
		searchMap.put("searchAddrList", searchAddrList); // 구별 검색수
		searchMap.put("searchAgeTotVisit", searchAgeTotVisit); // 연령별 검색수
		searchMap.put("searchGenderCnt", searchGenderCnt);// 성별 검색수
		searchMap.put("searchTotVisit", searchTotVisit);// 성별 검색수
		searchMap.put("searchTotVisitDate", searchTotVisitDate);// 성별 검색수

		searchMap.put("searchMonApply", searchMonApply);// 월세 검색수
		searchMap.put("searchBackApply", searchBackApply);// 귀환 검색수
		searchMap.put("searchRentApply", searchRentApply);// 전세검색수
		searchMap.put("searchReturnApply", searchReturnApply);// 반환 검색수
		searchMap.put("searchShareApply", searchShareApply);// 공공 검색수

		return searchMap;
	}

}