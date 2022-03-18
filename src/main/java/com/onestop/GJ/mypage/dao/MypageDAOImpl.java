package com.onestop.GJ.mypage.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.member.vo.MemberVO;

@Repository
public class MypageDAOImpl implements MypageDAO {
	@Autowired
	private SqlSession sqlSession;

	// 비밀번호 재확인
	@Override
	public MemberVO pwdConfirm(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.mypage.pwdConfirm", memberVO);
		return vo;
	}

//	// 아이디로 조회하여 회원 선택
	@Override
	public MemberVO selectMemberById(String id) throws DataAccessException {
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.mypage.selectMemberById", id);
		return memberVO;
	}

//	// 선택된 아이디 회원 정보 수정 (int)
//	@Override
//	public int updateMember(MemberVO memberVO) throws DataAccessException {
//		int result = sqlSession.update("mapper.mypage.updateMember", memberVO);
//		return result;
//	}

//	// 아이디로 조회하여 회원 선택
//	@Override
//	public MemberVO selectMemberById(String member_id) throws DataAccessException {
//		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.mypage.selectMemberById", member_id);
//		return memberVO;
//	}

//	// 선택된 아이디 회원 정보 수정 (void)
//	@Override
//	public void updateMember(MemberVO memberVO) throws DataAccessException {
//		sqlSession.update("mapper.mypage.updateMember", memberVO);
//	}
	
	// 회원 정보 수정
	public void updateMember(Map memberMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMember", memberMap);
	}
	
	// 회원 탈퇴
	@Override
	public int deleteMember(String member_id) throws DataAccessException {
		int result = sqlSession.delete("mapper.mypage.deleteMember", member_id);
		return result;
	}

	// 비밀번호 재확인을 위한 조회 기능
	@Override
	public boolean checkPwd(String member_id, String member_pw) throws DataAccessException {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("member_pw", member_pw);
		int count = sqlSession.selectOne("mapper.mypage.checkPwd", map);
		if (count == 1) {
			result = true;
		}
		return result;
	}

}
