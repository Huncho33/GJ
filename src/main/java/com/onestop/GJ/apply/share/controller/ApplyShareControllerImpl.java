package com.onestop.GJ.apply.share.controller;

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

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.share.service.ApplyShareService;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.service.MypageService;

@Controller("ApplyShareControllerImpl")
public class ApplyShareControllerImpl implements ApplyShareController {
	private static String shareApply_REPO = "C:\\GJ\\file_repo\\apply\\share";
	@Autowired
	private ApplyShareService applyShareService;
	@Autowired
	private MypageService mypageService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ApplyShareVO applyShareVO;

	// 신청페이지 호출(신청자격조건화면)
	@RequestMapping(value = "/share/shareApplyForm1.do", method = {RequestMethod.GET,RequestMethod.POST})
	private ModelAndView applyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("viewName" + viewName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;

	}

	// 신청정보 등록(수정)
	@Override
	@RequestMapping(value = "/share/shareApplyForm2.do", method = RequestMethod.POST)
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

	// 신청페이지 화면 호출(monthApplyForm3)
	@RequestMapping(value = "/share/shareApplyForm3.do", method = RequestMethod.POST)
	private ModelAndView applyForms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("viewName" + viewName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/share/shareApplyForm4.do");

		return mav;
	}

	
	// 신청등록
	@Override
	@RequestMapping(value = "/share/shareApplyForm4.do", method = RequestMethod.POST)
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
	      System.out.println("아티클맵 : "+ articleMap);

	      List<String> fileList = upload(multipartRequest, RequestMethod.POST);
	      List<ApplyShareFileVO> imageFileList = new ArrayList<ApplyShareFileVO>();
	      if (fileList != null && fileList.size() != 0) {
	         for (String fileName : fileList) {
	        	 ApplyShareFileVO applyShareFileVO = new ApplyShareFileVO();
	        	 applyShareFileVO.setUp_filename(fileName);
	            imageFileList.add(applyShareFileVO);
	         }
	         articleMap.put("imageFileList", imageFileList);
	      }
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			 int sh_no = applyShareService.addResult(articleMap);
			 if (imageFileList != null && imageFileList.size() != 0) {
		            for (ApplyShareFileVO applyShareFileVO : imageFileList) {
		               up_fileName = applyShareFileVO.getUp_filename();
		               System.out.println("apply : " + applyShareFileVO.toString());
		               File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + up_fileName);
		               File destDir = new File(shareApply_REPO + "\\" + sh_no);
		               // destDir.mkdirs();
		               FileUtils.moveFileToDirectory(srcFile, destDir, true);
		            }
		         }

		         message = "<script>";
		         message += " alert('신청이 완료되었습니다..');";
		         message += " location.href='" + multipartRequest.getContextPath() + "/share/shareApplyResult.do?sh_no="+sh_no+"';";
		         message += " </script>";
		         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		         System.out.println("신청번호:"+sh_no);

		      } catch (Exception e) {
		         if (imageFileList != null && imageFileList.size() != 0) {
		            for (ApplyShareFileVO applyShareFileVO : imageFileList) {
		               up_fileName = applyShareFileVO.getUp_filename();
		               File srcFile = new File(shareApply_REPO + "\\" + "temp" + "\\" + up_fileName);
		               srcFile.delete();
		            }
		         }

		         message = " <script>";
		         message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
		         message += " location.href='" + multipartRequest.getContextPath() + "/share/shareApplyForm3.do'; ";
		         message += " </script>";
		         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		         e.printStackTrace();
		      }
		      return resEnt;
		      //
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
	         File file = new File(shareApply_REPO + "\\" + "temp" + "\\" + fileName);
	         if (mFile.getSize() != 0) { // File Null Check
	            if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
	               file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들을 생성
	               mFile.transferTo(new File(shareApply_REPO + "\\" + "temp" + "\\" + originalFileName));
	            }
	         }
	      }
	      return fileList;
	   }
	
	
	
	// 결과페이지
		@RequestMapping(value = "/share/shareApplyResult.do")
		public ModelAndView findAll(HttpServletRequest request, @RequestParam("sh_no") int sh_no ) {
			String viewName = (String) request.getAttribute("viewName");
			System.out.println("sh_no : "+sh_no);
			System.out.println("viewName" + viewName);
			ModelAndView mav = new ModelAndView();
			System.out.println("1");
			HttpSession session = request.getSession();
			memberVO = (MemberVO) session.getAttribute("member");
			System.out.println(memberVO.getMember_id());
			String id = memberVO.getMember_id();
			int no = applyShareVO.getSh_no();
			
//			List list = applymonService.findAll(id);
			
			ApplyShareVO applyno = applyShareService.findNo(sh_no);
//			System.out.println(list);

//			mav.addObject("apply", list);
			mav.addObject("no", applyno);
			mav.setViewName(viewName);
			return mav;
		}
		
		

		

}