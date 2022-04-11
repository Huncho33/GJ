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

	// �湮�� ����Ʈ
	@Override
	public List selectAllVisitList(Map pagingMap) {
		List<AdminStatsVO> visitList = null;
		visitList = sqlSession.selectList("mapper.adminStats.selectAllVisitList", pagingMap);
		return visitList;
	}

	// �湮�� �߰�
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

	// �˻� �� �湮�� ��
	@Override
	public int searchVisitTotCnt(Map dateMap) {
		int searchVisitTotCnt = sqlSession.selectOne("mapper.adminStats.searchVisitTotCnt", dateMap);
		return searchVisitTotCnt;
	}

	// ���� �湮�ڼ�
	@Override
	public List<AdminStatsVO> getAddrTotVisit(Map visitMap) {
		List<AdminStatsVO> getAddrTotVisit = sqlSession.selectList("mapper.adminStats.getAddrTotVisit", visitMap);
		return getAddrTotVisit;
	}

	// �湮�� ���� ��
	@Override
	public List<AdminStatsVO> getGenderCnt(Map visitMap) {
		List<AdminStatsVO> getGenderCnt = sqlSession.selectList("mapper.adminStats.getGenderCnt", visitMap);
		return getGenderCnt;
	}

	// �湮�� ����
	@Override
	public List<AdminStatsVO> selectVisitListBySearchVisit(Map dateMap) {
		List<AdminStatsVO> searchList = sqlSession.selectList("mapper.adminStats.selectVisitListBySearchVisit",
				dateMap);
		return searchList;
	}

	// �湮�� ���� �˻� ��
	@Override
	public List<AdminStatsVO> getSearchAddrTotList(Map dateMap) {
		List<AdminStatsVO> getSearchAddrTotList = sqlSession.selectList("mapper.adminStats.getSearchAddrTotList",
				dateMap);
		return getSearchAddrTotList;
	}

	// �湮�� ���� ��
	@Override
	public List searchGenderCnt(Map dateMap) {
		List<AdminStatsVO> searchGenderCnt = sqlSession.selectList("mapper.adminStats.searchGenderCnt", dateMap);
		return searchGenderCnt;
	}

	// �湮�� ���ɺ� ��
	@Override
	public List<AdminStatsVO> getAgeTotVisit(Map visitMap) {
		List<AdminStatsVO> getAgeTotVisit = sqlSession.selectList("mapper.adminStats.getAgeTotVisit", visitMap);
		return getAgeTotVisit;
	}

	// �湮�� ���� �˻���
	@Override
	public List<AdminStatsVO> searchAgeTotVisit(Map dateMap) {
		List<AdminStatsVO> searchAgeTotVisit = sqlSession.selectList("mapper.adminStats.searchAgeTotVisit", dateMap);
		return searchAgeTotVisit;
	}

	// �Ⱓ�� �湮�� ��
	@Override
	public List<AdminStatsVO> totVisit(Map visitMap) {
		List<AdminStatsVO> totVisit = sqlSession.selectList("mapper.adminStats.totVisit", visitMap);
		return totVisit;
	}

	// �Ⱓ�� ��¥
	@Override
	public List<AdminStatsVO> totVisitDate(Map visitMap) {
		List<AdminStatsVO> totVisitDate = sqlSession.selectList("mapper.adminStats.totVisitDate", visitMap);
		return totVisitDate;
	}

	// �Ⱓ�� �˻� �湮�� ��
	@Override
	public List<AdminStatsVO> searchTotVisit(Map dateMap) {
		List<AdminStatsVO> searchTotVisit = sqlSession.selectList("mapper.adminStats.searchTotVisit", dateMap);
		return searchTotVisit;
	}

	// �Ⱓ�� �˻� ��¥
	@Override
	public List<AdminStatsVO> searchTotVisitDate(Map dateMap) {
		List<AdminStatsVO> searchTotVisitDate = sqlSession.selectList("mapper.adminStats.searchTotVisitDate", dateMap);
		return searchTotVisitDate;
	}

	// ���� ��û �˻� ��
	@Override
	public int searchMonApply(Map dateMap) {
		int searchMonApply = sqlSession.selectOne("mapper.adminStats.searchMonApply", dateMap);
		return searchMonApply;
	}

	// ��ȯ ��û �˻� ��
	@Override
	public int searchBackApply(Map dateMap) {
		int searchBackApply = sqlSession.selectOne("mapper.adminStats.searchBackApply", dateMap);
		return searchBackApply;
	}

	// ���� ��û �˻� ��
	@Override
	public int searchRentApply(Map dateMap) {
		int searchRentApply = sqlSession.selectOne("mapper.adminStats.searchRentApply", dateMap);
		return searchRentApply;
	}

	// ��ȯ ��û �˻� ��
	@Override
	public int searchReturnApply(Map dateMap) {
		int searchReturnApply = sqlSession.selectOne("mapper.adminStats.searchReturnApply", dateMap);
		return searchReturnApply;
	}

	// ���� ��û �˻� ��
	@Override
	public int searchShareApply(Map dateMap) {
		int searchShareApply = sqlSession.selectOne("mapper.adminStats.searchShareApply", dateMap);
		return searchShareApply;
	}

}