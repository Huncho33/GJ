package com.onestop.GJ.apply.rent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.rent.dao.ApplyRentDAO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;

@Service("ApplyRentServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyRentServiceImpl implements ApplyRentService{

	@Autowired
	private ApplyRentDAO applyrentDAO;
	
	
	@Override
	public ApplyRentVO selectResult(Map resultMap) throws Exception {
	  return applyrentDAO.selectResult(resultMap);
	}
	
	
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int rent_no = applyrentDAO.insertResult(articleMap);
			articleMap.put("rent_no", rent_no);
			applyrentDAO.insertNewFile(articleMap);
			return rent_no;
		}
	
	@Override
	public List findAll(String id) {
		List list = null;
		list = applyrentDAO.findAll(id);
		return list;
	}
	
	@Override
	public ApplyRentVO findNo(int no) {
		return  applyrentDAO.findNo(no);
	}
	
}
