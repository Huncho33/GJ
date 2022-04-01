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

	int selectSearchTotVisit(Map dateMap);

}
