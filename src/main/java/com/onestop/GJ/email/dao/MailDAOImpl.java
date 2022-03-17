package com.onestop.GJ.email.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import com.onestop.GJ.member.dao.MemberDAO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("mailDAO")
public class MailDAOImpl implements MailDAO {
	@Autowired
	private SqlSession sqlSession;

	// 인증관련
	@Override
	public MemberVO certHp_Pw(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.certHp_Pw", memberVO);
		return vo;
	}
	
}
