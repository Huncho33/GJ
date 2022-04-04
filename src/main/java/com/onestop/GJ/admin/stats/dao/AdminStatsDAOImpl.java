package com.onestop.GJ.admin.stats.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.stats.vo.AdminStatsVO;

@Repository("adminStatsDAO")
public class AdminStatsDAOImpl implements AdminStatsDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllVisitList() {
		List<AdminStatsVO> visitList = null;
		visitList = sqlSession.selectList("mapper.adminStats.selectAllVisitList");
		return visitList;
	}

	@Override
	public void insertVisit(Map visitMap) {
		sqlSession.insert("mapper.adminStats.insertVisit", visitMap);

	}

	// 방문자 수
	@Override
	public int getVisitTotCnt(Map visitMap) {
		int getVisitTotCnt = sqlSession.selectOne("mapper.adminStats.getVisitTotCnt", visitMap);
		return getVisitTotCnt;
	}
	@Override
	// 구별 방문자 수
	public int getSuguTotCnt(Map visitMap) {
		int getSuguTotCnt = sqlSession.selectOne("mapper.adminStats.getSuguTotCnt", visitMap);
		return getSuguTotCnt;
	}
	@Override
	public int getJungguTotCnt(Map visitMap) {
		int getJungguTotCnt = sqlSession.selectOne("mapper.adminStats.getJungguTotCnt", visitMap);
		return getJungguTotCnt;
	}
	@Override
	public int getDongguTotCnt(Map visitMap) {
		int getDongguTotCnt = sqlSession.selectOne("mapper.adminStats.getDongguTotCnt", visitMap);
		return getDongguTotCnt;
	}
	@Override
	public int getNamguTotCnt(Map visitMap) {
		int getNamguTotCnt = sqlSession.selectOne("mapper.adminStats.getNamguTotCnt", visitMap);
		return getNamguTotCnt;
	}
	@Override
	public int getBukguTotCnt(Map visitMap) {
		int getBukguTotCnt = sqlSession.selectOne("mapper.adminStats.getBukguTotCnt", visitMap);
		return getBukguTotCnt;
	}
	@Override
	public int getDalsungTotCnt(Map visitMap) {
		int getDalsungTotCnt = sqlSession.selectOne("mapper.adminStats.getDalsungTotCnt", visitMap);
		return getDalsungTotCnt;
	}
	@Override
	public int getDalsunggunTotCnt(Map visitMap) {
		int getDalsunggunTotCnt = sqlSession.selectOne("mapper.adminStats.getDalsunggunTotCnt", visitMap);
		return getDalsunggunTotCnt;
	}
	@Override
	public int getSusungTotCnt(Map visitMap) {
		int getSusungTotCnt = sqlSession.selectOne("mapper.adminStats.getSusungTotCnt", visitMap);
		return getSusungTotCnt;
	}

//  방문자 정보
	@Override
	public List<AdminStatsVO> selectVisitListBySearchVisit(Map dateMap) {
		System.out.println("다오 11dateMap : " + dateMap);
		List<AdminStatsVO> searchList = sqlSession.selectList("mapper.adminStats.selectVisitListBySearchVisit",
				dateMap);
		System.out.println("기간별 검색" + searchList);
		return searchList;
	}

//   방문자 검색 수
	@Override
	public int selectSearchTotVisit(Map dateMap) {
		System.out.println("dateMap : " + dateMap);
		int searchTotVisit = sqlSession.selectOne("mapper.adminStats.selectSearchTotVisit", dateMap);
		System.out.println("검색 방문자수" + searchTotVisit);
		return searchTotVisit;
	}
//	방문자 구별 검색 수
	@Override
	public int searchTotSuguVisit(Map dateMap) {
		System.out.println("dateMap sugu : " + dateMap);
		int searchTotSuguVisit = sqlSession.selectOne("mapper.adminStats.searchTotSuguVisit", dateMap);
		return searchTotSuguVisit;
	}
	@Override
	public int searchTotJungguVisit(Map dateMap) {
		int searchTotJungguVisit = sqlSession.selectOne("mapper.adminStats.searchTotJungguVisit", dateMap);
		return searchTotJungguVisit;
	}
	@Override
	public int searchTotDongguVisit(Map dateMap) {
		int searchTotDongguVisit = sqlSession.selectOne("mapper.adminStats.searchTotDongguVisit", dateMap);
		return searchTotDongguVisit;
	}
	@Override
	public int searchTotNamguVisit(Map dateMap) {
		int searchTotNamguVisit = sqlSession.selectOne("mapper.adminStats.searchTotNamguVisit", dateMap);
		return searchTotNamguVisit;
	}
	@Override
	public int searchTotBukguVisit(Map dateMap) {
		int searchTotBukguVisit = sqlSession.selectOne("mapper.adminStats.searchTotBukguVisit", dateMap);
		return searchTotBukguVisit;
	}
	@Override
	public int searchTotDalsungVisit(Map dateMap) {
		int searchTotDalsungVisit = sqlSession.selectOne("mapper.adminStats.searchTotDalsungVisit", dateMap);
		return searchTotDalsungVisit;
	}
	@Override
	public int searchTotDalsunggunVisit(Map dateMap) {
		int searchTotDalsunggunVisit = sqlSession.selectOne("mapper.adminStats.searchTotDalsunggunVisit", dateMap);
		return searchTotDalsunggunVisit;
	}
	@Override
	public int searchTotSusungVisit(Map dateMap) {
		int searchTotSusungVisit = sqlSession.selectOne("mapper.adminStats.searchTotSusungVisit", dateMap);
		return searchTotSusungVisit;
	}
	

}