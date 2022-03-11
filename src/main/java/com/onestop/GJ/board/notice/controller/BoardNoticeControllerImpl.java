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

import net.sf.json.JSONObject;

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
		
		String _section=request.getParameter("section");
		String _pageNum=request.getParameter("pageNum");
		int section = Integer.parseInt(((_section==null)? "1":_section));
		int pageNum= Integer.parseInt(((_pageNum==null)? "1":_pageNum));
		Map pagingMap=new HashMap();
		pagingMap.put("section",  section);
		pagingMap.put("pageNum",  pageNum);
		Map articlesMap = boardService.listArticles(pagingMap);
		
		articlesMap.put("section", section);
		articlesMap.put("pageNum", pageNum);
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesMap", articlesMap);
		return mav;
		
	}
	
	//검색창
	@Override
	@RequestMapping(value="/boardNotice/searchBoardList.do" ,method = RequestMethod.GET)
	public ModelAndView searchBoardList(@RequestParam("searchWord") String searchWord,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String _section=request.getParameter("section");
		String _pageNum=request.getParameter("pageNum");
		int section = Integer.parseInt(((_section==null)? "1":_section));
		int pageNum= Integer.parseInt(((_pageNum==null)? "1":_pageNum));
		Map pagingMap=new HashMap();
		pagingMap.put("section",  section);
		pagingMap.put("pageNum",  pageNum);
		pagingMap.put("searchWord",  searchWord);
		Map articlesMap = boardService.searchBoardList(pagingMap);
		
		articlesMap.put("section", section);
		articlesMap.put("pageNum", pageNum);
		articlesMap.put("searchWord", searchWord);
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesMap", articlesMap);
		System.out.println(articlesMap.size());
		return mav;
		
	}
	

	// 다중 이미지 글쓰기
	@Override
	@RequestMapping(value = "/boardNotice/addNewArticle.do", method = RequestMethod.POST)
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

		List<String> fileList = upload(multipartRequest);
		List<BoardNoticeImageVO> imageFileList = new ArrayList<BoardNoticeImageVO>();
		if (fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				BoardNoticeImageVO boardNoticeImageVO = new BoardNoticeImageVO();
				boardNoticeImageVO.setUp_fileName(fileName);
				imageFileList.add(boardNoticeImageVO);
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
				for (BoardNoticeImageVO boardNoticeImageVO : imageFileList) {
					up_fileName = boardNoticeImageVO.getUp_fileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
					// destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/boardNotice/listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for (BoardNoticeImageVO boardNoticeImageVO : imageFileList) {
					up_fileName = boardNoticeImageVO.getUp_fileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
					srcFile.delete();
				}
			}

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

//	// 한개 이미지 업로드하기
//	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
//		String up_fileName = null;
//		Iterator<String> fileNames = multipartRequest.getFileNames();
//
//		while (fileNames.hasNext()) {
//			String fileName = fileNames.next();
//			MultipartFile mFile = multipartRequest.getFile(fileName);
//			up_fileName = mFile.getOriginalFilename();
//			File file = new File(ARTICLE_IMAGE_REPO + "\\" + fileName);
//			if (mFile.getSize() != 0) { // File Null Check
//				if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
//					if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
//						file.createNewFile(); // 이후 파일 생성
//					}
//				}
//				mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName));
//				// 임시로 저장된 multipartFile을 실제 파일로 전송
//			}
//		}
//		return up_fileName;
//	}

	// 다중 이미지 업로드하기
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception {
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

//	게시글 상세
	@RequestMapping(value = "/boardNotice/viewArticle.do", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("noti_NO") int noti_NO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map articleMap = boardService.viewArticle(noti_NO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("articleMap", articleMap);
		return mav;
	}

	// 글 삭제하기
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
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/boardNotice/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 시도해주세요');";
			message += " location.href='" + request.getContextPath() + "/boardNotice/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 글 수정하기
	@RequestMapping(value = "/boardNotice/modArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		String up_fileName = null;

		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		List<String> fileList = upload(multipartRequest);
		List<BoardNoticeImageVO> imageFileList = new ArrayList<BoardNoticeImageVO>();
		if (fileList != null && fileList.size() != 0) {
			for (String fileName : fileList) {
				BoardNoticeImageVO boardNoticeImageVO = new BoardNoticeImageVO();
				boardNoticeImageVO.setUp_fileName(fileName);
				imageFileList.add(boardNoticeImageVO);
			}
			articleMap.put("imageFileList", imageFileList);
		}

		String noti_NO = (String) articleMap.get("noti_NO");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.modArticle(articleMap);
			if (imageFileList != null && imageFileList.size() != 0) {
				for (BoardNoticeImageVO boardNoticeImageVO : imageFileList) {
					up_fileName = boardNoticeImageVO.getUp_fileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO);
					//destDir = 파일 생성
					FileUtils.moveFileToDirectory(srcFile, destDir, true);

				}
				String originalFileName = (String) articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + noti_NO + "\\" + originalFileName);
				oldFile.delete();

			}
			message = "<script>";
			message += " alert('글을 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/boardNotice/viewArticle.do?noti_NO="
					+ noti_NO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + up_fileName);
			srcFile.delete();
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 수정해주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/boardNotice/viewArticle.do?noti_NO="
					+ noti_NO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}
	
	//키워드
//	@RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
//	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,
//			                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
//		//System.out.println(keyword);
//		if(keyword == null || keyword.equals(""))
//		   return null ;
//	
//		keyword = keyword.toUpperCase();
//	    List<String> keywordList =goodsService.keywordSearch(keyword);
//	    
//	 // 최종 완성될 JSONObject 선언(전체)
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("keyword", keywordList);
//		 		
//	    String jsonInfo = jsonObject.toString();
//	   // System.out.println(jsonInfo);
//	    return jsonInfo ;
//	}
	



}