package com.onestop.GJ.apply.share.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.share.vo.ApplyShareVO;

public interface ApplyShareDAO {

	ApplyShareVO selectResult(Map resultMap) throws DataAccessException;

	// 신청결과 값 넣기.
	int insertResult(Map articleMap) throws DataAccessException;

	// 다중 파일 업로드
	void insertNewFile(Map articleMap) throws DataAccessException;

	// 결과페이지
	ApplyShareVO findAll(String member_id);

	ApplyShareVO findNo(int sh_no);

}