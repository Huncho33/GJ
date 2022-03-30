package com.onestop.GJ.apply.rent_return.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;

public interface ApplyRentReturnService {

	ApplyRentReturnVO selectResult(Map resultMap) throws Exception;

	int addResult(Map articleMap) throws Exception;

	List findAll(String id);

	ApplyRentReturnVO findNo(int no);


}