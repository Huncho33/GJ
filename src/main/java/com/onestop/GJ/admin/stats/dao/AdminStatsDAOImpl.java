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

	// �湮�� ��
	@Override
	public int getVisitTotCnt(Map visitMap) {
		int getVisitTotCnt = sqlSession.selectOne("mapper.adminStats.getVisitTotCnt", visitMap);
		return getVisitTotCnt;
	}
	
	  //�˻� �� �湮�� ��
		@Override
		public int searchVisitTotCnt(Map dateMap) {
			int searchVisitTotCnt = sqlSession.selectOne("mapper.adminStats.searchVisitTotCnt", dateMap);
			return searchVisitTotCnt;
		}
	
	// ���� �湮�ڼ� 
	   @Override
	   public List<AdminStatsVO> getAddrTotVisit(Map visitMap) {
	      List<AdminStatsVO> getAddrTotVisit = sqlSession.selectList("mapper.adminStats.getAddrTotVisit", visitMap);
	      System.out.println("�ٿ� getAddrTotVisit : "+ getAddrTotVisit );
	      return getAddrTotVisit;
	   }

//	�湮�� ���� ��
	@Override
	public List<AdminStatsVO> getGenderCnt(Map visitMap) {
		List<AdminStatsVO> getGenderCnt = sqlSession.selectList("mapper.adminStats.getGenderCnt", visitMap);
		System.out.println("���� ���� �˻�" + getGenderCnt);
		return getGenderCnt;
	}

//  �湮�� ����
	@Override
	public List<AdminStatsVO> selectVisitListBySearchVisit(Map dateMap) {
		System.out.println("�ٿ� 11dateMap : " + dateMap);
		List<AdminStatsVO> searchList = sqlSession.selectList("mapper.adminStats.selectVisitListBySearchVisit",
				dateMap);
		System.out.println("�Ⱓ�� �˻�" + searchList);
		return searchList;
	}

//  �湮�� ���� �˻� ��
  @Override
  public List<AdminStatsVO> getSearchAddrTotList(Map dateMap) {
     List<AdminStatsVO> getSearchAddrTotList = sqlSession.selectList("mapper.adminStats.getSearchAddrTotList", dateMap);
     System.out.println("�ٿ� getSearchAddrTotList : "+ getSearchAddrTotList );
     return getSearchAddrTotList;
  }
	
//	�湮�� ���� ��
	@Override
	public List searchGenderCnt(Map dateMap) {
		List<AdminStatsVO> searchGenderCnt = sqlSession.selectList("mapper.adminStats.searchGenderCnt", dateMap);
		System.out.println("�˻� ���� ���� �˻�" + searchGenderCnt);
		return searchGenderCnt;
	}
	
	//�湮�� ���ɺ� ��
	   @Override
	   public List<AdminStatsVO> getAgeTotVisit(Map visitMap) {
	      List<AdminStatsVO> getAgeTotVisit = sqlSession.selectList("mapper.adminStats.getAgeTotVisit", visitMap);
	      System.out.println("�˻� ���� ���� �˻�" + getAgeTotVisit);
	      return getAgeTotVisit;
	   }

	// �湮�� ���� �˻���
	   @Override
	   public List<AdminStatsVO> searchAgeTotVisit(Map dateMap) {
	      List<AdminStatsVO> searchAgeTotVisit = sqlSession.selectList("mapper.adminStats.searchAgeTotVisit", dateMap);
	      System.out.println("�˻� ���� ���� �˻�" + searchAgeTotVisit);
	      return searchAgeTotVisit;
	   }

	 

	
	

}