package com.onestop.GJ.mypage.controller;

import java.io.File;
import java.io.PrintWriter;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.board.QNA.service.QnaService;
import com.onestop.GJ.board.free.service.BoardFreeService;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("mypageControllerImpl")
public class MypageControllerImpl implements MypageController {
	private static String monApply_REPO = "C:\\GJ\\file_repo\\apply\\month";
	private static String rentApply_REPO = "C:\\GJ\\file_repo\\apply\\rent";
	private static String retApply_REPO = "C:\\GJ\\file_repo\\apply\\rentReturn";
	private static String backApply_REPO = "C:\\GJ\\file_repo\\apply\\back";
	private static String shareApply_REPO = "C:\\GJ\\file_repo\\apply\\share";
	@Autowired
	private MypageService mypageService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private QnaService qnaService;
	@Autowired
	private BoardFreeService boardFreeService;

	// ��й�ȣ ��Ȯ�� ������
	@Override
	@RequestMapping(value = "/mypage/confirmPwdView.do", method = RequestMethod.GET)
	public ModelAndView confirmPwdView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// ȸ������ ����
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
	public ResponseEntity modMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> memberMap = new HashMap<String, String>();
		String val[] = null;
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		if (attribute.equals("member")) {
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

	// ���� �Խñ� �� ���
	// ��� ��� ������ ���
	@Override
	@RequestMapping(value = "/mypage/myQna.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView myQna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List monthQnaList = mypageService.selectMonthQnasList(member_id);
		List rentQnaList = mypageService.selectRentQnasList(member_id);
		List returnQnaList = mypageService.selectReturnQnasList(member_id);
		List weddingQnaList = mypageService.selectWeddingQnasList(member_id);
		List shareQnaList = mypageService.selectShareQnasList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("monthQnaList", monthQnaList);
		mav.addObject("rentQnaList", rentQnaList);
		mav.addObject("returnQnaList", returnQnaList);
		mav.addObject("weddingQnaList", weddingQnaList);
		mav.addObject("shareQnaList", shareQnaList);

		return mav;
	}

	// �������������� �����ϱ�
	@Override
	@RequestMapping(value = "/mypage/removeQna.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			qnaService.removeQna(qna_no);

			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQna.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQna.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// ��б� ��й�ȣ üũ�ϱ�
	@Override
	@RequestMapping(value = "/mypage/modalPwdCheck.do", method = RequestMethod.POST)
	public String modalPwdCheck(@RequestParam(value = "qna_no") int qna_no,
								@RequestParam(value = "qna_modalPwd") int qna_pw, 
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		boolean result = qnaService.checkPwd(qna_no, qna_pw);

		if (result == true) {
			// ��й�ȣ�� �´ٸ� �ش� �� ��â���� �̵�
			return "redirect:/qna/viewQna.do?qna_no=" + qna_no;
		} else {
			out.println("<script>alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); location.href='" + request.getContextPath()
					+ "/mypage/myQna.do'; </script>");
			out.flush();
			return null;
		}

	}

	// ���� ��û ��Ȳ
	// �������� ��û ��Ȳ ������ ���
	@Override
	@RequestMapping(value = "/mypage/monthApplyList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView monthApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List monthApplyList = mypageService.selectMonthApplyList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("monthApplyList", monthApplyList);

		return mav;
	}

	// �������� ��û ��Ȳ ������ ���
	@Override
	@RequestMapping(value = "/mypage/rentApplyList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView rentApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List rentApplyList = mypageService.selectRentApplyList(member_id);
		List rentReturnApplyList = mypageService.selectRentReturnApplyList(member_id);
		List backApplyList = mypageService.selectBackApplyList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("rentApplyList", rentApplyList);
		mav.addObject("rentReturnApplyList", rentReturnApplyList);
		mav.addObject("backApplyList", backApplyList);

		return mav;
	}

	// �����Ӵ����� ��û ��Ȳ ������ ���
	@Override
	@RequestMapping(value = "/mypage/shareApplyList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView shareApplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List shareApplyList = mypageService.selectShareApplyList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("shareApplyList", shareApplyList);

		return mav;
	}

	// û��������� �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/viewMonthApply.do", method = RequestMethod.GET)
	public ModelAndView viewMonthApply(@RequestParam("mo_no") int mo_no,
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewMonthMap = mypageService.selectViewMonthApply(member_id);
		ViewMonthMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewMonthMap", ViewMonthMap);
		return mav;
	}

	// �������� �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/viewRentApply.do", method = RequestMethod.GET)
	public ModelAndView viewRentApply(@RequestParam("rent_no") int rent_no,
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewRentMap = mypageService.selectViewRentApply(member_id);
		ViewRentMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewRentMap", ViewRentMap);
		return mav;
	}

	// �������� �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/viewRetApply.do", method = RequestMethod.GET)
	public ModelAndView viewRetApply(@RequestParam("ret_no") int ret_no,
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewRetMap = mypageService.selectViewRetApply(member_id);
		ViewRetMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewRetMap", ViewRetMap);
		return mav;
	}

	// �������� �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/viewBackApply.do", method = RequestMethod.GET)
	public ModelAndView viewBackApply(@RequestParam("ba_no") int ba_no,
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewBackMap = mypageService.selectViewBackApply(member_id);
		ViewBackMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewBackMap", ViewBackMap);
		return mav;
	}

	// �����Ӵ��������� �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/viewShareApply.do", method = RequestMethod.GET)
	public ModelAndView viewShareApply(@RequestParam("sh_no") int sh_no,
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewShareMap = mypageService.selectViewShareApply(member_id);
		ViewShareMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewShareMap", ViewShareMap);
		return mav;
	}

	// û��������� �����û �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/modMonthApplyView.do", method = RequestMethod.GET)
	public ModelAndView modMonthApplyView(
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewMonthMap = mypageService.selectViewMonthApply(member_id);
		ViewMonthMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewMonthMap", ViewMonthMap);
		return mav;
	}

	// �������������� �����û �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/modRentApplyView.do", method = RequestMethod.GET)
	public ModelAndView modRentApplyView(
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewRentMap = mypageService.selectViewRentApply(member_id);
		ViewRentMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewRentMap", ViewRentMap);
		return mav;
	}

	// ���������ݺ����� �����û �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/modRetApplyView.do", method = RequestMethod.GET)
	public ModelAndView modRetApplyView(
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewRetMap = mypageService.selectViewRetApply(member_id);
		ViewRetMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewRetMap", ViewRetMap);
		return mav;
	}

	// ��ȯ��ȥ�κ� �����û �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/modBackApplyView.do", method = RequestMethod.GET)
	public ModelAndView modBackApplyView(
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewBackMap = mypageService.selectViewBackApply(member_id);
		ViewBackMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewBackMap", ViewBackMap);
		return mav;
	}

	// û��������� �����û �� ������ ���
	@Override
	@RequestMapping(value = "/mypage/modShareApplyView.do", method = RequestMethod.GET)
	public ModelAndView modShareApplyView(
			@RequestParam(value = "removeCompleted", required = false) String removeCompleted,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		String member_id = memberVO.getMember_id();
		Map ViewShareMap = mypageService.selectViewShareApply(member_id);
		ViewShareMap.put("removeCompleted", removeCompleted);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("ViewShareMap", ViewShareMap);
		return mav;
	}

	// û���������-��û�����ϱ�
	@RequestMapping(value = "/mypage/modApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modApply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewMonthMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewMonthMap.put(name, value);
			}
		}

		ViewMonthMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewMonthMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewMonthMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewMonthMap.get("pre_img_num"));

