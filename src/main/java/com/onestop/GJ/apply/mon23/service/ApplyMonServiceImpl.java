package com.onestop.GJ.apply.mon23.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.mon23.dao.ApplyMonDAO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

@Service("ApplyMonServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyMonServiceImpl implements ApplyMonService{
	@Autowired
	private ApplyMonDAO applymonDAO;
	
	
	@Override
	public ApplyMonVO selectResult(Map resultMap) throws Exception {
	  return applymonDAO.selectResult(resultMap);
	}
	
	
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int mo_no = applymonDAO.insertResult(articleMap);
			articleMap.put("mo_no", mo_no);
			applymonDAO.insertNewFile(articleMap);
			return mo_no;
		}
	
	@Override
	public ApplyMonVO findAll(String id) {
		ApplyMonVO applyMonVO = null;
		applyMonVO = applymonDAO.findAll(id);
		return applyMonVO;
	}
	
	@Override
	public ApplyMonVO findNo(int no) {
		return  applymonDAO.findNo(no);
	}


	
}

