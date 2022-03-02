package com.onestop.GJ.member.controller;

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

import com.onestop.GJ.member.service.MemberService;
import com.onestop.GJ.member.vo.MemberVO;


@Controller("memberController")

public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	@RequestMapping(value = { "/*/*.do", "/main.do" }, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
//	@Override //�α��� â
//	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
//	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		memberVO = memberService.login(member);
//		if (memberVO != null && memberVO.getMember_id() != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("member", memberVO);
//			session.setAttribute("isLogOn", true);
//			mav.setViewName("redirect:/main.do");
//		} else {
//			rAttr.addAttribute("result", "loginFailed");
//			mav.setViewName("redirect:/member/loginForm.do");
//			
//		}
//
//		System.out.println(memberVO);
//		return mav;
//	}

	//�α���
	@Override
	   @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	   public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
	         HttpServletRequest request, HttpServletResponse response) throws Exception {
	      ModelAndView mav = new ModelAndView();
	      memberVO = memberService.login(member);
	      if (memberVO != null) {
	         HttpSession session = request.getSession();
	         session.setAttribute("member", memberVO);
	         session.setAttribute("isLogOn", true);

	         String action = (String) session.getAttribute("action");
	         session.removeAttribute("action");
	         if (action != null) {
	            mav.setViewName("redirect:" + action);
	         } else {
	            mav.setViewName("redirect:/main.do");
	         }

	      } else {
	         rAttr.addAttribute("result", "loginFailed");
	         mav.setViewName("redirect:/member/loginForm.do");
	      }
	      return mav;
	   }
	
	@Override //�α׾ƿ� â
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("member")MemberVO member, RedirectAttributes rAttr,
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOut");
		ModelAndView mav = new ModelAndView();
		
		rAttr.addAttribute("result", "logOut");
		mav.setViewName("redirect:/main.do");
		return mav;

	}
	
//	// ȸ������â
//			@Override 
//			@RequestMapping(value = "/member/memberForm.do", method = RequestMethod.GET)
//			public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//				String viewName = getViewName(request);
//				ModelAndView mav = new ModelAndView(viewName);
//				mav.setViewName(viewName);
//				return mav;
//			}
//	
//	private String getViewName(HttpServletRequest request) {
//		
//		return null;
//	}
	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
			@RequestParam(value= "action", required=false) String action,
					       HttpServletRequest request, 
					       HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	
	//ȸ������
	@Override
	@RequestMapping(value="/member/addMember.do" ,method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,
			                HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
		    memberService.addMember(_memberVO);
		    message  = "<script>";
		    message +=" alert('ȸ�� ������ ���ƽ��ϴ�.�α���â���� �̵��մϴ�.');";
		    message += " location.href='"+request.getContextPath()+"/member/loginForm.do';";
		    message += " </script>";
		    
		}catch(Exception e) {
			message  = "<script>";
		    message +=" alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
		    message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
		    message += " </script>";
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
//			//ȸ�������� �߰�
//			@Override
//			@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
//			public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
//					HttpServletResponse response) throws Exception {
//				request.setCharacterEncoding("utf-8");
//				int result = 0;
//				result = memberService.addMember(member);
//				ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
//				return mav;
//			}
	
	//ȸ������ ID �ߺ�üũ
	@Override
	@RequestMapping(value="/member/overlapped.do" ,method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
}
