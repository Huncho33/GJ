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
	int getDalsunggunTotCnt(Map visitMap);
	int getSuguTotCnt(Map visitMap);
	int getJungguTotCnt(Map visitMap);
	int getDongguTotCnt(Map visitMap);
	int getNamguTotCnt(Map visitMap);
	int getBukguTotCnt(Map visitMap);
	int getDalsungTotCnt(Map visitMap);
	int getSusungTotCnt(Map visitMap);

	int searchTotSuguVisit(Map dateMap);
	int searchTotJungguVisit(Map dateMap);
	int searchTotDongguVisit(Map dateMap);
	int searchTotNamguVisit(Map dateMap);
	int searchTotBukguVisit(Map dateMap);
	int searchTotDalsungVisit(Map dateMap);
	int searchTotDalsunggunVisit(Map dateMap);
	int searchTotSusungVisit(Map dateMap);

}
