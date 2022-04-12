package com.onestop.GJ.apply.rent_return.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnFileVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("ApplyRentReturnDAOImpl")
public class ApplyRentReturnDAOImpl implements ApplyRentReturnDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyRentReturnVO selectResult(Map resultMap) throws DataAccessException {
		ApplyRentReturnVO applyrentReturnVO = (ApplyRentReturnVO) sqlSession.selectOne("mapper.rentReturn.selectResult",
				resultMap);
		return applyrentReturnVO;
	}

	// 신청결과 값 넣기.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException {
		int ret_no = insertResultNO();
		articleMap.put("ret_no", ret_no);

		sqlSession.insert("mapper.rentReturn.insertResult", articleMap);
		return ret_no;

	}

	// 신청번호
	public int insertResultNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.rentReturn.insertResultNO");
	}

	// 다중 파일 업로드
	@Override
	public void insertNewFile(Map articleMap) throws DataAccessException {
		List<ApplyRentReturnFileVO> rentReturnApplyFileList = (ArrayList) articleMap.get("imageFileList");
		int return_no = (Integer) articleMap.get("return_no");

		int RETURN_FILENO = selectNewImageFileNO();

		if (rentReturnApplyFileList != null && rentReturnApplyFileList.size() != 0) {
			for (ApplyRentReturnFileVO applyrentReturnFileVO : rentReturnApplyFileList) {
				applyrentReturnFileVO.setUp_fileno(++RETURN_FILENO);
				applyrentReturnFileVO.setRet_no(return_no);
			}
			sqlSession.insert("mapper.rentReturn.insertNewImage", rentReturnApplyFileList);
		}
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.rentReturn.selectMoFileNO");
	}

	// 결과페이지
	@Override
	public ApplyRentReturnVO findAll(String member_id) {
		ApplyRentReturnVO list = null;
		list = sqlSession.selectOne("mapper.rentReturn.all", member_id);
		return list;
	}

	@Override
	public ApplyRentReturnVO findNo(int mo_no) {
		ApplyRentReturnVO ret = sqlSession.selectOne("mapper.rentReturn.findNo", mo_no);
		return ret;
	}

	@Override
	public MemberVO modifyMember(MemberVO member) {
		return sqlSession.selectOne("mapper.member.updateMember", member);
	}

}
