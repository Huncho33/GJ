package com.onestop.GJ.apply.rent.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.rent.vo.ApplyRentVO;

public interface ApplyRentService {

	ApplyRentVO selectResult(Map resultMap) throws Exception;

	int addResult(Map articleMap) throws Exception;

	List findAll(String id);

	ApplyRentVO findNo(int no);


	
}
