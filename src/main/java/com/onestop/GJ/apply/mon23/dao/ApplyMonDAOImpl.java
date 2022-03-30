package com.onestop.GJ.apply.mon23.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

@Repository("ApplyMonDAOImpl")
public class ApplyMonDAOImpl implements ApplyMonDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyMonVO selectResult(Map resultMap) throws DataAccessException {
		ApplyMonVO applymonVO = (ApplyMonVO)sqlSession.selectOne("mapper.apply.selectResult", resultMap);
		return applymonVO;
	}
	

	// 신청결과 값 넣기.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException{
		int mo_no = insertResultNO();
		articleMap.put("mo_no", mo_no);
		System.out.println("테스트11 : "+ articleMap);
		
		sqlSession.insert("mapper.apply.insertResult", articleMap);
		System.out.println("테스트22 : "+ articleMap);
		return mo_no;
		
	}

	
	// 신청번호
	 public int insertResultNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.apply.insertResultNO");
	   }
	 
	
	// 다중 파일 업로드
	   @Override
	   public void insertNewFile(Map articleMap) throws DataAccessException {
	      List<ApplyMonFileVO> moApplyFileList = (ArrayList) articleMap.get("imageFileList");
	      System.out.println("why? : "+ moApplyFileList);
	      int mo_no = (Integer) articleMap.get("mo_no");
	      
	      int MO_FILENO = selectNewImageFileNO();
	      
	       if (moApplyFileList != null && moApplyFileList.size() != 0) {
	      for (ApplyMonFileVO applyMonFileVO : moApplyFileList) {
	    	  applyMonFileVO.setUp_fileno(++MO_FILENO);
	    	  applyMonFileVO.setMo_no(mo_no);
	      }
	      System.out.println("imageFileList"+ moApplyFileList);
	      sqlSession.insert("mapper.apply.insertNewImage", moApplyFileList);
	   }
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.apply.selectMoFileNO");
	}
	
	
	
	//결과페이지
	@Override
	public List findAll(String member_id) {
		List<ApplyMonVO> list = null;
		list = sqlSession.selectList("mapper.apply.all", member_id);
		return list; 
	}
   
	@Override
	public ApplyMonVO findNo(int mo_no) {
		  ApplyMonVO month = sqlSession.selectOne("mapper.apply.findNo", mo_no);
		  return month;
	}




	
	

}
