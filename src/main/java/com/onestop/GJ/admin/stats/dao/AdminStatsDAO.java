package com.onestop.GJ.admin.stats.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.onestop.GJ.admin.stats.vo.AdminStatsVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

public interface AdminStatsDAO {

	void insertVisit(Map visitMap);

	int getVisitTotCnt(Map visitMap);

	List<AdminStatsVO> selectVisitListBySearchVisit(Map dateMap);

	List<AdminStatsVO> getGenderCnt(Map visitMap);

	List searchGenderCnt(Map dateMap);

	List<AdminStatsVO> getAddrTotVisit(Map visitMap);

	List<AdminStatsVO> getSearchAddrTotList(Map dateMap);

	List<AdminStatsVO> getAgeTotVisit(Map visitMap);

	List<AdminStatsVO> searchAgeTotVisit(Map dateMap);

	int searchVisitTotCnt(Map dateMap);

	List<AdminStatsVO> totVisit(Map visitMap);

	List<AdminStatsVO> totVisitDate(Map visitMap);

	List<AdminStatsVO> searchTotVisit(Map dateMap);

	List<AdminStatsVO> searchTotVisitDate(Map dateMap);

	List<AdminStatsVO> selectAllVisitList(Map pagingMap);

	int searchMonApply(Map dateMap);

	int searchBackApply(Map dateMap);

	int searchRentApply(Map dateMap);

	int searchReturnApply(Map dateMap);

	int searchShareApply(Map dateMap);

	

}
