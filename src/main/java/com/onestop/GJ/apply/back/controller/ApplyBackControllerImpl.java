package com.onestop.GJ.apply.back.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.onestop.GJ.apply.back.service.ApplyBackService;
import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("ApplyBackControllerImpl")
public class ApplyBackControllerImpl implements ApplyBackController {

	private static String backApply_REPO = "C:\\GJ\\file_repo\\apply\\back";
	@Autowired
	private ApplyBackService applyBackService;
	@Autowired
	private MypageService mypageService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ApplyBackVO applyBackVO;

	// 신청한 아이디 체크
		@RequestMapping(value = "/back/backApplyForm0.do", method = { RequestMethod.GET, RequestMethod.POST })
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
			ApplyBackVO list = applyBackService.findAll(id);
			if (list != null && list.getMember_id().equals(id)) {
				message = "<script>";
				message += " alert('이미 신청한 아이디입니다.');";
				message += " location.href='" + request.getContextPath() + "/main.do'";
				message += " </script>";
			} else {
				message = "<script>";
				message += " location.href='" + request.getContextPath() + "/back/backApplyForm1.do'";
				message += " </script>";
			}
			return resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

	// 신청페이지 호출(신청자격조건화면)
	@RequestMapping(value = "/back/backApplyForm1.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView applyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;

	}

	// 신청정보 등록(수정)
	@Override
	@RequestMapping(value = "/back/backApplyForm2.do", method = RequestMethod.POST)
	public ResponseEntity modApplyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> memberMap = new HashMap<String, String>();
		String val[] = null;
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		if (attribute.equals("member")) {
			val = value.split(",");
			memberMap.put("member_phoneno", val[0]);
			memberMap.put("member_email1", val[1]);
			memberMap.put("member_email2", val[2]);
			memberMap.put("member_zipcode", val[3]);
			memberMap.put("member_roadAddress", val[4]);
			memberMap.put("member_jibunAddress", val[5]);
			memberMap.put("member_namujiAddress", val[6]);
		} else {
			memberMap.put(attribute, value);
		}

		memberMap.put("member_id", member_id);
		memberVO = (MemberVO) mypageService.modifyMember(memberMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message = "mod_success";
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);

		return resEntity;
	}

	// 신청페이지 화면 호출(backApplyForm3)
	@RequestMapping(value = "/back/backApplyForm3.do", method = RequestMethod.POST)
	private ModelAndView applyForms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/back/backApplyForm4.do");
		return mav;
	}

	// 첨부파일등록
	@Override
	@RequestMapping(value = "/back/backApplyForm4.do", method = RequestMethod.POST)
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
		List<ApplyBackFileVO> imageFileList = new ArrayList<ApplyBackFileVO>();
		fileList.removeAll(Arrays.asList("", null));
		if (fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				ApplyBackFileVO applyBackFileVO = new ApplyBackFileVO();
				applyBackFileVO.setUp_filename(fileName);
				imageFileList.add(applyBackFileVO);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int ba_no = applyBackService.addResult(articleMap);
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ApplyBackFileVO applyBackFileVO : imageFileList) {
					up_fileName = applyBackFileVO.getUp_filename();
					File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + up_fileName);
					File destDir = new File(backApply_REPO + "\\" + ba_no);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}

			message = "<script>";
			message += " alert('신청이 완료되었습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/back/backApplyResult.do?ba_no="
					+ ba_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ApplyBackFileVO applyBackFileVO : imageFileList) {
					up_fileName = applyBackFileVO.getUp_filename();
					File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + up_fileName);
					srcFile.delete();
				}
			}

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/back/backApplyForm3.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 다중 파일 업로드하기
	private List<String> upload(MultipartHttpServletRequest multipartRequest, RequestMethod post) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(backApply_REPO + "\\" + "temp" + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
					file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들을 생성
					mFile.transferTo(new File(backApply_REPO + "\\" + "temp" + "\\" + originalFileName));
				}
			}
		}
		return fileList;
	}

	// 결과페이지
	@RequestMapping(value = "/back/backApplyResult.do")
	public ModelAndView findAll(HttpServletRequest request, @RequestParam("ba_no") int ba_no) {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		int no = applyBackVO.getBa_no();
		ApplyBackVO applyno = applyBackService.findNo(ba_no);
		mav.addObject("no", applyno);
		mav.setViewName(viewName);
		return mav;
	}

	// 선정결과페이지확인
	@RequestMapping(value = "/back/backSelectedResult.do")
	private ModelAndView backSelectedPage(HttpServletRequest request) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		ApplyBackVO list = applyBackService.findAll(id);

		mav.addObject("apply", list);
		mav.setViewName(viewName);
		if (list == null) {
			mav.addObject("message", "신청내역이 없는 아이디 입니다.");
		} else {
		}
		return mav;
	}

}