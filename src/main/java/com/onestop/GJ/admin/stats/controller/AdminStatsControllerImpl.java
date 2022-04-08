package com.onestop.GJ.admin.stats.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.admin.apply.mon.service.AdminMonApplyService;
import com.onestop.GJ.admin.stats.service.AdminStatsService;
import com.onestop.GJ.member.vo.MemberVO;

@Controller("adminStatsController")
public class AdminStatsControllerImpl implements AdminStatsController {
	@Autowired
	private MemberVO memberVO;

	@Autowired
	private AdminStatsService statsService;
	
	@Autowired
	private AdminMonApplyService adminService;

	//통계 방문자 리스트
	@Override
	@RequestMapping(value = { "/admin/stats/stats.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView stats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		int section = Integer.parseInt(((_section==null)? "1":_section));
		int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		Map visitMap = statsService.listStats(pagingMap);
		Map applyMap = adminService.joinTable(pagingMap);
		System.out.println("applyMap : " + applyMap);
		visitMap.put("section", section);
		visitMap.put("pageNum", pageNum);
		visitMap.put("applyMap", applyMap);
		
		Map countMap = statsService.getTotCnt(visitMap); //총 방문자수, 구별 인구 수, 남녀비율 
		System.out.println("visitMap : " + visitMap);
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("visitMap", visitMap);
		mav.addObject("countMap", countMap);
		return mav;

	}

	// 검색창
	@Override
	@RequestMapping(value = "/admin/stats/searchVisit.do", method = RequestMethod.GET)
	public ModelAndView searchVisit(
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		  String _section = request.getParameter("section");
	      String _pageNum = request.getParameter("pageNum");
	      int section = Integer.parseInt(((_section == null) ? "1" : _section));
	      int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
	      
	      
		Map dateMap = new HashMap();
		System.out.println("확인 1  : " + fromDate);
		System.out.println("확인 2  : " + toDate);
//         date 타입 -> string 타입으로 변환
		Date fromDate1 = fromDate;
		Date toDate1 = toDate;
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		DateFormat formatter2 = new SimpleDateFormat("YYYY-MM-dd");
		String result1 = formatter.format(fromDate1);
		String result2 = formatter2.format(toDate1);

		System.out.println("result1   : " + result1);
		System.out.println("확인 4  : " + result2);
		dateMap.put("fromDate", result1);
		dateMap.put("toDate", result2);
		dateMap.put("section", section);
		dateMap.put("pageNum", pageNum);
		System.out.println("컨트롤러dateMap : " + dateMap);
		
		Map searchMap = statsService.searchVisit(dateMap);
		searchMap.put("fromDate", result1);
		searchMap.put("toDate", result2);
		searchMap.put("section", section);
		searchMap.put("pageNum", pageNum);
		
		System.out.println("최종 searchMap : " + searchMap);

//         searchMap.put("fromDate", fromDate);
//         searchMap.put("toDate", toDate);

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("searchMap", searchMap);
		System.out.println("searchMap" + searchMap.size());
		return mav;
	}
}