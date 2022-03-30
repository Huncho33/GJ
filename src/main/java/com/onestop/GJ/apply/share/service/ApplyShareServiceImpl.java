package com.onestop.GJ.apply.share.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.apply.share.dao.ApplyShareDAO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;

@Service("ApplyShareServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyShareServiceImpl implements ApplyShareService{
	@Autowired
	private ApplyShareDAO applyShareDAO;

	@Override
	public ApplyShareVO selectResult(Map resultMap) throws Exception {
	  return applyShareDAO.selectResult(resultMap);
	}
	
	
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int sh_no = applyShareDAO.insertResult(articleMap);
			articleMap.put("sh_no", sh_no);
			applyShareDAO.insertNewFile(articleMap);
			return sh_no;
		}
	
	@Override
	public List findAll(String id) {
		List list = null;
		list = applyShareDAO.findAll(id);
		return list;
	}
	
	@Override
	public ApplyShareVO findNo(int no) {
		return  applyShareDAO.findNo(no);
	}


	
}
