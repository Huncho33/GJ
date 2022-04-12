package com.onestop.GJ.apply.mon23.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("ApplyMonDAOImpl")
public class ApplyMonDAOImpl implements ApplyMonDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyMonVO selectResult(Map resultMap) throws DataAccessException {
		ApplyMonVO applymonVO = (ApplyMonVO) sqlSession.selectOne("mapper.apply.selectResult", resultMap);
		return applymonVO;
	}

	// 신청결과 값 넣기.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException {
		int mo_no = insertResultNO();
		articleMap.put("mo_no", mo_no);

		sqlSession.insert("mapper.apply.insertResult", articleMap);
		return mo_no;

	}

	// 신청번호
	public int insertResultNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.apply.insertResultNO");
	}

	// 다중 파일 업로드
	@Override
	public void insertNewFile(Map articleMap) throws DataAccessException {
		List<ApplyMonFileVO> monApplyFileList = (ArrayList) articleMap.get("imageFileList");
		int mo_no = (Integer) articleMap.get("mo_no");

		int MO_FILENO = selectNewImageFileNO();

		if (monApplyFileList != null && monApplyFileList.size() != 0) {

			for (ApplyMonFileVO applyMonFileVO : monApplyFileList) {
				applyMonFileVO.setUp_fileno(++MO_FILENO);
				applyMonFileVO.setMo_no(mo_no);
			}

		}
		sqlSession.insert("mapper.apply.insertNewImage", monApplyFileList);
	}

	// 파일 번호생성
	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.apply.selectMoFileNO");
	}

	// 결과페이지(memberVO id값을 통해 member테이블값 조회)
	@Override
	public ApplyMonVO findAll(String member_id) {
		ApplyMonVO list = null;
		list = sqlSession.selectOne("mapper.apply.all", member_id);
		return list;
	}

	// 결과페이지(mo_no값을 통해 applyMon테이블값 조회)
	@Override
	public ApplyMonVO findNo(int mo_no) {
		ApplyMonVO month = sqlSession.selectOne("mapper.apply.findNo", mo_no);
		return month;
	}

	@Override
	public MemberVO modifyMember(MemberVO member) {
		return sqlSession.selectOne("mapper.member.updateMember", member);
	}

}
