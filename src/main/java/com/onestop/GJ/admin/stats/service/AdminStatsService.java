package com.onestop.GJ.admin.stats.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AdminStatsService {

	void insertVisit(Map visitMap);

	Map getTotCnt(Map visitMap);

	Map searchVisit(Map dateMap);

	Map listStats(Map pagingMap);
}
