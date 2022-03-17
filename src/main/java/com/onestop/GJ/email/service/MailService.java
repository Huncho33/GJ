package com.onestop.GJ.email.service;

import com.onestop.GJ.member.vo.MemberVO;

public interface MailService {

	void sendMail(String to, String subject, String body);

	void sendPreConfiguredMail(String message);

	MemberVO findPw_hp(MemberVO memberVO) throws Exception;

	String sendSimpleMessage(String email) throws Exception;

	







}