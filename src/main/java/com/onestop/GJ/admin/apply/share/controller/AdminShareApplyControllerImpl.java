package com.onestop.GJ.admin.apply.share.controller;

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

import com.onestop.GJ.admin.apply.share.service.AdminShareApplyService;
import com.onestop.GJ.admin.apply.share.vo.AdminShareApplyVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("AdminShareApplyController")
public class AdminShareApplyControllerImpl implements AdminShareApplyController {
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private AdminShareApplyService adminService;
	@Autowired
	private MypageService mypageService;

	@Override
	@RequestMapping(value = "/admin/adminApply/adminShareApply.do", method = RequestMethod.GET)
	public ModelAndView applyListMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));

		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map applyMap = adminService.joinTable(pagingMap);
		applyMap.put("section", section);
		applyMap.put("pageNum", pageNum);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("applyMap", applyMap);
		return mav;
	}

	// 검색창
	@Override
	@RequestMapping(value = "/admin/adminApply/adminSearchShareApply.do", method = RequestMethod.GET)
	public ModelAndView searchApplyList(@RequestParam("searchApply") String searchApply,
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
		pagingMap.put("searchApply", searchApply);
		pagingMap.put("searchType", searchType);
		Map applyMap = adminService.searchMemberList(pagingMap);

		applyMap.put("section", section);
		applyMap.put("pageNum", pageNum);
		applyMap.put("searchApply", searchApply);
		applyMap.put("searchType", searchType);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("applyMap", applyMap);
		return mav;
	}

//  신청 정보 상세
	@RequestMapping(value = "/admin/adminApply/adminShareViewApply.do", method = RequestMethod.GET)
	public ModelAndView viewMember(@RequestParam("sh_no") int sh_no, @RequestParam("member_id") String member_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map membersMap = adminService.viewApplyMember(sh_no, member_id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("membersMap", membersMap);
		return mav;
	}

	// 내정보 수정 기능 (responseEntity)
	@Override
	@RequestMapping(value = "/admin/adminApply/modifyAdminShare.do", method = RequestMethod.POST)
	public ResponseEntity modifyAdminShare(@RequestParam("attribute") String attribute,
			@RequestParam("value") String value, @RequestParam("sh_no") int sh_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map membersMap = new HashMap();
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		
		String val[] = null;
		if (attribute.equals("adminShare")) {
			val = value.split(",");
			if (val[0] == "null") {
				membersMap.put("_sh_result", null);
			} else if (val[0] != "null") {
				membersMap.put("_sh_result", val[0]);
			}
			if (val[1] == "null") {
				membersMap.put("_sh_reason", null);
			} else if (val[1] != "null") {
				membersMap.put("_sh_reason", val[1]);
			}
			membersMap.put("_sh_startpay", val[2]);
			membersMap.put("_sh_endpay", val[3]);
		} else {
			membersMap.put(attribute, value);
		}
		membersMap.put("sh_no", sh_no);
		
		if (!membersMap.get("_sh_result").equals("승인") && !membersMap.get("_sh_startpay").equals("null")) {
			message = "not_correct";
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEntity;
		} 

		AdminShareApplyVO applyShareVO = (AdminShareApplyVO) adminService.modifyAdminShare(membersMap);
		membersMap.put("applyShare", applyShareVO);

		if (membersMap.get("_sh_startpay").equals("null")) {
		} else {
			AdminShareApplyVO applyShareVO2 = (AdminShareApplyVO) adminService.modifyAdminSharePay(membersMap);
			membersMap.put("applyShare", applyShareVO2);
		}
		message = "mod_success";
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

}
