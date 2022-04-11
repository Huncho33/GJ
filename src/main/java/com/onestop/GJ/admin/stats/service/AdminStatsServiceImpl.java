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

//  �湮�� ��
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
		visitMap.put("getGenderCnt", getGenderCnt);// ���� ��
		visitMap.put("totVisit", totVisit);
		visitMap.put("totVisitDate", totVisitDate);

		return visitMap;
	}

	// �湮�� �˻�
	@Override
	public Map searchVisit(Map dateMap) {
		Map searchMap = new HashMap();
		int searchVisitTotCnt = statsDAO.searchVisitTotCnt(dateMap);
		List<AdminStatsVO> searchList = statsDAO.selectVisitListBySearchVisit(dateMap);
		List<AdminStatsVO> searchAddrList = statsDAO.getSearchAddrTotList(dateMap);
		List<AdminStatsVO> searchAgeTotVisit = statsDAO.searchAgeTotVisit(dateMap);
		List<AdminStatsVO> searchTotVisit = statsDAO.searchTotVisit(dateMap);
		List<AdminStatsVO> searchTotVisitDate = statsDAO.searchTotVisitDate(dateMap);

		// ��û�� �˻� ��
		int searchMonApply = statsDAO.searchMonApply(dateMap);
		int searchBackApply = statsDAO.searchBackApply(dateMap);
		int searchRentApply = statsDAO.searchRentApply(dateMap);
		int searchReturnApply = statsDAO.searchReturnApply(dateMap);
		int searchShareApply = statsDAO.searchShareApply(dateMap);

		List searchGenderCnt = statsDAO.searchGenderCnt(dateMap);

		searchMap.put("searchList", searchList);
		searchMap.put("searchVisitTotCnt", searchVisitTotCnt);// �˻� �� �湮�� ��
		searchMap.put("searchAddrList", searchAddrList); // ���� �˻���
		searchMap.put("searchAgeTotVisit", searchAgeTotVisit); // ���ɺ� �˻���
		searchMap.put("searchGenderCnt", searchGenderCnt);// ���� �˻���
		searchMap.put("searchTotVisit", searchTotVisit);// ���� �˻���
		searchMap.put("searchTotVisitDate", searchTotVisitDate);// ���� �˻���

		searchMap.put("searchMonApply", searchMonApply);// ���� �˻���
		searchMap.put("searchBackApply", searchBackApply);// ��ȯ �˻���
		searchMap.put("searchRentApply", searchRentApply);// �����˻���
		searchMap.put("searchReturnApply", searchReturnApply);// ��ȯ �˻���
		searchMap.put("searchShareApply", searchShareApply);// ���� �˻���

		return searchMap;
	}

}