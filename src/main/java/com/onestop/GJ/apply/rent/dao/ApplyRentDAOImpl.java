package com.onestop.GJ.apply.rent.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("ApplyRentDAOImpl")
public class ApplyRentDAOImpl implements ApplyRentDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyRentVO selectResult(Map resultMap) throws DataAccessException {
		ApplyRentVO applyrentVO = (ApplyRentVO) sqlSession.selectOne("mapper.rent.selectResult", resultMap);
		return applyrentVO;
	}

	// 신청결과 값 넣기.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException {
		int rent_no = insertResultNO();
		articleMap.put("rent_no", rent_no);

		sqlSession.insert("mapper.rent.insertResult", articleMap);
		return rent_no;

	}

	// 신청번호
	public int insertResultNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.rent.insertResultNO");
	}

	// 다중 파일 업로드
	@Override
	public void insertNewFile(Map articleMap) throws DataAccessException {
		List<ApplyRentFileVO> rentApplyFileList = (ArrayList) articleMap.get("imageFileList");
		int rent_no = (Integer) articleMap.get("rent_no");

		int RENT_FILENO = selectNewImageFileNO();

		if (rentApplyFileList != null && rentApplyFileList.size() != 0) {
			for (ApplyRentFileVO applyRentFileVO : rentApplyFileList) {
				applyRentFileVO.setUp_fileno(++RENT_FILENO);
				applyRentFileVO.setRent_no(rent_no);
			}
			sqlSession.insert("mapper.rent.insertNewImage", rentApplyFileList);
		}
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.rent.selectRentFileNO");
	}

	// 결과페이지
	@Override
	public ApplyRentVO findAll(String member_id) {
		ApplyRentVO list = null;
		list = sqlSession.selectOne("mapper.rent.all", member_id);
		return list;
	}

	@Override
	public ApplyRentVO findNo(int rent_no) {
		ApplyRentVO Rent = sqlSession.selectOne("mapper.rent.findNo", rent_no);
		return Rent;
	}

	@Override
	public MemberVO modifyMember(MemberVO member) {
		return sqlSession.selectOne("mapper.member.updateMember", member);
	}

}
