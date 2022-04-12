package com.onestop.GJ.apply.rent_return.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.rent_return.dao.ApplyRentReturnDAO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("ApplyRentReturnServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyRentReturnServiceImpl implements ApplyRentReturnService{
	@Autowired
	private ApplyRentReturnDAO applyrentReturnDAO;
	
	
	@Override
	public ApplyRentReturnVO selectResult(Map resultMap) throws Exception {
	  return applyrentReturnDAO.selectResult(resultMap);
	}
	
	
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int return_no = applyrentReturnDAO.insertResult(articleMap);
			articleMap.put("return_no", return_no);
			applyrentReturnDAO.insertNewFile(articleMap);
			return return_no;
		}
	
	@Override
	public ApplyRentReturnVO findAll(String id) {
		ApplyRentReturnVO list = null;
		list = applyrentReturnDAO.findAll(id);
		return list;
	}
	
	@Override
	public ApplyRentReturnVO findNo(int no) {
		return applyrentReturnDAO.findNo(no);
	}


	@Override
	public MemberVO modifyMember(MemberVO member) {
		return applyrentReturnDAO.modifyMember(member);
	}


	
}

