package com.onestop.GJ.apply.back.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.back.vo.ApplyBackVO;

public interface ApplyBackService {

	ApplyBackVO selectResult(Map resultMap) throws Exception;

	int addResult(Map articleMap) throws Exception;

	List findAll(String id);

	ApplyBackVO findNo(int no);

}
