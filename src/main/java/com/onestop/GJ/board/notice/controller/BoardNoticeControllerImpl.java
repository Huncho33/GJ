package com.onestop.GJ.board.notice.controller;

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

import com.onestop.GJ.board.notice.service.BoardNoticeService;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;
import com.onestop.GJ.member.vo.MemberVO;

@Controller("boardController")
public class BoardNoticeControllerImpl implements BoardNoticeController {
	private static String ARTICLE_IMAGE_REPO = "C:\\GJ\\file_repo\\board\\notice";
	@Autowired
	BoardNoticeService boardService;
	@Autowired
	BoardNoticeVO boardNoticeVO;

	@Override
	@RequestMapping(value = { "/boardNotice/listArticles.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName") ;
		List articlesList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	
	// �� �� �̹��� �۾���
	@Override
	@RequestMapping(value = "/boardNotice/addNewArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		String up_fileName = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		articleMap.put("id", id);
		articleMap.put("up_fileName", up_fileName);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int noti_NO = boardService.addNewArticle(articleMap);
			if (up_fileName != null && up_fileName.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('������ �߰��߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/boardNotice/listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
			srcFile.delete();

			message = " <script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/boardNotice/articleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// �Ѱ� �̹��� ���ε��ϱ�
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String up_fileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			up_fileName = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // ��λ� ������ �������� ���� ���
					if (file.getParentFile().mkdirs()) { // ��ο� �ش��ϴ� ���丮���� ����
						file.createNewFile(); // ���� ���� ����
					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName));
				// �ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			}
		}
		return up_fileName;
	}

	// �Ѱ��� �̹��� �����ֱ�
	@RequestMapping(value = "/boardNotice/viewArticle.do", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("noti_NO") int noti_NO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		boardNoticeVO = boardService.viewArticle(noti_NO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", boardNoticeVO);
		return mav;
	}



	// �� �����ϱ�
	@Override
	@RequestMapping(value = "/boardNotice/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("noti_NO") int noti_NO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.removeArticle(noti_NO);
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/boardNotice/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/boardNotice/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}