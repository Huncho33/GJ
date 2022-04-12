package com.onestop.GJ.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;
import com.onestop.GJ.member.vo.MemberVO;

public interface MemberService {

	void addMember(MemberVO memberVO) throws DataAccessException;

	MemberVO login(MemberVO memberVO) throws Exception;

	void last_log(String member_id) throws Exception;

	String overlapped(String id) throws Exception;

	void send_PwMail(MemberVO memberVO) throws Exception;

	MemberVO findId_hp(MemberVO memberVO) throws Exception;

	List<BoardNoticeVO> selectNotiList() throws Exception;

	List<BoardDataVO> selectDataList() throws Exception;

	void insertVisit(Map visitMap);

	Map getTotCnt(Map visitMap);

	void find_pw(HttpServletResponse response,  MemberVO memberVO) throws Exception;



}
