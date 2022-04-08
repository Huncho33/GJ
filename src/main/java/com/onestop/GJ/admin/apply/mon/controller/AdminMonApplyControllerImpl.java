package com.onestop.GJ.admin.apply.mon.controller;

import java.util.Collections;
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

import com.onestop.GJ.admin.apply.mon.service.AdminMonApplyService;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("AdminMonApplyController")
public class AdminMonApplyControllerImpl implements AdminMonApplyController {
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private AdminMonApplyService adminService;
	@Autowired
	private MypageService mypageService;

	@Override
	@RequestMapping(value = "/admin/adminApply/adminMonthApply.do", method = RequestMethod.GET)
	public ModelAndView applyListMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		System.out.println("회원정보리스트");
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));

		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		System.out.println("controller  pagingMap 값들 : " + pagingMap);
		Map applyMap = adminService.joinTable(pagingMap);
		applyMap.put("section", section);
		applyMap.put("pageNum", pageNum);
		System.out.println("applyMap 값 체크 : " + applyMap);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("applyMap", applyMap);

		return mav;
	}

	// 검색창
	@Override
	@RequestMapping(value = "/admin/adminApply/adminSearchMonthApply.do", method = RequestMethod.GET)
	public ModelAndView searchApplyList(@RequestParam("searchApply") String searchApply,
			@RequestParam("searchType") String searchType, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("1");
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		pagingMap.put("searchApply", searchApply);
		pagingMap.put("searchType", searchType);
		Map applyMap = adminService.searchMemberList(pagingMap);

		System.out.println("applyMap : " + applyMap);
		applyMap.put("section", section);
		applyMap.put("pageNum", pageNum);
		applyMap.put("searchApply", searchApply);
		applyMap.put("searchType", searchType);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("applyMap", applyMap);
		System.out.println("검색창 applyMap 값들 : " + applyMap);
		System.out.println("??" + applyMap.size());
		return mav;
	}

//  신청 정보 상세
	@RequestMapping(value = "/admin/adminApply/adminViewApply.do", method = RequestMethod.GET)
	public ModelAndView viewMember(@RequestParam("mo_no") int mo_no, @RequestParam("member_id") String member_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map membersMap = adminService.viewApplyMember(mo_no, member_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("membersMap", membersMap);
		return mav;
	}

	// 내정보 수정 기능 (responseEntity)
	@Override
	@RequestMapping(value = "/admin/adminApply/modifyAdminMon.do", method = RequestMethod.POST)
	public ResponseEntity modifyAdminMon(@RequestParam("attribute") String attribute,
			@RequestParam("value") String value, @RequestParam("mo_no") int mo_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("!");
		Map membersMap = new HashMap();
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		
		String val[] = null;
		if (attribute.equals("adminMon")) {
			val = value.split(",");
			if (val[0] == "null") {
				membersMap.put("_mo_result", null);
			} else if (val[0] != "null") {
				membersMap.put("_mo_result", val[0]);
			}
			if (val[1] == "null") {
				membersMap.put("_mo_reason", null);
			} else if (val[1] != "null") {
				membersMap.put("_mo_reason", val[1]);
			}
			membersMap.put("_mo_startpay", val[2]);
			membersMap.put("_mo_endpay", val[3]);
		} else {
			membersMap.put(attribute, value);
		}
		membersMap.put("mo_no", mo_no);
		
		if (!membersMap.get("_mo_result").equals("승인") && !membersMap.get("_mo_startpay").equals("null")) {
			System.out.println("먹어라: " + membersMap.get("_mo_result"));
			message = "not_correct";
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEntity;
		} 

		ApplyMonVO applyMonVO = (ApplyMonVO) adminService.modifyAdminMon(membersMap);
		membersMap.put("applyMon", applyMonVO);
		System.out.println("_mo_startpay 값 : " + membersMap.get("_mo_startpay"));
		System.out.println("_mo_startpay 형 : " + membersMap.get("_mo_startpay").getClass().getName());

		if (membersMap.get("_mo_startpay").equals("null")) {
			System.out.println("pass");
		} else {
			ApplyMonVO applyMonVO2 = (ApplyMonVO) adminService.modifyAdminMonPay(membersMap);
			membersMap.put("applyMon", applyMonVO2);
		}
		System.out.println("membersMap 값들 : " + membersMap);
		System.out.println("membersMap 값들 : " + (membersMap.get("applyMon")));

		

		
		message = "mod_success";
		
		
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;

	}

}
