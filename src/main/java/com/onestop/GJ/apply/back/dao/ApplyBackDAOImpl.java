package com.onestop.GJ.apply.back.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("ApplyBackDAOImpl")
public class ApplyBackDAOImpl implements ApplyBackDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyBackVO selectResult(Map resultMap) throws DataAccessException {
		ApplyBackVO applyBackVO = (ApplyBackVO) sqlSession.selectOne("mapper.back.selectResult", resultMap);
		return applyBackVO;
	}

	// 신청결과 값 넣기.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException {
		int ba_no = insertResultNO();
		articleMap.put("ba_no", ba_no);

		sqlSession.insert("mapper.back.insertResult", articleMap);
		return ba_no;

	}

	// 신청번호
	public int insertResultNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.back.insertResultNO");
	}

	// 다중 파일 업로드
	@Override
	public void insertNewFile(Map articleMap) throws DataAccessException {
		List<ApplyBackFileVO> backApplyFileList = (ArrayList) articleMap.get("imageFileList");
		int ba_no = (Integer) articleMap.get("ba_no");

		int MO_FILENO = selectNewImageFileNO();

		if (backApplyFileList != null && backApplyFileList.size() != 0) {

			for (ApplyBackFileVO applyBackFileVO : backApplyFileList) {
				applyBackFileVO.setUp_fileno(++MO_FILENO);
				applyBackFileVO.setBa_no(ba_no);
			}
		}
		sqlSession.insert("mapper.back.insertNewImage", backApplyFileList);
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.back.selectMoFileNO");
	}

	// 결과페이지
	@Override
	public ApplyBackVO findAll(String member_id) {
		ApplyBackVO list = null;
		list = sqlSession.selectOne("mapper.back.all", member_id);
		return list;
	}

	@Override
	public ApplyBackVO findNo(int ba_no) {
		ApplyBackVO month = sqlSession.selectOne("mapper.back.findNo", ba_no);
		return month;
	}

	@Override
	public MemberVO modifyMember(MemberVO member) {
		return sqlSession.selectOne("mapper.member.updateMember", member);
	}

}
