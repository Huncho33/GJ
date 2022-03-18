package com.onestop.GJ.board.QNA.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

	@Override
	@RequestMapping(value = { "/qna/listQnas.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listQnas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List QnasList = qnaService.listQnas();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("QnasList", QnasList);
		return mav;
	}

	// ���� �̹��� �۾���
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
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');');";
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

	@RequestMapping(value = { "/qna/viewQna.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map QnaMap = qnaService.viewQna(qna_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("QnaMap", QnaMap);
		return mav;
	}
//
//	/*
//	// �� �� �̹��� ���� ���
//	@RequestMapping(value = "/board/modQna.do", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity modQna(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
//			throws Exception {
//		multipartRequest.setCharacterEncoding("utf-8");
//		Map<String, Object> QnaMap = new HashMap<String, Object>();
//		Enumeration enu = multipartRequest.getParameterNames();
//		while (enu.hasMoreElements()) {
//			String name = (String) enu.nextElement();
//			String value = multipartRequest.getParameter(name);
//			QnaMap.put(name, value);
//		}
//
//		String imageFileName = upload(multipartRequest);
//		QnaMap.put("imageFileName", imageFileName);
//
//		String QnaNO = (String) QnaMap.get("QnaNO");
//		String message;
//		ResponseEntity resEnt = null;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
//		try {
//			boardService.modQna(QnaMap);
//			if (imageFileName != null && imageFileName.length() != 0) {
//				File srcFile = new File(Qna_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
//				File destDir = new File(Qna_IMAGE_REPO + "\\" + QnaNO);
//				FileUtils.moveFileToDirectory(srcFile, destDir, true);
//
//				String originalFileName = (String) QnaMap.get("originalFileName");
//				File oldFile = new File(Qna_IMAGE_REPO + "\\" + QnaNO + "\\" + originalFileName);
//				oldFile.delete();
//			}
//			message = "<script>";
//			message += " alert('���� �����߽��ϴ�.');";
//			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewQna.do?QnaNO="
//					+ QnaNO + "';";
//			message += " </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//		} catch (Exception e) {
//			File srcFile = new File(Qna_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
//			srcFile.delete();
//			message = "<script>";
//			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
//			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewQna.do?QnaNO="
//					+ QnaNO + "';";
//			message += " </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//		}
//		return resEnt;
//	} */
//
//	// �� �����ϱ�
//	@Override
//	@RequestMapping(value = "/board/removeQna.do", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity removeQna(@RequestParam("QnaNO") int QnaNO, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		response.setContentType("text/html; charset=utf-8");
//		String message;
//		ResponseEntity resEnt = null;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
//		try {
//			qnaService.removeQna(QnaNO);
//			File destDir = new File(Qna_IMAGE_REPO + "\\" + QnaNO);
//			FileUtils.deleteDirectory(destDir);
//
//			message = "<script>";
//			message += " alert('���� �����߽��ϴ�.');";
//			message += " location.href='" + request.getContextPath() + "/board/listQnas.do';";
//			message += " </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//		} catch (Exception e) {
//			message = "<script>";
//			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
//			message += " location.href='" + request.getContextPath() + "/board/listQnas.do';";
//			message += " </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//			e.printStackTrace();
//		}
//		return resEnt;
//	}

}