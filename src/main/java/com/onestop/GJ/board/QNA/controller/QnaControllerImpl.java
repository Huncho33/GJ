package com.onestop.GJ.board.QNA.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.board.QNA.service.QnaService;
import com.onestop.GJ.board.QNA.vo.QnaVO;
import com.onestop.GJ.member.vo.MemberVO;

@Controller("QnaController")
public class QnaControllerImpl implements QnaController {
	@Autowired
	QnaService qnaService;
	
	@Autowired
	QnaVO QnaVO;

	
	// �� ��� �ҷ�����
	@Override
	@RequestMapping(value = { "/qna/listQnas.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listQnas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		int section = Integer.parseInt(((_section==null)? "1":_section));
		int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
		
		Map pagingMap = new HashMap();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map qnasMap = qnaService.listQnas(pagingMap);
		
		qnasMap.put("section", section);
		qnasMap.put("pageNum", pageNum);
		
		
		String viewName = (String) request.getAttribute("viewName");

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("qnasMap", qnasMap);
		return mav;
	}

	// ���� �ۼ��ϱ�
	@Override
	@RequestMapping(value = { "/qna/addNewQna.do" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewQna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		Map QnaMap = new HashMap();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			QnaMap.put(name, value);
		}

		// �α��� �� ���ǿ� ����� ȸ�� �������� �۾��� ���̵� ���ͼ� Map�� �����մϴ�.
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		QnaMap.put("member_id", member_id);
		String qnaparent_no = (String)session.getAttribute("qnaparent_no");
		QnaMap.put("qnaparent_no", (qnaparent_no == null ? 0 : qnaparent_no));

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			qnaService.addNewQna(QnaMap);

			message = "<script>";
			message += " alert('������ �߰��߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/qna/listQnas.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {

			message = " <script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.');');";
			message += " location.href='" + request.getContextPath() + "/qna/QnaForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	
	// ��� �ۼ��ϱ�
		@Override
		@RequestMapping(value = { "/qna/addNewReply.do" }, method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity addNewReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			request.setCharacterEncoding("utf-8");

			Map QnaMap = new HashMap();
			Enumeration enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = (String) enu.nextElement();
				String value = request.getParameter(name);
				QnaMap.put(name, value);
			}

			// �α��� �� ���ǿ� ����� ȸ�� �������� �۾��� ���̵� ���ͼ� Map�� �����մϴ�.
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			String member_id = memberVO.getMember_id();
			QnaMap.put("member_id", member_id);
			String qnaparent_no = (String)session.getAttribute("qnaparent_no");
			QnaMap.put("qnaparent_no", (qnaparent_no == null ? 0 : qnaparent_no));

			String message;
			ResponseEntity resEnt = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			try {
				qnaService.addNewQna(QnaMap);
				qnaService.updateReply(QnaMap);

				message = "<script>";
				message += " alert('������ �߰��߽��ϴ�.');";
				message += " location.href='" + request.getContextPath() + "/qna/listQnas.do'; ";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			} catch (Exception e) {

				message = " <script>";
				message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.');');";
				message += " location.href='" + request.getContextPath() + "/qna/QnaForm.do'; ";
				message += " </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				e.printStackTrace();
			}
			return resEnt;
		}
	
	
	
	// �۾���â ȣ��
	@RequestMapping(value = "/qna/QnaForm.do", method = RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;

	}
	
	
	// �󼼱� ����
	@RequestMapping(value = { "/qna/viewQna.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewQna(@RequestParam("qna_no") int qna_no,
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map QnaMap = qnaService.viewQna(qna_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("QnaMap", QnaMap);
		return mav;
	}
	
	
	// ��б� ��й�ȣ üũ�ϱ�
	@Override
	@RequestMapping(value = "/qna/modalPwdCheck.do", method = RequestMethod.POST)
	public String modalPwdCheck(@RequestParam(value = "qna_no") int qna_no,
								@RequestParam(value = "qna_modalPwd") int qna_pw,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		System.out.println("qna_no:"+qna_no + "qna_pw:"+ qna_pw);
		boolean result = qnaService.checkPwd(qna_no, qna_pw);
		System.out.println("result: " + result);
		
		if (result == true) {
			// ��й�ȣ�� �´ٸ� �ش� �� ��â���� �̵�
			return "redirect:/qna/viewQna.do?qna_no="+qna_no;
		} else {
			out.println("<script>alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); location.href='" + request.getContextPath() + "/qna/listQnas.do'; </script>");
			out.flush();
			return null;
		}

	}

	
	// ���� �����ϱ�
	@RequestMapping(value = "/qna/modQna.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modQna(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> QnaMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			QnaMap.put(name, value);
		}

		String qna_no = (String) QnaMap.get("qna_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			qnaService.modQna(QnaMap);
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/qna/viewQna.do?qna_no="
					+ qna_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�. ����� ������ �ʼ��Է��Դϴ�.');";
			message += " location.href='" + request.getContextPath() + "/qna/viewQna.do?qna_no=" + qna_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}
	
	
	//// �۾���â ȣ��
	@RequestMapping(value = "/qna/replyForm.do", method = RequestMethod.POST)
	private ModelAndView replyForm(@RequestParam(value="qnaparent_no", required=false) String qnaparent_no,
					HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map QnaParentMap = qnaService.selectParentQna(qnaparent_no);
		System.out.println("QnaParentMap if ��: " + QnaParentMap );
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		if(qnaparent_no != null) {
			session.setAttribute("qnaparent_no", qnaparent_no);
			System.out.println("QnaParentMap if ��: " + QnaParentMap );
			mav.addObject("QnaParentMap", QnaParentMap);
		}
		mav.setViewName(viewName);
		return mav;

	}

	
	// ���� �����ϱ�
	@Override
	@RequestMapping(value = "/qna/removeQna.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, 
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			qnaService.removeQna(qna_no);

			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/qna/listQnas.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/qna/listQnas.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	

}