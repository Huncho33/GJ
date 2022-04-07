package com.onestop.GJ.admin.stats.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.onestop.GJ.admin.stats.vo.AdminStatsVO;

public interface AdminStatsDAO {

	List selectAllVisitList();

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

	

}
