package com.onestop.GJ.admin.board.notice.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.admin.board.notice.service.AdminNoticeService;
import com.onestop.GJ.admin.board.notice.vo.AdminNoticeImageVO;
import com.onestop.GJ.admin.board.notice.vo.AdminNoticeVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
import com.onestop.GJ.member.vo.MemberVO;



@Controller("adminNoticeController")
public class AdminNoticeControllerImpl implements AdminNoticeController {
	private static String ARTICLE_IMAGE_REPO = "C:\\GJ\\file_repo\\board\\notice";
	
	 @Autowired
	 AdminNoticeService boardService;
	 @Autowired
	 AdminNoticeVO boardNoticeVO;

	 // 공지사항 관리 게시글 목록 
	 @Override
	   @RequestMapping(value = { "/adminNotice/listArticles.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	   public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String _section = request.getParameter("section");
	      String _pageNum = request.getParameter("pageNum");
	      int section = Integer.parseInt(((_section == null) ? "1" : _section));
	      int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
	      Map pagingMap = new HashMap();
	      pagingMap.put("section", section);
	      pagingMap.put("pageNum", pageNum);
	      Map articlesMap = boardService.listArticles(pagingMap);

	      articlesMap.put("section", section);
	      articlesMap.put("pageNum", pageNum);

	      String viewName = (String) request.getAttribute("viewName");
	      ModelAndView mav = new ModelAndView(viewName);
	      mav.addObject("articlesMap", articlesMap);
	      return mav;

	   }

	// 공지사항 관리 게시글 검색 목록 
	   @Override
	   @RequestMapping(value = "/adminNotice/searchBoardList.do", method = RequestMethod.GET)
	   public ModelAndView searchBoardList(@RequestParam("searchWord") String searchWord, 
			   @RequestParam("searchType_notice") String searchType_notice,
			   HttpServletRequest request,HttpServletResponse response) throws Exception {
	      response.setContentType("text/html;charset=utf-8");
	      response.setCharacterEncoding("utf-8");

	      String _section = request.getParameter("section");
	      String _pageNum = request.getParameter("pageNum");
	      int section = Integer.parseInt(((_section == null) ? "1" : _section));
	      int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
	      Map pagingMap = new HashMap();
	      pagingMap.put("section", section);
	      pagingMap.put("pageNum", pageNum);
	      pagingMap.put("searchWord", searchWord);
	      pagingMap.put("searchType_notice", searchType_notice);
	      Map articlesMap = boardService.searchBoardList(pagingMap);

	      articlesMap.put("section", section);
	      articlesMap.put("pageNum", pageNum);
	      articlesMap.put("searchWord", searchWord);
	      articlesMap.put("searchType_notice", searchType_notice);

	      String viewName = (String) request.getAttribute("viewName");
	      ModelAndView mav = new ModelAndView(viewName);
	      mav.addObject("articlesMap", articlesMap);
	      return mav;
	   }

	// 공지사항  관리 게시글 추가
	   @Override
	   @RequestMapping(value = "/adminNotice/addNewArticle.do", method = RequestMethod.POST)
	   @ResponseBody
	   public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
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

	      // 로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다.
	      HttpSession session = multipartRequest.getSession();
	      MemberVO memberVO = (MemberVO) session.getAttribute("member");
	      String member_id = memberVO.getMember_id();
	      articleMap.put("member_id", member_id);

	      List<String> fileList = upload(multipartRequest, RequestMethod.POST);
	      List<AdminNoticeImageVO> imageFileList = new ArrayList<AdminNoticeImageVO>();
	      if (fileList != null && fileList.size() != 0) {
	         for (String fileName : fileList) {
	        	 AdminNoticeImageVO adminNoticeImageVO = new AdminNoticeImageVO();
	        	 adminNoticeImageVO.setUp_fileName(fileName);
	            imageFileList.add(adminNoticeImageVO);
	         }
	         articleMap.put("imageFileList", imageFileList);
	      }

	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");

	      try {
	         int noti_NO = boardService.addNewArticle(articleMap);
	         if (imageFileList != null && imageFileList.size() != 0) {
	            for (AdminNoticeImageVO adminNoticeImageVO : imageFileList) {
	               up_fileName = adminNoticeImageVO.getUp_fileName();
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	               File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
	               FileUtils.moveFileToDirectory(srcFile, destDir, true);
	            }
	         }

	         message = "<script>";
	         message += " alert('새글을 추가했습니다.');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminNotice/listArticles.do'; ";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

	      } catch (Exception e) {
	         if (imageFileList != null && imageFileList.size() != 0) {
	            for (AdminNoticeImageVO adminNoticeImageVO : imageFileList) {
	               up_fileName = adminNoticeImageVO.getUp_fileName();
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	               srcFile.delete();
	            }
	         }

	         message = " <script>";
	         message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminNotice/articleForm.do'; ";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	         e.printStackTrace();
	      }
	      return resEnt;
	   }

