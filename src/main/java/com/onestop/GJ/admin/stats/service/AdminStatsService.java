package com.onestop.GJ.admin.stats.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AdminStatsService {

	List listStats();

	void insertVisit(Map visitMap);

	Map getTotCnt(Map visitMap);

	Map searchVisit(Map dateMap);
}
