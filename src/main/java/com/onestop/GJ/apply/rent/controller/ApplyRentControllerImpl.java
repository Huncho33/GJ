package com.onestop.GJ.apply.rent.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.rent.service.ApplyRentService;
import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("ApplyRentControllerImpl")
public class ApplyRentControllerImpl implements ApplyRentController {

	private static String rentApply_REPO = "C:\\GJ\\file_repo\\apply\\rent";
	@Autowired
	private ApplyRentService applyrentService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ApplyRentVO applyrentVO;

	// ��û�� ���̵� �ִ��� üũ
	@RequestMapping(value = "/rent/rentApplyForm0.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ResponseEntity applyCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("content-Type", "text/html; charset=utf-8");
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		ApplyRentVO list = applyrentService.findAll(id);
		if (list != null && list.getMember_id().equals(id)) {
			message = "<script>";
			message += " alert('�̹� ��û�� ���̵��Դϴ�.');";
			message += " location.href='" + request.getContextPath() + "/main.do'";
			message += " </script>";
		} else {
			message = "<script>";
			message += " location.href='" + request.getContextPath() + "/rent/rentApplyForm1.do'";
			message += " </script>";
		}
		return resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	}

	// ��û������ ȣ��(��û�ڰ�����ȭ��)
	@RequestMapping(value = "/rent/rentApplyForm1.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView applyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;

	}

	// ��û���� ���(����)
		@Override
		@RequestMapping(value = "/rent/rentApplyForm2.do", method = RequestMethod.POST)
		public ModelAndView modApplyInfo(@ModelAttribute("member") MemberVO member,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			memberVO = (MemberVO) session.getAttribute("member");
			ModelAndView mav = new ModelAndView();
			memberVO = (MemberVO) applyrentService.modifyMember(member);
			mav.setViewName("redirect:/rent/rentApplyForm3.do");
			return mav;

			
		}

	// ��û������ ȭ�� ȣ��(rentApplyForm3)
	@RequestMapping(value = "/rent/rentApplyForm3.do", method = RequestMethod.POST)
	private ModelAndView applyForms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/rent/rentApplyForm4.do");

		return mav;
	}

	// ��û���
	@Override
	@RequestMapping(value = "/rent/rentApplyForm4.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		String up_fileName = null;
		Map articleMap = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		articleMap.put("member_id", member_id);

		List<String> fileList = upload(multipartRequest, RequestMethod.POST);
		List<ApplyRentFileVO> imageFileList = new ArrayList<ApplyRentFileVO>();
		fileList.removeAll(Arrays.asList("", null));
		if (fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				ApplyRentFileVO applyRentFileVO = new ApplyRentFileVO();
				applyRentFileVO.setUp_filename(fileName);
				imageFileList.add(applyRentFileVO);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int rent_no = applyrentService.addResult(articleMap);
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ApplyRentFileVO applyRentFileVO : imageFileList) {
					up_fileName = applyRentFileVO.getUp_filename();
					File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + up_fileName);
					File destDir = new File(rentApply_REPO + "\\" + rent_no);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}

			message = "<script>";
			message += " alert('��û�� �Ϸ�Ǿ����ϴ�..');";
			message += " location.href='" + multipartRequest.getContextPath() + "/rent/rentApplyResult.do?rent_no="
					+ rent_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ApplyRentFileVO applyRentFileVO : imageFileList) {
					up_fileName = applyRentFileVO.getUp_filename();
					File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + up_fileName);
					srcFile.delete();
				}
			}

			message = " <script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/rent/rentApplyForm3.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
		//
	}

	// ���� ���� ���ε��ϱ�
	private List<String> upload(MultipartHttpServletRequest multipartRequest, RequestMethod post) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(rentApply_REPO + "\\" + "temp" + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // ��λ� ������ �������� ���� ���
					file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
					mFile.transferTo(new File(rentApply_REPO + "\\" + "temp" + "\\" + originalFileName));
				}
			}
		}
		return fileList;
	}

	// ���������
	@RequestMapping(value = "/rent/rentApplyResult.do")
	public ModelAndView findAll(HttpServletRequest request, @RequestParam("rent_no") int rent_no) {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		int no = applyrentVO.getRent_no();

		ApplyRentVO applyno = applyrentService.findNo(rent_no);

		mav.addObject("no", applyno);
		mav.setViewName(viewName);
		return mav;
	}

	// �������������Ȯ��
	@RequestMapping(value = "/rent/rentSelectedResult.do")
	private ModelAndView rentSelectedPage(HttpServletRequest request) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		ApplyRentVO list = applyrentService.findAll(id);
		mav.addObject("apply", list);
		mav.setViewName(viewName);
		if (list == null) {
			mav.addObject("message", "��û������ ���� ���̵� �Դϴ�.");
		} else {
		}

		return mav;
	}

}
