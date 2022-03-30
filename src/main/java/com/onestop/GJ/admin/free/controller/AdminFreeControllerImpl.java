package com.onestop.GJ.admin.free.controller;

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

import com.onestop.GJ.admin.free.service.AdminFreeService;
import com.onestop.GJ.admin.free.vo.AdminFreeImageVO;
import com.onestop.GJ.admin.free.vo.AdminFreeVO;
import com.onestop.GJ.member.vo.MemberVO;


@Controller("adminFreeController")
public class AdminFreeControllerImpl implements AdminFreeController{
	private static String ARTICLE_IMAGE_REPO = "C:\\GJ\\file_repo\\board\\free";
	   @Autowired
	   AdminFreeService boardService;
	   @Autowired
	   AdminFreeVO adminFreeVO;
	   
	   @Override
	   @RequestMapping(value = { "/adminFree/listArticles.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	   public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {

	      String _section = request.getParameter("section");
	      String _pageNum = request.getParameter("pageNum");
	      int section = Integer.parseInt(((_section == null) ? "1" : _section));
	      int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
	      Map pagingMap = new HashMap();
	      pagingMap.put("section", section);
	      pagingMap.put("pageNum", pageNum);
	      System.out.println("hello");
	      Map articlesMap = boardService.listArticles(pagingMap);

	      articlesMap.put("section", section);
	      articlesMap.put("pageNum", pageNum);

	      String viewName = (String) request.getAttribute("viewName");
	      ModelAndView mav = new ModelAndView(viewName);
	      mav.addObject("articlesMap", articlesMap);
	      return mav;

	   }
	   
	// �˻�â
	   @Override
	   @RequestMapping(value = "/adminFree/searchBoardList.do", method = RequestMethod.GET)
	   public ModelAndView searchBoardList(@RequestParam("searchWord") String searchWord, HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
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
	      Map articlesMap = boardService.searchBoardList(pagingMap);

	      articlesMap.put("section", section);
	      articlesMap.put("pageNum", pageNum);
	      articlesMap.put("searchWord", searchWord);

	      String viewName = (String) request.getAttribute("viewName");
	      ModelAndView mav = new ModelAndView(viewName);
	      mav.addObject("articlesMap", articlesMap);
	      return mav;
	   }
	   
	// ���� �̹��� �۾���
	   @Override
	   @RequestMapping(value = "/adminFree/addNewArticle.do", method = RequestMethod.POST)
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

	      // �α��� �� ���ǿ� ����� ȸ�� �������� �۾��� ���̵� ���ͼ� Map�� �����մϴ�.
	      HttpSession session = multipartRequest.getSession();
	      MemberVO memberVO = (MemberVO) session.getAttribute("member");
	      String member_id = memberVO.getMember_id();
	      articleMap.put("member_id", member_id);

	      List<String> fileList = upload(multipartRequest, RequestMethod.POST);
	      List<AdminFreeImageVO> imageFileList = new ArrayList<AdminFreeImageVO>();
	      if (fileList != null && fileList.size() != 0) {
	         for (String fileName : fileList) {
	        	 AdminFreeImageVO adminFreeImageVO = new AdminFreeImageVO();
	        	 adminFreeImageVO.setUp_fileName(fileName);
	            imageFileList.add(adminFreeImageVO);
	         }
	         articleMap.put("imageFileList", imageFileList);
	      }

	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");

	      try {
	         int fr_NO = boardService.addNewArticle(articleMap);
	         if (imageFileList != null && imageFileList.size() != 0) {
	            for (AdminFreeImageVO adminFreeImageVO : imageFileList) {
	               up_fileName = adminFreeImageVO.getUp_fileName();
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	               File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + fr_NO);
	               // destDir.mkdirs();
	               FileUtils.moveFileToDirectory(srcFile, destDir, true);
	            }
	         }

	         message = "<script>";
	         message += " alert('������ �߰��߽��ϴ�.');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminFree/listArticles.do'; ";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

	      } catch (Exception e) {
	         if (imageFileList != null && imageFileList.size() != 0) {
	            for (AdminFreeImageVO adminFreeImageVO : imageFileList) {
	               up_fileName = adminFreeImageVO.getUp_fileName();
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	               srcFile.delete();
	            }
	         }

	         message = " <script>";
	         message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminFree/articleForm.do'; ";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	         e.printStackTrace();
	      }
	      return resEnt;
	   }
	   // ���� �̹��� ���ε��ϱ�
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
	            if (!file.exists()) { // ��λ� ������ �������� ���� ���
	               file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
	               mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName));
	            }
	         }
	      }
	      return fileList;
	   }
	   
	//   �Խñ� ��
	   @RequestMapping(value = "/adminFree/viewArticle.do", method = RequestMethod.GET)
	   public ModelAndView viewArticle(@RequestParam("fr_NO") int fr_NO,
	         @RequestParam(value="removeCompleted", required=false) String removeCompleted,
	         HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String viewName = (String) request.getAttribute("viewName");
	      Map articleMap = boardService.viewArticle(fr_NO);
	      articleMap.put("removeCompleted", removeCompleted );
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName(viewName);
	      mav.addObject("articleMap", articleMap);
	      System.out.println("articleMap1 : "+ articleMap);
	      return mav;
	   }
	   
	// �� �����ϱ�
	   @Override
	   @RequestMapping(value = "/adminFree/removeArticle.do", method = RequestMethod.POST)
	   @ResponseBody
	   public ResponseEntity removeArticle(@RequestParam("fr_NO") int fr_NO, HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
	      response.setContentType("text/html; charset=utf-8");
	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	      try {
	    	  System.out.println("fr : "+ fr_NO);
	    	  boardService.removeArticle(fr_NO);
	         File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + fr_NO);
	         System.out.println("��������1 : "+ destDir);
	         FileUtils.deleteDirectory(destDir);
	        

	         message = "<script>";
	         message += " alert('���� �����߽��ϴ�.');";
	         message += " location.href='" + request.getContextPath() + "/adminFree/listArticles.do';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      } catch (Exception e) {
	         message = "<script>";
	         message += " alert('������ �߻��߽��ϴ�.�ٽ� �õ����ּ���');";
	         message += " location.href='" + request.getContextPath() + "/adminFree/listArticles.do';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	         e.printStackTrace();
	      }
	      return resEnt;
	   }
	   
	// �� �����ϱ�
	   @RequestMapping(value = "/adminFree/modArticle.do", method = RequestMethod.POST)
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
	      List<String> fileList = uploadModImageFile(multipartRequest);//������ �̹��� ������ ���ε��Ѵ�.
	      int added_img_num = Integer.parseInt((String) articleMap.get("added_img_num"));
	      int pre_img_num = Integer.parseInt((String) articleMap.get("pre_img_num"));
	      System.out.println("added_img_num : "+ added_img_num);
	      System.out.println("pre_img_num : "+ pre_img_num);
	      List<AdminFreeImageVO> imageFileList = new ArrayList<AdminFreeImageVO>();
	      List<AdminFreeImageVO> modAddimageFileList = new ArrayList<AdminFreeImageVO>();
	      if (fileList != null && fileList.size() != 0) {
	         String[] up_fileNO = (String[]) articleMap.get("up_fileNO");
	         
	         for (int i = 0; i < added_img_num; i++) {
	            String up_fileName = fileList.get(i);
	            
	            AdminFreeImageVO adminFreeImageVO = new AdminFreeImageVO();
	            if (i < pre_img_num) {
	            	adminFreeImageVO.setUp_fileName(up_fileName);
	            	adminFreeImageVO.setUp_fileNO(Integer.parseInt(up_fileNO[i]));
	               imageFileList.add(adminFreeImageVO);
	               articleMap.put("imageFileList", imageFileList);
	            } else {
	            	adminFreeImageVO.setUp_fileName(up_fileName);
	               modAddimageFileList.add(adminFreeImageVO);
	               articleMap.put("modAddimageFileList", modAddimageFileList);
	            }
	         }
	      }

	      String fr_NO = (String) articleMap.get("fr_NO");
	      System.out.println("fr_NO1 : "+ fr_NO);
	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	      try {
	         boardService.modArticle(articleMap);
	         if (fileList != null && fileList.size() != 0) { // ������ ���ϵ��� ���ʴ�� ���ε��Ѵ�.
					for (int i = 0; i < fileList.size(); i++) {
						String up_fileName = fileList.get(i);
	               if (i < pre_img_num) {
	                  if (up_fileName != null) {
	                     File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	                     File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + fr_NO);
	                     FileUtils.moveFileToDirectory(srcFile, destDir, true);

	                     String[] oldName = (String[]) articleMap.get("oldFileName");
	                     String oldFileName = oldName[i];

	                     File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + fr_NO + "\\" + oldFileName);
	                     oldFile.delete();
	                  }
	               } else {
	                  if (up_fileName != null) {
	                	  
	                     File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
	                     File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + fr_NO);
	                     FileUtils.moveFileToDirectory(srcFile, destDir, true);
	                  }
	               }
	            }
	         }
	         message = "<script>";
	         message += " alert('���� �����߽��ϴ�.');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminFree/viewArticle.do?fr_NO="
	               + fr_NO + "';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      } catch (Exception e) {
	         if (fileList != null && fileList.size() != 0) { // ���� �߻� �� temp ������ ���ε�� �̹��� ���ϵ��� �����Ѵ�.
	            for (int i = 0; i < fileList.size(); i++) {
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + fileList.get(i));
	               srcFile.delete();
	            }
	            e.printStackTrace();
	         }
	         message = "<script>";
	         message += " alert('������ �߻��߽��ϴ�.�ٽ� �������ּ���');";
	         message += " location.href='" + multipartRequest.getContextPath() + "/adminFree/viewArticle.do?fr_NO="
	               + fr_NO + "';";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      }
	      return resEnt;
	   }
	   
	   
	   // �����ϱ⿡�� �̹��� ���� ���
	      @RequestMapping(value = "/adminFree/removeModImage.do", method = RequestMethod.POST)
	      @ResponseBody
	      public void removeModImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
	         request.setCharacterEncoding("utf-8");
	         response.setContentType("text/html; charset=utf-8");
	         PrintWriter writer = response.getWriter();

	         try {
	            String up_fileNO = (String) request.getParameter("up_fileNO");
	            String up_fileName = (String) request.getParameter("up_fileName");
	            String fr_NO = (String) request.getParameter("fr_NO");

	            
	            System.out.println("up_fileNO = " + up_fileNO);
	            System.out.println("fr_NO = " + fr_NO);

	            AdminFreeImageVO adminFreeImageVO = new AdminFreeImageVO();
	            adminFreeImageVO.setFr_NO(Integer.parseInt(fr_NO));
	            adminFreeImageVO.setUp_fileNO(Integer.parseInt(up_fileNO));
	            boardService.removeModImage(adminFreeImageVO);
	            
	            File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + fr_NO + "\\" + up_fileName);
	            oldFile.delete();
	            
	            writer.print("success");
	         } catch (Exception e) {
	            writer.print("failed");
	         }

	      }
	      
	   // ���� �� ���� �̹��� ���ε��ϱ�
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
							if (!file.exists()) { // ��λ� ������ �������� ���� ���
								file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
								mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName)); // �ӽ÷�
							}
						}
					} else {
						fileList.add(null);
					}

				}
				return fileList;
			}
}
