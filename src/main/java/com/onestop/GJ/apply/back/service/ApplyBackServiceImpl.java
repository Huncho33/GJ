package com.onestop.GJ.apply.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.back.dao.ApplyBackDAO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("ApplyBackServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyBackServiceImpl implements ApplyBackService{
	@Autowired
	private ApplyBackDAO applybackDAO;
	
//	첨부파일 등록
	 @Override
		public int addResult(Map articleMap) throws Exception {
			int ba_no = applybackDAO.insertResult(articleMap);
			articleMap.put("ba_no", ba_no);
			applybackDAO.insertNewFile(articleMap);
			return ba_no;
		}
	
	// 신청 아이디  체크
	@Override
	public ApplyBackVO findAll(String id) {
		ApplyBackVO list = null;
		list = applybackDAO.findAll(id);
		return list;
	}
	
	// 결과페이지
	@Override
	public ApplyBackVO findNo(int no) {
		return  applybackDAO.findNo(no);
	}


	@Override
	public MemberVO modifyMember(MemberVO member) {
		
		return applybackDAO.modifyMember(member);
	}


	
}
