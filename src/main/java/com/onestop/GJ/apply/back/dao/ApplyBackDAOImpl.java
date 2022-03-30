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

@Repository("ApplyBackDAOImpl")
public class ApplyBackDAOImpl implements ApplyBackDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ApplyBackVO selectResult(Map resultMap) throws DataAccessException {
		ApplyBackVO applyBackVO = (ApplyBackVO)sqlSession.selectOne("mapper.back.selectResult", resultMap);
		return applyBackVO;
	}
	

	// ��û��� �� �ֱ�.
	@Override
	public int insertResult(Map articleMap) throws DataAccessException{
		int ba_no = insertResultNO();
		articleMap.put("ba_no", ba_no);
		System.out.println("�׽�Ʈ11 : "+ articleMap);
		
		sqlSession.insert("mapper.back.insertResult", articleMap);
		System.out.println("�׽�Ʈ22 : "+ articleMap);
		return ba_no;
		
	}

	
	// ��û��ȣ
	 public int insertResultNO() throws DataAccessException {
	      return sqlSession.selectOne("mapper.back.insertResultNO");
	   }
	 
	
	// ���� ���� ���ε�
	   @Override
	   public void insertNewFile(Map articleMap) throws DataAccessException {
	      List<ApplyBackFileVO> backApplyFileList = (ArrayList) articleMap.get("imageFileList");
	      System.out.println("why? : "+ backApplyFileList);
	      int ba_no = (Integer) articleMap.get("ba_no");
	      
	      int MO_FILENO = selectNewImageFileNO();
	      
	       if (backApplyFileList != null && backApplyFileList.size() != 0) {
	      for (ApplyBackFileVO ApplyBackFileVO : backApplyFileList) {
	    	  ApplyBackFileVO.setUp_fileno(++MO_FILENO);
	    	  ApplyBackFileVO.setBa_no(ba_no);
	      }
	      System.out.println("imageFileList"+ backApplyFileList);
	      sqlSession.insert("mapper.back.insertNewImage", backApplyFileList);
	   }
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.back.selectMoFileNO");
	}
	
	
	
	//���������
	@Override
	public List findAll(String member_id) {
		List<ApplyBackFileVO> list = null;
		list = sqlSession.selectList("mapper.back.all", member_id);
		return list; 
	}
   
	@Override
	public ApplyBackVO findNo(int ba_no) {
		ApplyBackVO month = sqlSession.selectOne("mapper.back.findNo", ba_no);
		  return month;
	}




	
	

}
