package com.onestop.GJ.mypage.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller
public class MypageControllerImpl implements MypageController {
	@Autowired
	private MypageService mypageService;
	@Autowired
	private MemberVO memberVO;

	// ��й�ȣ ��Ȯ�� ������
	@Override
	@RequestMapping(value = "/mypage/confirmPwdView.do", method = RequestMethod.GET)
	public ModelAndView confirmPwdView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// ���üũ �� ������ ��� �޼���� �̵�
	@Override
	@RequestMapping(value = "/mypage/myInfoView.do", method = RequestMethod.POST)
	public String myInfo(@RequestParam(value = "pwdCfm_id2") String member_id,
			@RequestParam(value = "pwdCfm_pwd") String member_pw, Model model) throws Exception {

		boolean result = mypageService.checkPwd(member_id, member_pw);
		if (result == true) {
			// ��й�ȣ�� �´ٸ� ������ ��â ����
			return "redirect:/mypage/myInfo.do";
		} else {
			System.out.println(member_id);
			System.out.println(member_pw);
			return "redirect:/mypage/confirmPwdView.do";
		}

	}

	// ������(myInfo.jsp) ������ ���
	@Override
	@RequestMapping(value = "/mypage/myInfo.do", method = RequestMethod.GET)
	public ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	// ������ ���� ��� (responseEntity)
		@Override
		@RequestMapping(value = "/mypage/modMyInfo.do", method = RequestMethod.POST)
		public ResponseEntity modMyInfo(@RequestParam("attribute") String attribute,
									@RequestParam("value") String value,
					HttpServletRequest request, HttpServletResponse response) throws Exception {
			Map<String, String> memberMap = new HashMap<String, String>();
			String val[] = null;
			HttpSession session = request.getSession();
			memberVO=(MemberVO)session.getAttribute("member");
			String member_id = memberVO.getMember_id();
			if(attribute.equals("member")) {
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
			
			memberVO = (MemberVO)mypageService.modifyMember(memberMap);
			session.removeAttribute("member");
			session.setAttribute("member", memberVO);
			
			String message = null;
			ResponseEntity resEntity = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			message = "mod_success";
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			
			return resEntity;
			
		}
	

	// ȸ��Ż�� �� ���������� �̵�(�α׾ƿ�) ���
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
	

}
