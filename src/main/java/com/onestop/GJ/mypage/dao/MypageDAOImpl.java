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

	// ��й�ȣ ��Ȯ��
	@Override
	public MemberVO pwdConfirm(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.mypage.pwdConfirm", memberVO);
		return vo;
	}

//	// ���̵�� ��ȸ�Ͽ� ȸ�� ����
	@Override
	public MemberVO selectMemberById(String id) throws DataAccessException {
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.mypage.selectMemberById", id);
		return memberVO;
	}

	// ȸ�� ���� ����
	public void updateMember(Map memberMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMember", memberMap);
	}

	// ȸ�� Ż��
	@Override
	public int deleteMember(String member_id) throws DataAccessException {
		int result = sqlSession.delete("mapper.mypage.deleteMember", member_id);
		return result;
	}

	// ��й�ȣ ��Ȯ���� ���� ��ȸ ���
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

	// ����-�������� ī�װ��� ��ȸ
	@Override
	public List selectMonthQnasList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = sqlSession.selectList("mapper.mypage.selectMonthQnasList", member_id);
		return monthQnaList;
	}

	// ����-������������ ī�װ��� ��ȸ
	@Override
	public List selectRentQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectRentQnasList", member_id);
		return QnasList;
	}

	// ����-������ȯ������ ī�װ��� ��ȸ
	@Override
	public List selectReturnQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectReturnQnasList", member_id);
		return QnasList;
	}

	// ����-��ȥ�κ������ڱ� ī�װ��� ��ȸ
	@Override
	public List selectWeddingQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectWeddingQnasList", member_id);
		return QnasList;
	}

	// ����-û��������� ī�װ��� ��ȸ
	@Override
	public List selectShareQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectShareQnasList", member_id);
		return QnasList;
	}

}
