package com.onestop.GJ.email.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.email.service.MailService;
import com.onestop.GJ.email.service.MailServiceImpl;
import com.onestop.GJ.member.controller.MemberController;
import com.onestop.GJ.member.dao.MemberDAO;
import com.onestop.GJ.member.vo.MemberVO;

@Controller
@EnableAsync
public class MailControllerImpl implements MailController {
	@Autowired
	private MailService mailService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private MemberDAO memberDAO;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 회원가입전 이메일로 본인인증
	@RequestMapping(value = "/email/mail.do", method = RequestMethod.POST)
	public String emailConfirm(String email) throws Exception {
		logger.info("post emailConfirm");
		System.out.println("전달 받은 이메일 : " + email);
		mailService.sendSimpleMessage(email);
		return "redirect:/member/emailSend.do";

	}

	@RequestMapping(value = "/email/verifyCode.do", method = RequestMethod.POST)

	public ResponseEntity verifyCode(String code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		logger.info("Post verifyCode");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		int result = 0;
		System.out.println("code : " + code);
		System.out.println("code match : " + MailServiceImpl.ePw.equals(code));

		if (MailServiceImpl.ePw.equals(code)) {
			result = 1;

			message = "<script>";
			message += " alert('인증이 완료되었습니다.');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm3.do';";
			message += " </script>";
		} else {
			message = "<script>";
			message += " alert('인증번호가 틀렸습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/emailSend.do';";
			message += " </script>";

		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

}
