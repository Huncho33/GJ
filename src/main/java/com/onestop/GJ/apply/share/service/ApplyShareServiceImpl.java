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
import com.onestop.GJ.member.vo.MemberVO;

@Service("ApplyShareServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyShareServiceImpl implements ApplyShareService {
	@Autowired
	private ApplyShareDAO applyShareDAO;

//	첨부파일 등록
	@Override
	public int addResult(Map articleMap) throws Exception {
		int sh_no = applyShareDAO.insertResult(articleMap);
		articleMap.put("sh_no", sh_no);
		applyShareDAO.insertNewFile(articleMap);
		return sh_no;
	}

	// 신청 아이디 체크
	@Override
	public ApplyShareVO findAll(String id) {
		ApplyShareVO list = null;
		list = applyShareDAO.findAll(id);
		return list;
	}

	// 결과페이지
	@Override
	public ApplyShareVO findNo(int no) {
		return applyShareDAO.findNo(no);
	}

	@Override
	public MemberVO modifyMember(MemberVO member) {
		return applyShareDAO.modifyMember(member);
	}
}
