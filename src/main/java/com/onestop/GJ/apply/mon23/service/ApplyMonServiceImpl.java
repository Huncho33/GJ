package com.onestop.GJ.apply.mon23.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.apply.mon23.dao.ApplyMonDAO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("ApplyMonServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyMonServiceImpl implements ApplyMonService {
	@Autowired
	private ApplyMonDAO applymonDAO;

	// 첨부파일 등록
	@Override
	public int addResult(Map articleMap) throws Exception {
		int mo_no = applymonDAO.insertResult(articleMap);
		articleMap.put("mo_no", mo_no);
		applymonDAO.insertNewFile(articleMap);
		return mo_no;
	}

	// 신청 아이디 체크
	@Override
	public ApplyMonVO findAll(String id) {
		ApplyMonVO list = null;
		list = applymonDAO.findAll(id);
		return list;
	}

	// 결과페이지
	@Override
	public ApplyMonVO findNo(int no) {
		return applymonDAO.findNo(no);
	}

	@Override
	public MemberVO modifyMember(MemberVO member) {
		return applymonDAO.modifyMember(member);
	}
}