	// 공지사항 관리 게시글 첨부파일 업로드 
	   private List<String> upload(MultipartHttpServletRequest multipartRequest, RequestMethod post) throws Exception {
	      List<String> fileList = new ArrayList<String>();
	      Iterator<String> fileNames = multipartRequest.getFileNames();
	      while (fileNames.hasNext()) {
	         String fileName = fileNames.next();
	         MultipartFile mFile = multipartRequest.getFile(fileName);
	         String originalFileName = mFile.getOriginalFilename();
	         fileList.add(originalFileName);
	         File file = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
	         if (mFile.getSize() != 0) { // File Null Check
	            if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
	               file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들을 생성
	               mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName));
	            }
	         }
	      }
	      return fileList;
	   }

	// 공지사항 관리 게시글 상세화면 
	   @RequestMapping(value = "/adminNotice/viewArticle.do", method = RequestMethod.GET)
	   public ModelAndView viewArticle(@RequestParam("noti_NO") int noti_NO,
	         @RequestParam(value="removeCompleted", required=false) String removeCompleted,
	         HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String viewName = (String) request.getAttribute("viewName");
	      Map articleMap = boardService.viewArticle(noti_NO);
	      articleMap.put("removeCompleted", removeCompleted );
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName(viewName);
	      mav.addObject("articleMap", articleMap);
	      return mav;
	   }

	// 공지사항 관리 게시글 삭제
	   @Override
	   @RequestMapping(value = "/adminNotice/removeArticle.do", method = RequestMethod.POST)
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
	         message += " alert('글을 삭제했습니다.');";
	         message += " location.href='" + request.getContextPath() + "/adminNotice/listArticles.do';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      } catch (Exception e) {
	         message = "<script>";
	         message += " alert('오류가 발생했습니다.다시 시도해주세요');";
	         message += " location.href='" + request.getContextPath() + "/adminNotice/listArticles.do';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	         e.printStackTrace();
	      }
	      return resEnt;
	   }

	// 공지사항 관리 게시글 수정 
	   @RequestMapping(value = "/adminNotice/modArticle.do", method = RequestMethod.POST)
	   @ResponseBody
	   public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
	         throws Exception {
	      multipartRequest.setCharacterEncoding("utf-8");
	      Map<String, Object> articleMap = new HashMap<String, Object>();
	     
	      Enumeration enu = multipartRequest.getParameterNames();
	      while (enu.hasMoreElements()) {
	         String name = (String) enu.nextElement();

	         if (name.equals("up_fileNO")) {
	            String[] values = multipartRequest.getParameterValues(name);
	            articleMap.put(name, values);
	         } else if (name.equals("oldFileName")) {
	            String[] values = multipartRequest.getParameterValues(name);
	            articleMap.put(name, values);
	         } else {
	            String value = multipartRequest.getParameter(name);
	            articleMap.put(name, value);
	         }
	      }

	      List<String> fileList = uploadModImageFile(multipartRequest);//수정한 이미지 파일을 업로드한다.
	      int added_img_num = Integer.parseInt((String) articleMap.get("added_img_num"));
	      int pre_img_num = Integer.parseInt((String) articleMap.get("pre_img_num"));
	      List<AdminNoticeImageVO> imageFileList = new ArrayList<AdminNoticeImageVO>();
	      List<AdminNoticeImageVO> modAddimageFileList = new ArrayList<AdminNoticeImageVO>();
	      if (fileList != null && fileList.size() != 0) {
	         String[] up_fileNO = (String[]) articleMap.get("up_fileNO");
	         
	         for (int i = 0; i < added_img_num; i++) {
	            String up_fileName = fileList.get(i);
	            
	            AdminNoticeImageVO adminNoticeImageVO = new AdminNoticeImageVO();
	            if (i < pre_img_num) {
	            	adminNoticeImageVO.setUp_fileName(up_fileName);
	            	adminNoticeImageVO.setUp_fileNO(Integer.parseInt(up_fileNO[i]));
	               imageFileList.add(adminNoticeImageVO);
	               articleMap.put("imageFileList", imageFileList);
	            } else {
	            	adminNoticeImageVO.setUp_fileName(up_fileName);
	               modAddimageFileList.add(adminNoticeImageVO);
	               articleMap.put("modAddimageFileList", modAddimageFileList);
	            }
	         }
	      }

	      String noti_NO = (String) articleMap.get("noti_NO");
	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	      try {
	         boardService.modArticle(articleMap);
	         if (fileList != null && fileList.size() != 0) { // 수정한 파일들을 차례대로 업로드한다.
					for (int i = 0; i < fileList.size(); i++) {
						String up_fileName = fileList.get(i);
	               if (i < pre_img_num) {
	                  if (up_fileName != null) {
	                     File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	                     File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
	                     FileUtils.moveFileToDirectory(srcFile, destDir, true);

	                     String[] oldName = (String[]) articleMap.get("oldFileName");
	                     String oldFileName = oldName[i];

	                     File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO + "\\" + oldFileName);
	                     oldFile.delete();
	                  }
	               } else {
	                  if (up_fileName != null) {
	                     File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	                     File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
	                     FileUtils.moveFileToDirectory(srcFile, destDir, true);
	                  }
	               }
	            }
	         }
	         message = "<script>";
	         message += " alert('글을 수정했습니다.');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminNotice/viewArticle.do?noti_NO="
	               + noti_NO + "';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      } catch (Exception e) {
	         if (fileList != null && fileList.size() != 0) { // 오류 발생 시 temp 폴더에 업로드된 이미지 파일들을 삭제한다.
	            for (int i = 0; i < fileList.size(); i++) {
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + fileList.get(i));
	               srcFile.delete();
	            }
	            e.printStackTrace();
	         }
	         message = "<script>";
	         message += " alert('오류가 발생했습니다.다시 수정해주세요');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminNotice/viewArticle.do?noti_NO="
	               + noti_NO + "';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      }
	      return resEnt;
	   }
	   
	// 공지사항 관리 게시글 수정(첨부파일 삭제)
	      @RequestMapping(value = "/adminNotice/removeModImage.do", method = RequestMethod.POST)
	      @ResponseBody
	      public void removeModImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
	         request.setCharacterEncoding("utf-8");
	         response.setContentType("text/html; charset=utf-8");
	         PrintWriter writer = response.getWriter();

	         try {
	            String up_fileNO = (String) request.getParameter("up_fileNO");
	            String up_fileName = (String) request.getParameter("up_fileName");
	            String noti_NO = (String) request.getParameter("noti_NO");

	            AdminNoticeImageVO adminNoticeImageVO = new AdminNoticeImageVO();
	            adminNoticeImageVO.setNoti_NO(Integer.parseInt(noti_NO));
	            adminNoticeImageVO.setUp_fileNO(Integer.parseInt(up_fileNO));
	            boardService.removeModImage(adminNoticeImageVO);
	            
	            File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO + "\\" + up_fileName);
	            oldFile.delete();
	            
	            writer.print("success");
	         } catch (Exception e) {
	            writer.print("failed");
	         }

	      }
			
	   // 공지사항 관리 게시글 수정(첨부파일 수정)
			private ArrayList<String> uploadModImageFile(MultipartHttpServletRequest multipartRequest) throws Exception {
				ArrayList<String> fileList = new ArrayList<String>();
				Iterator<String> fileNames = multipartRequest.getFileNames();
				while (fileNames.hasNext()) {
					String fileName = fileNames.next();
					MultipartFile mFile = multipartRequest.getFile(fileName);
					String originalFileName = mFile.getOriginalFilename();
					if (originalFileName != "" && originalFileName != null) {
						fileList.add(originalFileName);
						File file = new File(ARTICLE_IMAGE_REPO + "\\" + fileName);
						if (mFile.getSize() != 0) { // File Null Check
							if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
								file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들을 생성
								mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName)); // 임시로
							}
						}
					} else {
						fileList.add(null);
					}

				}
				return fileList;
			}
			
	   
}
