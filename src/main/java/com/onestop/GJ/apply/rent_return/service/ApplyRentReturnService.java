package com.onestop.GJ.apply.rent_return.service;

import java.util.Map;

import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyRentReturnService {

	ApplyRentReturnVO selectResult(Map resultMap) throws Exception;

	int addResult(Map articleMap) throws Exception;

	ApplyRentReturnVO findAll(String id);

	ApplyRentReturnVO findNo(int no);

	MemberVO modifyMember(MemberVO member);


}