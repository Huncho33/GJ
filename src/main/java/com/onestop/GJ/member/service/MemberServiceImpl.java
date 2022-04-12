package com.onestop.GJ.member.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.onestop.GJ.board.data.vo.BoardDataVO;
import com.onestop.GJ.board.notice.vo.BoardNoticeVO;
import com.onestop.GJ.member.dao.MemberDAO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}

	@Override
	public void addMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.insertMember(memberVO);
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return memberDAO.loginById(memberVO);
	}
	
	@Override
	public void last_log(String member_id) throws Exception {
		memberDAO.last_logOn(member_id);
	}
	

	@Override
	public String overlapped(String id) throws Exception {
		return memberDAO.selectOverlappedID(id);
	}

//  -- 인증 관련(비밀번호)
//	@Override
//	public MemberVO findPw_hp(MemberVO memberVO) throws Exception {
//		return memberDAO.certHp_Pw(memberVO);
//	}

	// 이메일 임시 비밀번호 발송 메서드
	@Override
	public void send_PwMail(MemberVO memberVO) throws Exception {
		String to = memberVO.getMember_email1() + "@" + memberVO.getMember_email2();
		String subject = "원스톱청년주거 플랫폼 임시 비밀번호 입니다.";

		MimeMessage message = mailSender.createMimeMessage();

		String body = "";
		body += "<html><body>";
		body += "<div style='margin:100px;'>";
		body += "<h1> 안녕하세요 원스톱청년주거 입니다. </h1>";
		body += "<br>";
		body += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
		body += "<br>";
		body += "<p>감사합니다!<p>";
		body += "<br>";
		body += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		body += "<h3 style='color:blue;'>임시 비밀번호입니다.</h3>";
		body += "<div style='font-size:130%'>";
		body += "임시 비밀번호 : <strong>";
		body += memberVO.getMember_pw() + "</strong><div><br/> ";
		body += "</div><body></html>";

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom("27minsub@gmail.com", "원스톱청년주거");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body, true);
			mailSender.send(message);
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	// 비밀번호 찾기
	@Override
	public ModelAndView find_pw(HttpServletResponse response, MemberVO memberVO) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		  ModelAndView mav = new ModelAndView();
		String res_overlap = (memberDAO.selectOverlappedID(memberVO.getMember_id()));

		// 아이디가 없는 경우
		if (res_overlap.equals("false")) {
			out.print("아이디가 없습니다.");
			out.close();
			System.out.println(memberVO.getMember_id());
		}

		String input_email1 = memberVO.getMember_email1();
		String input_email2 = memberVO.getMember_email2();
		String mem_email1 = (memberDAO.SearchById(memberVO)).getMember_email1();
		String mem_email2 = (memberDAO.SearchById(memberVO)).getMember_email2();
		// 이메일이 가입 시 작성한 이메일이 아닐 경우

		if (!((input_email1.equals(mem_email1)) && (input_email2.equals(mem_email2)))) {

			out.print("가입 시 등록한 이메일이 아닙니다.");
			out.close();
		} else {
			// 아이디 이메일 일치
			// 임시 비밀번호 생성
			String member_pw = "";
			for (int i = 0; i < 12; i++) {
				member_pw += (char) ((Math.random() * 26) + 97);
			}
			memberVO.setMember_pw(member_pw);
			System.out.println(memberVO.toString());

			System.out.println("비밀번호 변경 아이디: " + memberVO.getMember_id());

			// 비밀번호 변경
			memberDAO.update_pw(memberVO);

			// 비밀번호 변경 메일 발송
			send_PwMail(memberVO);
			
			out.print("이메일로 임시 비밀번호를 발송하였습니다.\n 임시 비밀번호로 다시 로그인 해 주세요.");
			mav.setViewName("redirect:/main.do");
		}
		return mav;
	}

	// ID찾기
	@Override
	public MemberVO findId_hp(MemberVO memberVO) throws Exception {
		return memberDAO.certHp_Id(memberVO);
	}

	// 공지사항 최근 5개 글 호출
	@Override
	public List<BoardNoticeVO> selectNotiList() throws Exception {
		List<BoardNoticeVO> notiList = memberDAO.selectNotiList();
		return notiList;
	}

	// 자료실 최근 5개 글 호출
	@Override
	public List<BoardDataVO> selectDataList() throws Exception {
		List<BoardDataVO> dataList = memberDAO.selectDataList();
		return dataList;
	}
	
	//방문자 정보 저장
	@Override
	public void insertVisit(Map visitMap) {
		memberDAO.insertVisit(visitMap);
		
	}
	
	//방문자 수
	@Override
	public Map getTotCnt(Map visitMap) {
		int getVisitTotCnt = memberDAO.getVisitTotCnt(visitMap);
		visitMap.put("getVisitTotCnt", getVisitTotCnt);
		
		return visitMap;
	}


}
