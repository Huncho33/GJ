package com.onestop.GJ.member.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
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

import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;
import com.onestop.GJ.member.service.MemberService;
import com.onestop.GJ.member.vo.MemberVO;

@Controller("memberController")

public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	

	@RequestMapping(value = { "/*/*.do", "/main.do" }, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		
		List<BoardNoticeVO> notiList = memberService.selectNotiList();
		List<BoardDataVO> dataList = memberService.selectDataList();
		
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.addObject("notiList", notiList);
		mav.addObject("dataList", dataList);
		
		return mav;
	}

	// 로그인
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
	         
	         String member_id = memberVO.getMember_id();
	         String member_gender = memberVO.getMember_gender();
	         String member_roadAddress = memberVO.getMember_roadAddress();
	         String member_birth = memberVO.getMember_birth();
	         String member_right = memberVO.getMember_right();
	         memberService.last_log(member_id);
	         Date member_last_log = memberVO.getMember_last_log();
	        
	         Map visitMap = new HashMap();
	         if(member_right.equals("MEMBER")) {
	            visitMap.put("member_id", member_id);
	            visitMap.put("member_gender", member_gender);
	            visitMap.put("member_roadAddress", member_roadAddress);
	            visitMap.put("member_birth", member_birth);
	            visitMap.put("member_last_log", member_last_log);
	            
	         
	         memberService.insertVisit(visitMap);
	         Map countMap = memberService.getTotCnt(visitMap); 
	         }
	         
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

	@Override // 로그아웃
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOut");
		ModelAndView mav = new ModelAndView();

		//rAttr.addAttribute("result", "logOut");
		session.invalidate();
		mav.setViewName("redirect:/main.do");
		return mav;

	}
	
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
		

	// 회원가입
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
			message += " alert('회원 가입이 완료되었습니다. 로그인창으로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}


	// 회원가입 ID 중복체크
	@Override
	@RequestMapping(value = "/member/overlapped.do", method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}



	// 비밀번호 찾기(이메일)
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
			out.print("올바르지 않은 이메일 형식입니다.");
			out.close();
		}
		
	}

	// 아이디찾기
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
			message += " alert('" +"아이디 : "+  memberVO.getMember_id()  + "');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";
			System.out.println("ok~"+ memberVO.getMember_id());
			
		}else {
			System.out.println("not okay");
			message = "<script>";
			message += " alert('" +" 입력한 정보가 올바르지 않습니다. "  + "');";
			message += " location.href='" + request.getContextPath() + "/member/findId_cellph.do';";
			message += " </script>";
		}
		resEntity1 = new ResponseEntity(message, responseHeaders1, HttpStatus.OK);
		return resEntity1;
	}

}