		List<ApplyMonFileVO> monthFileList = new ArrayList<ApplyMonFileVO>();
		List<ApplyMonFileVO> modAddimageFileList = new ArrayList<ApplyMonFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewMonthMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyMonFileVO applyMonFileVO = new ApplyMonFileVO();

				if (i < pre_img_num) {
					applyMonFileVO.setUp_filename(up_fileName);
					applyMonFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					monthFileList.add(applyMonFileVO);
					ViewMonthMap.put("monthFileList", monthFileList);
				} else {
					applyMonFileVO.setUp_filename(up_fileName);
					monthFileList.add(applyMonFileVO);
					ViewMonthMap.put("monthFileList", monthFileList);
				}
			}
		}

		String mo_no = (String) ViewMonthMap.get("mo_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modApply(ViewMonthMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(monApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(monApply_REPO + "\\" + mo_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewMonthMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(monApply_REPO + "\\" + mo_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(monApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(monApply_REPO + "\\" + mo_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewMonthApply.do?mo_no="
					+ mo_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(monApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewMonthApply.do?mo_no="
					+ mo_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// û���������-���� �� ���� �̹��� ���ε��ϱ�
	private ArrayList<String> uploadModFile(MultipartHttpServletRequest multipartRequest) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);
				File file = new File(monApply_REPO + "\\" + fileName);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // ��λ� ������ �������� ���� ���
						file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
						mFile.transferTo(new File(monApply_REPO + "\\" + "temp" + "\\" + originalFileName)); // �ӽ÷�
					}
				}
			} else {
				fileList.add(null);
			}
		}
		return fileList;
	}

	// ����������-��û�����ϱ�
	@RequestMapping(value = "/mypage/modRentApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modRentApply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewRentMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRentMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRentMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewRentMap.put(name, value);
			}
		}

		ViewRentMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewRentMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModRentFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewRentMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewRentMap.get("pre_img_num"));

		List<ApplyRentFileVO> rentFileList = new ArrayList<ApplyRentFileVO>();
		List<ApplyRentFileVO> modAddrentFileList = new ArrayList<ApplyRentFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewRentMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyRentFileVO applyRentFileVO = new ApplyRentFileVO();

				if (i < pre_img_num) {
					applyRentFileVO.setUp_filename(up_fileName);
					applyRentFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					rentFileList.add(applyRentFileVO);
					ViewRentMap.put("rentFileList", rentFileList);
				} else {
					applyRentFileVO.setUp_filename(up_fileName);
					rentFileList.add(applyRentFileVO);
					ViewRentMap.put("rentFileList", rentFileList);
				}
			}
		}

		String rent_no = (String) ViewRentMap.get("rent_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modRentApply(ViewRentMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(rentApply_REPO + "\\" + rent_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewRentMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(rentApply_REPO + "\\" + rent_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(rentApply_REPO + "\\" + rent_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRentApply.do?rent_no="
					+ rent_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRentApply.do?rent_no="
					+ rent_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ����������-���� �� ���� �̹��� ���ε��ϱ�
	private ArrayList<String> uploadModRentFile(MultipartHttpServletRequest multipartRequest) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);
				File file = new File(rentApply_REPO + "\\" + fileName);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // ��λ� ������ �������� ���� ���
						file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
						mFile.transferTo(new File(rentApply_REPO + "\\" + "temp" + "\\" + originalFileName)); // �ӽ÷�
					}
				}
			} else {
				fileList.add(null);
			}
		}
		return fileList;
	}

	// ������ȯ������-��û�����ϱ�
	@RequestMapping(value = "/mypage/modRetApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modRetApply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewRetMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRetMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRetMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewRetMap.put(name, value);
			}
		}

		ViewRetMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewRetMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModRetFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewRetMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewRetMap.get("pre_img_num"));

		List<ApplyRentReturnFileVO> retFileList = new ArrayList<ApplyRentReturnFileVO>();
		List<ApplyRentReturnFileVO> modAddretFileList = new ArrayList<ApplyRentReturnFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewRetMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyRentReturnFileVO applyRentReturnFileVO = new ApplyRentReturnFileVO();

				if (i < pre_img_num) {
					applyRentReturnFileVO.setUp_filename(up_fileName);
					applyRentReturnFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					retFileList.add(applyRentReturnFileVO);
					ViewRetMap.put("retFileList", retFileList);
				} else {
					applyRentReturnFileVO.setUp_filename(up_fileName);
					retFileList.add(applyRentReturnFileVO);
					ViewRetMap.put("retFileList", retFileList);
				}
			}
		}

		String ret_no = (String) ViewRetMap.get("ret_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modRetApply(ViewRetMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(retApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(retApply_REPO + "\\" + ret_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewRetMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(retApply_REPO + "\\" + ret_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(retApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(retApply_REPO + "\\" + ret_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRetApply.do?ret_no="
					+ ret_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(retApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRetApply.do?ret_no="
					+ ret_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ������ȯ������-���� �� ���� �̹��� ���ε��ϱ�
	private ArrayList<String> uploadModRetFile(MultipartHttpServletRequest multipartRequest) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);
				File file = new File(retApply_REPO + "\\" + fileName);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // ��λ� ������ �������� ���� ���
						file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
						mFile.transferTo(new File(retApply_REPO + "\\" + "temp" + "\\" + originalFileName)); // �ӽ÷�
					}
				}
			} else {
				fileList.add(null);
			}
		}
		return fileList;
	}

	// ��ȯ��ȥ�κ�-��û�����ϱ�
	@RequestMapping(value = "/mypage/modBackApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modBackApply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewBackMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewBackMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewBackMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewBackMap.put(name, value);
			}
		}

		ViewBackMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewBackMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModBackFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewBackMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewBackMap.get("pre_img_num"));

		List<ApplyBackFileVO> backFileList = new ArrayList<ApplyBackFileVO>();
		List<ApplyBackFileVO> modAddbackFileList = new ArrayList<ApplyBackFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewBackMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyBackFileVO applyBackFileVO = new ApplyBackFileVO();

				if (i < pre_img_num) {
					applyBackFileVO.setUp_filename(up_fileName);
					applyBackFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					backFileList.add(applyBackFileVO);
					ViewBackMap.put("backFileList", backFileList);
				} else {
					applyBackFileVO.setUp_filename(up_fileName);
					backFileList.add(applyBackFileVO);
					ViewBackMap.put("backFileList", backFileList);
				}
			}
		}

		String ba_no = (String) ViewBackMap.get("ba_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modBackApply(ViewBackMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(backApply_REPO + "\\" + ba_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewBackMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(backApply_REPO + "\\" + ba_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(backApply_REPO + "\\" + ba_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewBackApply.do?ba_no="
					+ ba_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewBackApply.do?ba_no="
					+ ba_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ��ȯ��ȥ�κ�-���� �� ���� �̹��� ���ε��ϱ�
	private ArrayList<String> uploadModBackFile(MultipartHttpServletRequest multipartRequest) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);
				File file = new File(backApply_REPO + "\\" + fileName);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // ��λ� ������ �������� ���� ���
						file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
						mFile.transferTo(new File(backApply_REPO + "\\" + "temp" + "\\" + originalFileName)); // �ӽ÷�
					}
				}
			} else {
				fileList.add(null);
			}
		}
		return fileList;
	}

	// �������-��û�����ϱ�
	@RequestMapping(value = "/mypage/modShareApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modShareApply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewMonthMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewMonthMap.put(name, value);
			}
		}

		ViewMonthMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewMonthMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModShareFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewMonthMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewMonthMap.get("pre_img_num"));

		List<ApplyShareFileVO> shareFileList = new ArrayList<ApplyShareFileVO>();
		List<ApplyShareFileVO> modAddshareFileList = new ArrayList<ApplyShareFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewMonthMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyShareFileVO applyShareFileVO = new ApplyShareFileVO();

				if (i < pre_img_num) {
					applyShareFileVO.setUp_filename(up_fileName);
					applyShareFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					shareFileList.add(applyShareFileVO);
					ViewMonthMap.put("shareFileList", shareFileList);
				} else {
					applyShareFileVO.setUp_filename(up_fileName);
					shareFileList.add(applyShareFileVO);
					ViewMonthMap.put("shareFileList", shareFileList);
				}
			}
		}

		String sh_no = (String) ViewMonthMap.get("sh_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modShareApply(ViewMonthMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(shareApply_REPO + "\\" + sh_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewMonthMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(shareApply_REPO + "\\" + sh_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(shareApply_REPO + "\\" + sh_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewShareApply.do?sh_no="
					+ sh_no + "';";
			message += " </script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewShareApply.do?sh_no="
					+ sh_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// �������-���� �� ���� �̹��� ���ε��ϱ�
	private ArrayList<String> uploadModShareFile(MultipartHttpServletRequest multipartRequest) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);
				File file = new File(shareApply_REPO + "\\" + fileName);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // ��λ� ������ �������� ���� ���
						file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
						mFile.transferTo(new File(shareApply_REPO + "\\" + "temp" + "\\" + originalFileName)); // �ӽ÷�
					}
				}
			} else {
				fileList.add(null);
			}
		}
		return fileList;
	}

	// ��û �����ϱ�
	@Override
	@RequestMapping(value = "/mypage/removeMonthApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeMonthApply(@RequestParam("mo_no") int mo_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			mypageService.removeApply(mo_no);
			File destDir = new File(monApply_REPO + "\\" + mo_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('��û�� ����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/monthApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/monthApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value = "/mypage/removeRentApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeRentApply(@RequestParam("rent_no") int rent_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			mypageService.removeRentApply(rent_no);
			File destDir = new File(rentApply_REPO + "\\" + rent_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('��û�� ����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/rentApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/rentApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value = "/mypage/removeRetApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeRetApply(@RequestParam("ret_no") int ret_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			mypageService.removeRetApply(ret_no);
			File destDir = new File(retApply_REPO   + "\\" + ret_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('��û�� ����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/rentApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/rentApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value = "/mypage/removeBackApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeBackApply(@RequestParam("ba_no") int ba_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			mypageService.removeBackApply(ba_no);
			File destDir = new File(backApply_REPO + "\\" + ba_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('��û�� ����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/rentApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/rentApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value = "/mypage/removeShareApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeShareApply(@RequestParam("sh_no") int sh_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			mypageService.removeShareApply(sh_no);
			File destDir = new File(shareApply_REPO  + "\\" + sh_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('��û�� ����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/shareApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/shareApplyList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// ���� �Խñ� �� ���
	// �۸�� ������ ���
	@Override
	@RequestMapping(value = "/mypage/myBoardList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView myBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		List myBoardList = mypageService.selectMyBoardList(member_id);

		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("myBoardList", myBoardList);

		return mav;
	}

	// û��������� �����û�ϱ�
	@RequestMapping(value = "/mypage/updateAlarmApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateAlarmApply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewMonthMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewMonthMap.put(name, value);
			}
		}

		ViewMonthMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewMonthMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewMonthMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewMonthMap.get("pre_img_num"));

		List<ApplyMonFileVO> monthFileList = new ArrayList<ApplyMonFileVO>();
		List<ApplyMonFileVO> modAddimageFileList = new ArrayList<ApplyMonFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewMonthMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyMonFileVO applyMonFileVO = new ApplyMonFileVO();

				if (i < pre_img_num) {
					applyMonFileVO.setUp_filename(up_fileName);
					applyMonFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					monthFileList.add(applyMonFileVO);
					ViewMonthMap.put("monthFileList", monthFileList);
				} else {
					applyMonFileVO.setUp_filename(up_fileName);
					monthFileList.add(applyMonFileVO);
					ViewMonthMap.put("monthFileList", monthFileList);
				}
			}
		}

		String mo_no = (String) ViewMonthMap.get("mo_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modApply(ViewMonthMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(monApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(monApply_REPO + "\\" + mo_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewMonthMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(monApply_REPO + "\\" + mo_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(monApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(monApply_REPO + "\\" + mo_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewMonthApply.do?mo_no="
					+ mo_no + "';";
			message += " </script>";
			// ������Ʈ List? Map?�� �˸��Խ������� �������ִ� service �޼���
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(monApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewMonthApply.do?mo_no="
					+ mo_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ��������������-�����û�ϱ�
	@RequestMapping(value = "/mypage/updateAlarmRentApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateAlarmRentApply(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewRentMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRentMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRentMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewRentMap.put(name, value);
			}
		}

		ViewRentMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewRentMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModRentFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewRentMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewRentMap.get("pre_img_num"));

		List<ApplyRentFileVO> rentFileList = new ArrayList<ApplyRentFileVO>();
		List<ApplyRentFileVO> modAddrentFileList = new ArrayList<ApplyRentFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewRentMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyRentFileVO applyRentFileVO = new ApplyRentFileVO();

				if (i < pre_img_num) {
					applyRentFileVO.setUp_filename(up_fileName);
					applyRentFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					rentFileList.add(applyRentFileVO);
					ViewRentMap.put("rentFileList", rentFileList);
				} else {
					applyRentFileVO.setUp_filename(up_fileName);
					rentFileList.add(applyRentFileVO);
					ViewRentMap.put("rentFileList", rentFileList);
				}
			}
		}

		String rent_no = (String) ViewRentMap.get("rent_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modRentApply(ViewRentMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(rentApply_REPO + "\\" + rent_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewRentMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(rentApply_REPO + "\\" + rent_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(rentApply_REPO + "\\" + rent_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRentApply.do?rent_no="
					+ rent_no + "';";
			message += " </script>";
			// ������Ʈ List? Map?�� �˸��Խ������� �������ִ� service �޼���
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(rentApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRentApply.do?rent_no="
					+ rent_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ��������������-�����û�ϱ�
	@RequestMapping(value = "/mypage/updateAlarmRetApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateAlarmRetApply(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewRetMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRetMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewRetMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewRetMap.put(name, value);
			}
		}

		ViewRetMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewRetMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModRetFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewRetMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewRetMap.get("pre_img_num"));

		List<ApplyRentReturnFileVO> retFileList = new ArrayList<ApplyRentReturnFileVO>();
		List<ApplyRentReturnFileVO> modAddretFileList = new ArrayList<ApplyRentReturnFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewRetMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyRentReturnFileVO applyRentReturnFileVO = new ApplyRentReturnFileVO();

				if (i < pre_img_num) {
					applyRentReturnFileVO.setUp_filename(up_fileName);
					applyRentReturnFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					retFileList.add(applyRentReturnFileVO);
					ViewRetMap.put("retFileList", retFileList);
				} else {
					applyRentReturnFileVO.setUp_filename(up_fileName);
					retFileList.add(applyRentReturnFileVO);
					ViewRetMap.put("retFileList", retFileList);
				}
			}
		}

		String ret_no = (String) ViewRetMap.get("ret_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modRetApply(ViewRetMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(retApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(retApply_REPO + "\\" + ret_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewRetMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(retApply_REPO + "\\" + ret_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(retApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(retApply_REPO + "\\" + ret_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRetApply.do?ret_no="
					+ ret_no + "';";
			message += " </script>";
			
			
			ViewRetMap.put("fileList", fileList);
			mypageService.modifyAlarm(ViewRetMap);
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(retApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewRetApply.do?ret_no="
					+ ret_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// ��ȯ��ȥ�κ�����-�����û�ϱ�
	@RequestMapping(value = "/mypage/updateAlarmBackApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateAlarmBackApply(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewBackMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewBackMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewBackMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewBackMap.put(name, value);
			}
		}

		ViewBackMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewBackMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModBackFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewBackMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewBackMap.get("pre_img_num"));

		List<ApplyBackFileVO> backFileList = new ArrayList<ApplyBackFileVO>();
		List<ApplyBackFileVO> modAddbackFileList = new ArrayList<ApplyBackFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewBackMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyBackFileVO applyBackFileVO = new ApplyBackFileVO();

				if (i < pre_img_num) {
					applyBackFileVO.setUp_filename(up_fileName);
					applyBackFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					backFileList.add(applyBackFileVO);
					ViewBackMap.put("backFileList", backFileList);
				} else {
					applyBackFileVO.setUp_filename(up_fileName);
					backFileList.add(applyBackFileVO);
					ViewBackMap.put("backFileList", backFileList);
				}
			}
		}

		String ba_no = (String) ViewBackMap.get("ba_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modBackApply(ViewBackMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(backApply_REPO + "\\" + ba_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewBackMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(backApply_REPO + "\\" + ba_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(backApply_REPO + "\\" + ba_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewBackApply.do?ba_no="
					+ ba_no + "';";
			message += " </script>";
			// ������Ʈ List? Map?�� �˸��Խ������� �������ִ� service �޼���
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(backApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewBackApply.do?ba_no="
					+ ba_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// �����������-�����û�ϱ�
	@RequestMapping(value = "/mypage/updateAlarmShareApply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateAlarmShareApply(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();

		Map<String, Object> ViewMonthMap = new HashMap<String, Object>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("up_fileno")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				ViewMonthMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				ViewMonthMap.put(name, value);
			}
		}

		ViewMonthMap.put("member_id", member_id);

		memberVO = (MemberVO) mypageService.modifyMember(ViewMonthMap);
		session.removeAttribute("member");
		session.setAttribute("member", memberVO);

		List<String> fileList = uploadModShareFile(multipartRequest);// ������ �̹��� ������ ���ε��Ѵ�.
		int added_img_num = Integer.parseInt((String) ViewMonthMap.get("added_img_num"));
		int pre_img_num = Integer.parseInt((String) ViewMonthMap.get("pre_img_num"));

		List<ApplyShareFileVO> shareFileList = new ArrayList<ApplyShareFileVO>();
		List<ApplyShareFileVO> modAddshareFileList = new ArrayList<ApplyShareFileVO>();
		if (fileList != null && fileList.size() != 0) {
			String[] up_fileNO = (String[]) ViewMonthMap.get("up_fileno");

			for (int i = 0; i < added_img_num; i++) {
				String up_fileName = fileList.get(i);

				ApplyShareFileVO applyShareFileVO = new ApplyShareFileVO();

				if (i < pre_img_num) {
					applyShareFileVO.setUp_filename(up_fileName);
					applyShareFileVO.setUp_fileno(Integer.parseInt(up_fileNO[i]));
					shareFileList.add(applyShareFileVO);
					ViewMonthMap.put("shareFileList", shareFileList);
				} else {
					applyShareFileVO.setUp_filename(up_fileName);
					shareFileList.add(applyShareFileVO);
					ViewMonthMap.put("shareFileList", shareFileList);
				}
			}
		}

		String sh_no = (String) ViewMonthMap.get("sh_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			mypageService.modShareApply(ViewMonthMap);
			if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					String up_fileName = fileList.get(i);
					if (i < pre_img_num) {
						if (up_fileName != null) {
							File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(shareApply_REPO + "\\" + sh_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);

							String[] oldName = (String[]) ViewMonthMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(shareApply_REPO + "\\" + sh_no + "\\" + oldFileName);
							oldFile.delete();

						}
					} else {
						if (up_fileName != null) {
							File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + up_fileName);
							File destDir = new File(shareApply_REPO + "\\" + sh_no);
							FileUtils.moveFileToDirectory(srcFile, destDir, true);
						}
					}
				}
			}
			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewShareApply.do?sh_no="
					+ sh_no + "';";
			message += " </script>";
			// ������Ʈ List? Map?�� �˸��Խ������� �������ִ� service �޼���
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + fileList.get(i));
					srcFile.delete();
				}
				e.printStackTrace();
			}
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/viewShareApply.do?sh_no="
					+ sh_no + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
	}

	// �������������� �����ϱ�
	@Override
	@RequestMapping(value = "/mypage/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("fr_NO") int fr_NO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardFreeService.removeArticle(fr_NO);

			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/mypage/myBoardList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
			message += " location.href='" + request.getContextPath() + "/mypage/myBoardList.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}
