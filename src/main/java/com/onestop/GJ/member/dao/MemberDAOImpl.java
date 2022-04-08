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

	@Override
	public MemberVO login(Map loginMap) throws DataAccessException {
		MemberVO member = (MemberVO) sqlSession.selectOne("mapper.member.login", loginMap);
		return member;
	}

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public void insertMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertMember", memberVO);
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}

	@Override
	public void last_logOn(String member_id) throws DataAccessException {
		sqlSession.update("mapper.member.last_logOn", member_id);
	}
	
	@Override
	public MemberVO SearchById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.SearchById", memberVO);
		return vo;
	}

	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.member.selectOverlappedID", id);
		return result;
	}

//	--아이디찾기
	@Override
	public MemberVO certHp_Id(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.certHp_Id", memberVO);
		return vo;
	}

	// 비밀번호 변경
	@Override
	public int update_pw(MemberVO member) throws Exception {
		return sqlSession.update("mapper.member.update_pw", member);
	}

	// 공지사항 최근 5개 글 호출
	@Override
	public List selectNotiList() throws Exception {
		List<BoardNoticeVO> notiList = sqlSession.selectList("mapper.member.selectNotiList");
		return notiList;
	}

	// 자료실 최근 5개 글 호출
	@Override
	public List selectDataList() throws Exception {
		List<BoardDataVO> dataList = sqlSession.selectList("mapper.member.selectDataList");
		return dataList;
	}
	
	//방문자 정보 저장
	@Override
	public void insertVisit(Map visitMap) {
		System.out.println("확인 : "+visitMap);
		sqlSession.insert("mapper.member.insertVisit", visitMap);
		
	}
	
	//방문자 수
	@Override
	public int getVisitTotCnt(Map visitMap) {
		int getVisitTotCnt = sqlSession.selectOne("mapper.member.selectVisitList");
		return getVisitTotCnt;
		
	}

}
