package com.onestop.GJ.admin.stats.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface AdminStatsController {



	ModelAndView stats(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView searchVisit(Date fromDate, Date toDate, HttpServletRequest request, HttpServletResponse response)
			throws Exception;


}

