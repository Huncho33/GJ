package com.onestop.GJ.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.board.QNA.vo.QnaVO;
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

	// 상담글-월세지원 카테고리만 조회
	@Override
	public List selectMonthQnasList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = sqlSession.selectList("mapper.mypage.selectMonthQnasList", member_id);
		return monthQnaList;
	}

	// 상담글-전월세보증금 카테고리만 조회
	@Override
	public List selectRentQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectRentQnasList", member_id);
		return QnasList;
	}

	// 상담글-전세반환보증금 카테고리만 조회
	@Override
	public List selectReturnQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectReturnQnasList", member_id);
		return QnasList;
	}

	// 상담글-신혼부부전세자금 카테고리만 조회
	@Override
	public List selectWeddingQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectWeddingQnasList", member_id);
		return QnasList;
	}

	// 상담글-청년희망주택 카테고리만 조회
	@Override
	public List selectShareQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectShareQnasList", member_id);
		return QnasList;
	}

}
