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
		System.out.println("테스트11 : " + articleMap + "ret_no"+ ret_no);

		sqlSession.insert("mapper.rentReturn.insertResult", articleMap);
		System.out.println("테스트22 : " + articleMap);
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
		System.out.println("why? : " + rentReturnApplyFileList);
		int return_no = (Integer) articleMap.get("return_no");

		int RETURN_FILENO = selectNewImageFileNO();

		if (rentReturnApplyFileList != null && rentReturnApplyFileList.size() != 0) {
			for (ApplyRentReturnFileVO applyrentReturnFileVO : rentReturnApplyFileList) {
				applyrentReturnFileVO.setUp_fileno(++RETURN_FILENO);
				applyrentReturnFileVO.setRet_no(return_no);
			}
			System.out.println("imageFileList" + rentReturnApplyFileList);
			sqlSession.insert("mapper.rentReturn.insertNewImage", rentReturnApplyFileList);
		}
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.rentReturn.selectMoFileNO");
	}

	// 결과페이지
	@Override
	public List findAll(String member_id) {
		List<ApplyMonVO> list = null;
		list = sqlSession.selectList("mapper.rentReturn.all", member_id);
		return list;
	}

	@Override
	public ApplyRentReturnVO findNo(int mo_no) {
		ApplyRentReturnVO ret = sqlSession.selectOne("mapper.rentReturn.findNo", mo_no);
		return ret;
	}

}
