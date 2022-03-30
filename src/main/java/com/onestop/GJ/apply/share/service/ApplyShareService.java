package com.onestop.GJ.apply.share.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.share.vo.ApplyShareVO;

public interface ApplyShareService {

	ApplyShareVO selectResult(Map resultMap) throws Exception;

	int addResult(Map articleMap) throws Exception;

	List findAll(String id);

	ApplyShareVO findNo(int no);

}