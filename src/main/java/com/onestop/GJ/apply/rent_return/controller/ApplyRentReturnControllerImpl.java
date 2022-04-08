package com.onestop.GJ.apply.rent_return.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.apply.rent_return.service.ApplyRentReturnService;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnFileVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("ApplyRentReturnControllerImpl")
public class ApplyRentReturnControllerImpl implements ApplyRentReturnController {

	private static String rentReturnApply_REPO = "C:\\GJ\\file_repo\\apply\\rentReturn";
	@Autowired
	private ApplyRentReturnService applyrentReturnService;
	@Autowired
	private MypageService mypageService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ApplyRentReturnVO applyrentReturnVO;
	
	// ��û�� ���̵� �ִ��� üũ
		@RequestMapping(value = "/ret/rentReturnApplyForm0.do", method = { RequestMethod.GET, RequestMethod.POST })
		private ResponseEntity applyCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("utf-8");
			String message = null;
			ResponseEntity resEnt = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-Type", "text/html; charset=utf-8");
			String viewName = (String) request.getAttribute("viewName");
			System.out.println("viewName" + viewName);
			HttpSession session = request.getSession();
			memberVO = (MemberVO) session.getAttribute("member");
			System.out.println("memberVO �� �ҷ�����" + memberVO);
			String id = memberVO.getMember_id();
			ApplyRentReturnVO list = applyrentReturnService.findAll(id);
			System.out.println("apply ���̺� �� : " + list);
			if (list != null && list.getMember_id().equals(id)) {
				System.out.println("���̵� ������: " + list.getMember_id());
				message = "<script>";
				message += " alert('�̹� ��û�� ���̵��Դϴ�.');";
				message += " location.href='" + request.getContextPath() + "/main.do'";
				message += " </script>";
			} else {
				message = "<script>";
				message += " location.href='" + request.getContextPath() + "/ret/rentReturnApplyForm1.do'";
				message += " </script>";
			}
			return resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
	
	
	// ��û������ ȣ��(��û�ڰ�����ȭ��)
	@RequestMapping(value = "/ret/rentReturnApplyForm1.do", method = {RequestMethod.GET,RequestMethod.POST})
	private ModelAndView applyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("viewName" + viewName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;

	}

	// ��û���� ���(����)
	@Override
	@RequestMapping(value = "/ret/rentReturnApplyForm2.do", method = RequestMethod.POST)
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

		System.out.println(memberMap);

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

	// ��û������ ȭ�� ȣ��(rentReturnApplyForm3)
	@RequestMapping(value = "/ret/rentReturnApplyForm3.do", method = RequestMethod.POST)
	private ModelAndView applyForms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("viewName" + viewName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/ret/rentReturnApplyForm4.do");

		return mav;
	}

	
	// ��û���
	@Override
	@RequestMapping(value = "/ret/rentReturnApplyForm4.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity insertResult(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
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
	      System.out.println("��ƼŬ�� : "+ articleMap);

	      List<String> fileList = upload(multipartRequest, RequestMethod.POST);
	      List<ApplyRentReturnFileVO> imageFileList = new ArrayList<ApplyRentReturnFileVO>();
	      fileList.removeAll(Arrays.asList("", null));
	      if (fileList != null && fileList.size() != 0) {
	         for (String fileName : fileList) {
	        	 ApplyRentReturnFileVO applyrentReturnFileVO = new ApplyRentReturnFileVO();
	        	 applyrentReturnFileVO.setUp_filename(fileName);
	            imageFileList.add(applyrentReturnFileVO);
	         }
	         articleMap.put("imageFileList", imageFileList);
	      }
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			 int ret_no = applyrentReturnService.addResult(articleMap);
			 if (imageFileList != null && imageFileList.size() != 0) {
		            for (ApplyRentReturnFileVO applyrentReturnFileVO : imageFileList) {
		               up_fileName = applyrentReturnFileVO.getUp_filename();
		               System.out.println("apply : " + applyrentReturnFileVO.toString());
		               File srcFile = new File(rentReturnApply_REPO + "\\" + "temp" + "\\" + up_fileName);
		               File destDir = new File(rentReturnApply_REPO + "\\" + ret_no);
		               // destDir.mkdirs();
		               FileUtils.moveFileToDirectory(srcFile, destDir, true);
		            }
		         }

		         message = "<script>";
		         message += " alert('��û�� �Ϸ�Ǿ����ϴ�..');";
		         message += " location.href='" + multipartRequest.getContextPath() + "/ret/rentReturnApplyResult.do?ret_no="+ret_no+"';";
		         message += " </script>";
		         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		         System.out.println("��û��ȣ:"+ret_no);

		      } catch (Exception e) {
		         if (imageFileList != null && imageFileList.size() != 0) {
		            for (ApplyRentReturnFileVO applyrentReturnFileVO : imageFileList) {
		               up_fileName = applyrentReturnFileVO.getUp_filename();
		               File srcFile = new File(rentReturnApply_REPO + "\\" + "temp" + "\\" + up_fileName);
		               srcFile.delete();
		            }
		         }

		         message = " <script>";
		         message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');');";
		         message += " location.href='" + multipartRequest.getContextPath() + "/ret/rentReturnApplyForm3.do'; ";
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
	         File file = new File(rentReturnApply_REPO + "\\" + "temp" + "\\" + fileName);
	         if (mFile.getSize() != 0) { // File Null Check
	            if (!file.exists()) { // ��λ� ������ �������� ���� ���
	               file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
	               mFile.transferTo(new File(rentReturnApply_REPO + "\\" + "temp" + "\\" + originalFileName));
	            }
	         }
	      }
	      return fileList;
	   }
	
	
	
	// ���������
		@RequestMapping(value = "/ret/rentReturnApplyResult.do")
		public ModelAndView findAll(HttpServletRequest request, @RequestParam("ret_no") int ret_no ) {
			String viewName = (String) request.getAttribute("viewName");
			System.out.println("ret_no : "+ret_no);
			System.out.println("viewName" + viewName);
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();
			memberVO = (MemberVO) session.getAttribute("member");
			System.out.println(memberVO.getMember_id());
			String id = memberVO.getMember_id();
			int no = applyrentReturnVO.getRet_no();
			
//			List list = applymonService.findAll(id);
			
			ApplyRentReturnVO applyno = applyrentReturnService.findNo(ret_no);
//			System.out.println(list);

//			mav.addObject("apply", list);
			mav.addObject("no", applyno);
			mav.setViewName(viewName);
			return mav;
		}
		
		// �������������Ȯ��
		@RequestMapping(value = "/ret/rentReturnSelectedResult.do")
		private ModelAndView retSelectedPage(HttpServletRequest request) throws Exception {
			String viewName = (String) request.getAttribute("viewName");
			System.out.println("viewName" + viewName);
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();
			memberVO = (MemberVO) session.getAttribute("member");
			System.out.println(memberVO.getMember_id());
			String id = memberVO.getMember_id();
			ApplyRentReturnVO list = applyrentReturnService.findAll(id);
			System.out.println(list);
			mav.addObject("apply", list);
			mav.setViewName(viewName);
//			if (list != null && list.getMember_id().equals(id)) {
	//
//			}
			if(list==null ) {
				mav.addObject("message", "��û������ ���� ���̵� �Դϴ�.");
			}
			else {
			}

			return mav;
		}
		

}
