package com.onestop.GJ.apply.back.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.back.vo.ApplyBackVO;

public interface ApplyBackService {

	int addResult(Map articleMap) throws Exception;

	ApplyBackVO findAll(String id);

	ApplyBackVO findNo(int no);

}
