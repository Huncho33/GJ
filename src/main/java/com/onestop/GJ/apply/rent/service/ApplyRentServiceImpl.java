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
	
//	÷������ ���
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int rent_no = applyrentDAO.insertResult(articleMap);
			articleMap.put("rent_no", rent_no);
			applyrentDAO.insertNewFile(articleMap);
			return rent_no;
		}
	
		// ��û ���̵�  üũ
	@Override
	public ApplyRentVO findAll(String id) {
		ApplyRentVO list = null;
		list = applyrentDAO.findAll(id);
		return list;
	}
	
	// ���������
	@Override
	public ApplyRentVO findNo(int no) {
		return  applyrentDAO.findNo(no);
	}
	
}
