package com.onestop.GJ.apply.mon23.service;

import java.util.List;
import java.util.Map;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

public interface ApplyMonService {

	int addResult(Map mo_no) throws Exception;

	ApplyMonVO findAll(String id);

	ApplyMonVO findNo(int no);

}
