package com.onestop.GJ.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.member.vo.MemberVO;

public interface MemberController {

	ModelAndView login(MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	ModelAndView logout(MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}