package com.onestop.GJ.apply.share.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;

@Repository("ApplyShareDAOImpl")
public class ApplyShareDAOImpl implements ApplyShareDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyShareVO selectResult(Map resultMap) throws DataAccessException {
		ApplyShareVO applyShareVO = (ApplyShareVO) sqlSession.selectOne("mapper.share.selectResult", resultMap);
		return applyShareVO;
	}

	// 신청결과 값 넣기.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException {
		int sh_no = insertResultNO();
		articleMap.put("sh_no", sh_no);
		System.out.println("테스트11 : " + articleMap);

		sqlSession.insert("mapper.share.insertResult", articleMap);
		System.out.println("테스트22 : " + articleMap);
		return sh_no;

	}

	// 신청번호
	public int insertResultNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.share.insertResultNO");
	}

	// 다중 파일 업로드
	@Override
	public void insertNewFile(Map articleMap) throws DataAccessException {
		List<ApplyShareFileVO> shareApplyFileList = (ArrayList) articleMap.get("imageFileList");
		System.out.println("why? : " + shareApplyFileList);
		int sh_no = (Integer) articleMap.get("sh_no");

		int MO_FILENO = selectNewImageFileNO();

		if (shareApplyFileList != null && shareApplyFileList.size() != 0) {
			for (ApplyShareFileVO applyShareFileVO : shareApplyFileList) {
				applyShareFileVO.setUp_fileno(++MO_FILENO);
				applyShareFileVO.setSh_no(sh_no);
			}
			System.out.println("imageFileList" + shareApplyFileList);
			sqlSession.insert("mapper.share.insertNewImage", shareApplyFileList);
		}
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.share.selectShFileNO");
	}

	// 결과페이지
	@Override
	public List findAll(String member_id) {
		List<ApplyShareVO> list = null;
		list = sqlSession.selectList("mapper.share.all", member_id);
		return list;
	}

	@Override
	public ApplyShareVO findNo(int sh_no) {
		ApplyShareVO month = sqlSession.selectOne("mapper.share.findNo", sh_no);
		return month;
	}

}
