package com.onestop.GJ.admin.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.admin.member.service.AdminMemberService;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("adminMemberController")
public class AdminMemberControllerImpl implements AdminMemberController {
	@Autowired
	private AdminMemberService adminMemberService;
	@Autowired
	private MemberVO memberVO;
	@Autowired

	private MypageService mypageService;

	// 회원정보리스트
	@Override
	@RequestMapping(value = "/admin/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");

		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");

		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map membersMap = adminMemberService.listMembers(pagingMap);

		membersMap.put("section", section);
		membersMap.put("pageNum", pageNum);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersMap", membersMap);
		return mav;
	}

	// 검색창
	@Override
	@RequestMapping(value = "/admin/member/searchMemberList.do", method = RequestMethod.GET)
	public ModelAndView searchMemberList(@RequestParam("searchMember") String searchMember,
			@RequestParam("searchType") String searchType, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		pagingMap.put("searchMember", searchMember);
		pagingMap.put("searchType", searchType);

		Map membersMap = adminMemberService.searchMemberList(pagingMap);

		membersMap.put("section", section);
		membersMap.put("pageNum", pageNum);
		membersMap.put("searchMember", searchMember);
		membersMap.put("searchType", searchType);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersMap", membersMap);
		return mav;
	}

	// 게시글 상세
	@RequestMapping(value = "/admin/member/viewMember.do", method = RequestMethod.GET)
	public ModelAndView viewMember(@RequestParam("member_id") String member_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map membersMap = adminMemberService.viewMember(member_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("membersMap", membersMap);
		return mav;
	}

	// 내정보 수정 기능 (responseEntity)
	@Override
	@RequestMapping(value = "/admin/member/modMemberInfo.do", method = RequestMethod.POST)
	public ResponseEntity modMemberInfo(@RequestParam("attribute") String attribute,
			@RequestParam("value") String value, @RequestParam("member_id") String sltmember_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		Map<String, String> membersMap = new HashMap<String, String>();
		String val[] = null;
		MemberVO memberVO = (MemberVO) adminMemberService.selectMemberId(sltmember_id);

		if (attribute.equals("member")) {
			val = value.split(",");
			membersMap.put("member_pw", val[0]);
			membersMap.put("member_phoneno", val[1]);
			membersMap.put("member_email1", val[2]);
			membersMap.put("member_email2", val[3]);
			membersMap.put("member_zipcode", val[4]);
			membersMap.put("member_roadAddress", val[5]);
			membersMap.put("member_jibunAddress", val[6]);
			membersMap.put("member_namujiAddress", val[7]);
			membersMap.put("member_right", val[8]);
		} else {
			membersMap.put(attribute, value);
		}
		membersMap.put("member_id", sltmember_id);
		memberVO = (MemberVO) adminMemberService.modifyMember_adm(membersMap);

		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message = "mod_success";
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	// 회원삭제
	@RequestMapping(value = "/admin/member/removeMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView removeMember(@RequestParam(value = "member_id") String member_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		adminMemberService.removeMember(member_id);
		mav.setViewName("redirect:/admin/member/listMembers.do");
		return mav;
	}

}
