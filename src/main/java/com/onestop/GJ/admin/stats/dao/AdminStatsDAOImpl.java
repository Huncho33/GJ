package com.onestop.GJ.admin.stats.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.stats.vo.AdminStatsVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

@Repository("adminStatsDAO")
public class AdminStatsDAOImpl implements AdminStatsDAO {
	@Autowired
	private SqlSession sqlSession;

	// 방문자 리스트
	@Override
	public List selectAllVisitList(Map pagingMap) {
		List<AdminStatsVO> visitList = null;
		visitList = sqlSession.selectList("mapper.adminStats.selectAllVisitList", pagingMap);
		return visitList;
	}

	// 방문자 추가
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

	// 검색 총 방문자 수
	@Override
	public int searchVisitTotCnt(Map dateMap) {
		int searchVisitTotCnt = sqlSession.selectOne("mapper.adminStats.searchVisitTotCnt", dateMap);
		return searchVisitTotCnt;
	}

	// 구별 방문자수
	@Override
	public List<AdminStatsVO> getAddrTotVisit(Map visitMap) {
		List<AdminStatsVO> getAddrTotVisit = sqlSession.selectList("mapper.adminStats.getAddrTotVisit", visitMap);
		return getAddrTotVisit;
	}

	// 방문자 성별 수
	@Override
	public List<AdminStatsVO> getGenderCnt(Map visitMap) {
		List<AdminStatsVO> getGenderCnt = sqlSession.selectList("mapper.adminStats.getGenderCnt", visitMap);
		return getGenderCnt;
	}

	// 방문자 정보
	@Override
	public List<AdminStatsVO> selectVisitListBySearchVisit(Map dateMap) {
		List<AdminStatsVO> searchList = sqlSession.selectList("mapper.adminStats.selectVisitListBySearchVisit",
				dateMap);
		return searchList;
	}

	// 방문자 구별 검색 수
	@Override
	public List<AdminStatsVO> getSearchAddrTotList(Map dateMap) {
		List<AdminStatsVO> getSearchAddrTotList = sqlSession.selectList("mapper.adminStats.getSearchAddrTotList",
				dateMap);
		return getSearchAddrTotList;
	}

	// 방문자 성별 수
	@Override
	public List searchGenderCnt(Map dateMap) {
		List<AdminStatsVO> searchGenderCnt = sqlSession.selectList("mapper.adminStats.searchGenderCnt", dateMap);
		return searchGenderCnt;
	}

	// 방문자 연령별 수
	@Override
	public List<AdminStatsVO> getAgeTotVisit(Map visitMap) {
		List<AdminStatsVO> getAgeTotVisit = sqlSession.selectList("mapper.adminStats.getAgeTotVisit", visitMap);
		return getAgeTotVisit;
	}

	// 방문자 연령 검색수
	@Override
	public List<AdminStatsVO> searchAgeTotVisit(Map dateMap) {
		List<AdminStatsVO> searchAgeTotVisit = sqlSession.selectList("mapper.adminStats.searchAgeTotVisit", dateMap);
		return searchAgeTotVisit;
	}

	// 기간별 방문자 수
	@Override
	public List<AdminStatsVO> totVisit(Map visitMap) {
		List<AdminStatsVO> totVisit = sqlSession.selectList("mapper.adminStats.totVisit", visitMap);
		return totVisit;
	}

	// 기간별 날짜
	@Override
	public List<AdminStatsVO> totVisitDate(Map visitMap) {
		List<AdminStatsVO> totVisitDate = sqlSession.selectList("mapper.adminStats.totVisitDate", visitMap);
		return totVisitDate;
	}

	// 기간별 검색 방문자 수
	@Override
	public List<AdminStatsVO> searchTotVisit(Map dateMap) {
		List<AdminStatsVO> searchTotVisit = sqlSession.selectList("mapper.adminStats.searchTotVisit", dateMap);
		return searchTotVisit;
	}

	// 기간별 검색 날짜
	@Override
	public List<AdminStatsVO> searchTotVisitDate(Map dateMap) {
		List<AdminStatsVO> searchTotVisitDate = sqlSession.selectList("mapper.adminStats.searchTotVisitDate", dateMap);
		return searchTotVisitDate;
	}

	// 월세 신청 검색 수
	@Override
	public int searchMonApply(Map dateMap) {
		int searchMonApply = sqlSession.selectOne("mapper.adminStats.searchMonApply", dateMap);
		return searchMonApply;
	}

	// 귀환 신청 검색 수
	@Override
	public int searchBackApply(Map dateMap) {
		int searchBackApply = sqlSession.selectOne("mapper.adminStats.searchBackApply", dateMap);
		return searchBackApply;
	}

	// 전세 신청 검색 수
	@Override
	public int searchRentApply(Map dateMap) {
		int searchRentApply = sqlSession.selectOne("mapper.adminStats.searchRentApply", dateMap);
		return searchRentApply;
	}

	// 반환 신청 검색 수
	@Override
	public int searchReturnApply(Map dateMap) {
		int searchReturnApply = sqlSession.selectOne("mapper.adminStats.searchReturnApply", dateMap);
		return searchReturnApply;
	}

	// 공공 신청 검색 수
	@Override
	public int searchShareApply(Map dateMap) {
		int searchShareApply = sqlSession.selectOne("mapper.adminStats.searchShareApply", dateMap);
		return searchShareApply;
	}

}