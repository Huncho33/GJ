package com.onestop.GJ.apply.mon23.service;

import java.util.Map;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyMonService {

	ApplyMonVO selectResult(Map resultMap) throws Exception;


	int addResult(Map mo_no) throws Exception;

	ApplyMonVO findAll(String id);


	ApplyMonVO findNo(int no);


	MemberVO modifyMember(MemberVO member);


	



	

	
}
