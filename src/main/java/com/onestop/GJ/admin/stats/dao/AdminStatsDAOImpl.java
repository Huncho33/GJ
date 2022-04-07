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
	
	  //검색 총 방문자 수
		@Override
		public int searchVisitTotCnt(Map dateMap) {
			int searchVisitTotCnt = sqlSession.selectOne("mapper.adminStats.searchVisitTotCnt", dateMap);
			return searchVisitTotCnt;
		}
	
	// 구별 방문자수 
	   @Override
	   public List<AdminStatsVO> getAddrTotVisit(Map visitMap) {
	      List<AdminStatsVO> getAddrTotVisit = sqlSession.selectList("mapper.adminStats.getAddrTotVisit", visitMap);
	      System.out.println("다오 getAddrTotVisit : "+ getAddrTotVisit );
	      return getAddrTotVisit;
	   }

//	방문자 성별 수
	@Override
	public List<AdminStatsVO> getGenderCnt(Map visitMap) {
		List<AdminStatsVO> getGenderCnt = sqlSession.selectList("mapper.adminStats.getGenderCnt", visitMap);
		System.out.println("남녀 비율 검색" + getGenderCnt);
		return getGenderCnt;
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

//  방문자 구별 검색 수
  @Override
  public List<AdminStatsVO> getSearchAddrTotList(Map dateMap) {
     List<AdminStatsVO> getSearchAddrTotList = sqlSession.selectList("mapper.adminStats.getSearchAddrTotList", dateMap);
     System.out.println("다오 getSearchAddrTotList : "+ getSearchAddrTotList );
     return getSearchAddrTotList;
  }
	
//	방문자 성별 수
	@Override
	public List searchGenderCnt(Map dateMap) {
		List<AdminStatsVO> searchGenderCnt = sqlSession.selectList("mapper.adminStats.searchGenderCnt", dateMap);
		System.out.println("검색 남녀 비율 검색" + searchGenderCnt);
		return searchGenderCnt;
	}
	
	//방문자 연령별 수
	   @Override
	   public List<AdminStatsVO> getAgeTotVisit(Map visitMap) {
	      List<AdminStatsVO> getAgeTotVisit = sqlSession.selectList("mapper.adminStats.getAgeTotVisit", visitMap);
	      System.out.println("검색 연령 비율 검색" + getAgeTotVisit);
	      return getAgeTotVisit;
	   }

	// 방문자 연령 검색수
	   @Override
	   public List<AdminStatsVO> searchAgeTotVisit(Map dateMap) {
	      List<AdminStatsVO> searchAgeTotVisit = sqlSession.selectList("mapper.adminStats.searchAgeTotVisit", dateMap);
	      System.out.println("검색 연령 비율 검색" + searchAgeTotVisit);
	      return searchAgeTotVisit;
	   }
	   
	   //기간별 방문자 수
	   @Override
	   public List<AdminStatsVO> totVisit(Map visitMap) {
	      List<AdminStatsVO> totVisit = sqlSession.selectList("mapper.adminStats.totVisit", visitMap);
	         System.out.println("검색 연령 비율 검색" + totVisit);
	         return totVisit;
	   }

	   //기간별 날짜
	   @Override
	   public List<AdminStatsVO> totVisitDate(Map visitMap) {
	      List<AdminStatsVO> totVisitDate = sqlSession.selectList("mapper.adminStats.totVisitDate", visitMap);
	         System.out.println("검색 연령 비율 검색" + totVisitDate);
	         return totVisitDate;
	   }

	   //기간별 검색 방문자 수
	@Override
	public List<AdminStatsVO> searchTotVisit(Map dateMap) {
	    List<AdminStatsVO> searchTotVisit = sqlSession.selectList("mapper.adminStats.searchTotVisit", dateMap);
        System.out.println("검색 방문자수 검색" + searchTotVisit);
        return searchTotVisit;
	}

	//기간별 검색 날짜
	@Override
	public List<AdminStatsVO> searchTotVisitDate(Map dateMap) {
	    List<AdminStatsVO> searchTotVisitDate = sqlSession.selectList("mapper.adminStats.searchTotVisitDate", dateMap);
        System.out.println("검색 날짜 " + searchTotVisitDate);
        return searchTotVisitDate;
	}

}