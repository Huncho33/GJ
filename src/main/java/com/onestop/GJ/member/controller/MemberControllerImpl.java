package com.onestop.GJ.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.common.base.BaseController;
import com.onestop.GJ.member.controller.MemberController;
import com.onestop.GJ.member.service.MemberService;
import com.onestop.GJ.member.vo.MemberVO;

//@Controller("memberController")
//@RequestMapping(value="/member")
//public class MemberControllerImpl extends BaseController implements MemberController{
//	@Autowired
//	private MemberService memberService;
//	@Autowired
//	private MemberVO memberVO;
@Controller("memberController")

public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	@RequestMapping(value = { "/", "/main.do" }, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	@Override //로그인 창
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/member/listMembers.do");
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
			
		}
		return mav;
	}

	@Override //로그아웃 창
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("member")MemberVO member, RedirectAttributes rAttr,
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOut");
		ModelAndView mav = new ModelAndView();
		
		rAttr.addAttribute("result", "logOut");
		mav.setViewName("redirect:/member/logoutForm.do");
//		mav.setViewName("redirect:/member/listMembers.do");
		return mav;

	}
	
	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
					       HttpServletRequest request, 
					       HttpServletResponse response) throws Exception {
		//String viewName = getViewName(request);
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
}
