package com.onestop.GJ.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface MemberService {

	List listMembers() throws DataAccessException;

	public void addMember(MemberVO memberVO) throws DataAccessException;

	int removeMember(String id) throws DataAccessException;

	MemberVO login(MemberVO memberVO) throws Exception;

	String overlapped(String id) throws Exception;

// pw 찾기 인증 메일 보내기
	void send_PwMail(MemberVO member) throws Exception;

// pw 찾기 인증
	ModelAndView find_pw(HttpServletResponse response, MemberVO member) throws Exception;

// ID 찾기
	MemberVO findId_hp(MemberVO memberVO) throws Exception;

	void last_log(String member_id) throws Exception;

	List<BoardNoticeVO> selectNotiList() throws Exception;

	List<BoardDataVO> selectDataList() throws Exception;

	void insertVisit(Map visitMap);

	Map getTotCnt(Map visitMap);

}
