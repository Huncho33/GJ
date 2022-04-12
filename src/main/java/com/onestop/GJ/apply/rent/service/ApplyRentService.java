package com.onestop.GJ.apply.rent.service;

import java.util.Map;

import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyRentService {

	int addResult(Map articleMap) throws Exception;

	ApplyRentVO findAll(String id);

	ApplyRentVO findNo(int no);

	MemberVO modifyMember(MemberVO member);


	
}
