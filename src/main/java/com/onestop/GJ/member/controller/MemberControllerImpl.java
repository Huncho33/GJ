package com.onestop.GJ.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import javafx.scene.control.Alert;

@Controller("memberController")

public class MemberControllerImpl implements MemberController  {
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

	// �α���
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

	@Override // �α׾ƿ� â
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOut");
		ModelAndView mav = new ModelAndView();

		rAttr.addAttribute("result", "logOut");
		mav.setViewName("redirect:/main.do");
		return mav;

	}


	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
	
	//ȸ������
	@RequestMapping(value = "/member/memberForm2.do", method = RequestMethod.GET)
	private ModelAndView memberForm2(@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/member/memberForm3.do", method = RequestMethod.GET)
	private ModelAndView memberForm3(@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}

	// ȸ������
	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			memberService.addMember(_memberVO);
			message = "<script>";
			message += " alert('ȸ�� ������ ���ƽ��ϴ�.�α���â���� �̵��մϴ�.');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}


	// ȸ������ ID �ߺ�üũ
	@Override
	@RequestMapping(value = "/member/overlapped.do", method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}

	// ��й�ȣ ã�� �� ��� �޼���
	@RequestMapping(value = "/member/find_pw_form.do", method = RequestMethod.GET)
	private ModelAndView find_pw_form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// ��й�ȣ ã�� ��� �޼���
	@RequestMapping(value = "/member/find_pw.do", method = RequestMethod.POST)
	public void find_pw(@RequestParam("id") String find_id, @RequestParam("email") String find_email, MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (find_email.indexOf("@") != -1) {
			int index = find_email.indexOf("@");
			memberVO.setMember_id(find_id);
			memberVO.setMember_email1(find_email.substring(0, index));
			memberVO.setMember_email2(find_email.substring(index + 1));
			memberService.find_pw(response, memberVO);
		} else {
			out.print("�ùٸ��� ���� �̸��� �����Դϴ�.");
			out.close();
		}

	}

	//	--���̵�ã��
	@Override
	@RequestMapping(value = "/member/findYourId.do", method = RequestMethod.POST)
	public ResponseEntity sendPhone(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.findId_hp(member);
		
		String message = null;
		ResponseEntity resEntity1 = null;
		HttpHeaders responseHeaders1 = new HttpHeaders();
		responseHeaders1.add("Content-Type", "text/html; charset=utf-8");
		
		
		if (memberVO != null) {
			
			message = "<script>";
			message += " alert('" +"���̵� : "+  memberVO.getMember_id()  + "');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";
			

			System.out.println("ok~"+ memberVO.getMember_id());
			
		}else {
			System.out.println("not okay");
			
			message = "<script>";
			message += " alert('" +" �Է��� ������ �ùٸ��� �ʽ��ϴ�. "  + "');";
			message += " location.href='" + request.getContextPath() + "/member/findId_cellph.do';";
			message += " </script>";
	
	
		}
			
		resEntity1 = new ResponseEntity(message, responseHeaders1, HttpStatus.OK);
		return resEntity1;
		
	}

}
