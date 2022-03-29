package com.onestop.GJ.mypage.controller;

import java.io.PrintWriter;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.board.QNA.service.QnaService;
import com.onestop.GJ.board.free.service.BoardFreeService;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller
public class MypageControllerImpl implements MypageController {
	@Autowired
	private MypageService mypageService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private QnaService qnaService;
	@Autowired
	private BoardFreeService boardFreeService;

	// 비밀번호 재확인 페이지
	@Override
	@RequestMapping(value = "/mypage/confirmPwdView.do", method = RequestMethod.GET)
	public ModelAndView confirmPwdView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// 회원정보 관리
	// 비번체크 후 내정보 출력 메서드로 이동
	@Override
	@RequestMapping(value = "/mypage/myInfoView.do", method = RequestMethod.POST)
	public String myInfo(@RequestParam(value = "pwdCfm_id2") String member_id,
			@RequestParam(value = "pwdCfm_pwd") String member_pw, Model model) throws Exception {

		boolean result = mypageService.checkPwd(member_id, member_pw);
		if (result == true) {
			// 비밀번호가 맞다면 내정보 상세창 리턴
			return "redirect:/mypage/myInfo.do";
		} else {
			System.out.println(member_id);
			System.out.println(member_pw);
			return "redirect:/mypage/confirmPwdView.do";
		}
	}

	// 내정보(myInfo.jsp) 페이지 출력
	@Override
	@RequestMapping(value = "/mypage/myInfo.do", method = RequestMethod.GET)
	public ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// 내정보 수정 기능 (responseEntity)
	@Override
	@RequestMapping(value = "/mypage/modMyInfo.do", method = RequestMethod.POST)
	public ResponseEntity modMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> memberMap = new HashMap<String, String>();
		String val[] = null;
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		if (attribute.equals("member")) {
			val = value.split(",");
			memberMap.put("member_pw", val[0]);
			memberMap.put("member_phoneno", val[1]);
			memberMap.put("member_email1", val[2]);
			memberMap.put("member_email2", val[3]);
			memberMap.put("member_zipcode", val[4]);
			memberMap.put("member_roadAddress", val[5]);
			memberMap.put("member_jibunAddress", val[6]);
			memberMap.put("member_namujiAddress", val[7]);
		} else {
			memberMap.put(attribute, value);
		}

		memberMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(memberMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message = "mod_success";
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);

		return resEntity;

	}

	// 회원탈퇴 후 메인페이지 이동(로그아웃) 기능
	@Override
	@RequestMapping(value = "/mypage/deleteMember.do", method = RequestMethod.GET)
	public ModelAndView deleteMember(@RequestParam(value = "memDel_id2") String member_id, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		mypageService.deleteMember(member_id);
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/main.do");
		return mav;
	}

	// 나의 게시글 및 상담
	// 글목록 페이지 출력
	@Override
	@RequestMapping(value = "/mypage/myBoardList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView myBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List myBoardList = mypageService.selectMyBoardList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("myBoardList", myBoardList);

		return mav;
	}

	// 마이페이지상담글 삭제하기
	@Override
	@RequestMapping(value = "/mypage/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("fr_NO") int fr_NO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardFreeService.removeArticle(fr_NO);

			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/mypage/myBoardList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 시도해주세요');";
			message += " location.href='" + request.getContextPath() + "/mypage/myBoardList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 상담 목록 페이지 출력
	@Override
	@RequestMapping(value = "/mypage/myQna.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView myQna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List monthQnaList = mypageService.selectMonthQnasList(member_id);
		List rentQnaList = mypageService.selectRentQnasList(member_id);
		List returnQnaList = mypageService.selectReturnQnasList(member_id);
		List weddingQnaList = mypageService.selectWeddingQnasList(member_id);
		List shareQnaList = mypageService.selectShareQnasList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("monthQnaList", monthQnaList);
		mav.addObject("rentQnaList", rentQnaList);
		mav.addObject("returnQnaList", returnQnaList);
		mav.addObject("weddingQnaList", weddingQnaList);
		mav.addObject("shareQnaList", shareQnaList);

		return mav;
	}

	// 마이페이지상담글 삭제하기
	@Override
	@RequestMapping(value = "/mypage/removeQna.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			qnaService.removeQna(qna_no);

			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQna.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 시도해주세요');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQna.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 비밀글 비밀번호 체크하기
	@Override
	@RequestMapping(value = "/mypage/modalPwdCheck.do", method = RequestMethod.POST)
	public String modalPwdCheck(@RequestParam(value = "qna_no") int qna_no,
			@RequestParam(value = "qna_modalPwd") int qna_pw, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		System.out.println("qna_no:" + qna_no + "qna_pw:" + qna_pw);
		boolean result = qnaService.checkPwd(qna_no, qna_pw);
		System.out.println("result: " + result);

		if (result == true) {
			// 비밀번호가 맞다면 해당 글 상세창으로 이동
			return "redirect:/qna/viewQna.do?qna_no=" + qna_no;
		} else {
			out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='" + request.getContextPath()
					+ "/mypage/myQna.do'; </script>");
			out.flush();
			return null;
		}

	}

}
