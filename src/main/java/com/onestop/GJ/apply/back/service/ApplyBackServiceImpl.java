package com.onestop.GJ.apply.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.back.dao.ApplyBackDAO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;

@Service("ApplyBackServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyBackServiceImpl implements ApplyBackService{
	@Autowired
	private ApplyBackDAO applybackDAO;
	
	
	@Override
	public ApplyBackVO selectResult(Map resultMap) throws Exception {
	  return applybackDAO.selectResult(resultMap);
	}
	
	
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int ba_no = applybackDAO.insertResult(articleMap);
			articleMap.put("ba_no", ba_no);
			applybackDAO.insertNewFile(articleMap);
			return ba_no;
		}
	
	@Override
	public List findAll(String id) {
		List list = null;
		list = applybackDAO.findAll(id);
		return list;
	}
	
	@Override
	public ApplyBackVO findNo(int no) {
		return  applybackDAO.findNo(no);
	}


	
}
