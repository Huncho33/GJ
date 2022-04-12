package com.onestop.GJ.apply.share.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface ApplyShareDAO {

	// ��û��� �� �ֱ�.
	int insertResult(Map articleMap) throws DataAccessException;

	// ���� ���� ���ε�
	void insertNewFile(Map articleMap) throws DataAccessException;

	// ���������
	ApplyShareVO findAll(String member_id);

	ApplyShareVO findNo(int sh_no);

	MemberVO modifyMember(MemberVO member);

}