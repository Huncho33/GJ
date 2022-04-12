package com.onestop.GJ.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;
//import com.onestop.GJ.member.dao.MemberDAO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

//	ȸ���߰�
	@Override
	public void insertMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertMember", memberVO);
	}

//	�α���
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}
	
//	�������ӽð�
	@Override
	public void last_logOn(String member_id) throws DataAccessException {
		sqlSession.update("mapper.member.last_logOn", member_id);
	}
	
	@Override
	public MemberVO SearchById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.SearchById", memberVO);
		return vo;
	}
	
//	���̵� �ߺ�üũ
	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.member.selectOverlappedID", id);
		return result;
	}

//	���̵�ã��
	@Override
	public MemberVO certHp_Id(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.certHp_Id", memberVO);
		return vo;
	}

	// ��й�ȣ ����
	@Override
	public int update_pw(MemberVO member) throws Exception {
		return sqlSession.update("mapper.member.update_pw", member);
	}

	// �������� �ֱ� 5�� �� ȣ��
	@Override
	public List selectNotiList() throws Exception {
		List<BoardNoticeVO> notiList = sqlSession.selectList("mapper.member.selectNotiList");
		return notiList;
	}

	// �ڷ�� �ֱ� 5�� �� ȣ��
	@Override
	public List selectDataList() throws Exception {
		List<BoardDataVO> dataList = sqlSession.selectList("mapper.member.selectDataList");
		return dataList;
	}
	
	//�湮�� ���� ����
	@Override
	public void insertVisit(Map visitMap) {
		sqlSession.insert("mapper.member.insertVisit", visitMap);
		
	}
	
	//�湮�� ��
	@Override
	public int getVisitTotCnt(Map visitMap) {
		int getVisitTotCnt = sqlSession.selectOne("mapper.member.selectVisitList");
		return getVisitTotCnt;
		
	}

}
