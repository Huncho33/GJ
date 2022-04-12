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

//  -- ���� ����(��й�ȣ)
//	@Override
//	public MemberVO findPw_hp(MemberVO memberVO) throws Exception {
//		return memberDAO.certHp_Pw(memberVO);
//	}

	// �̸��� �ӽ� ��й�ȣ �߼� �޼���
	@Override
	public void send_PwMail(MemberVO memberVO) throws Exception {
		String to = memberVO.getMember_email1() + "@" + memberVO.getMember_email2();
		String subject = "������û���ְ� �÷��� �ӽ� ��й�ȣ �Դϴ�.";

		MimeMessage message = mailSender.createMimeMessage();

		String body = "";
		body += "<html><body>";
		body += "<div style='margin:100px;'>";
		body += "<h1> �ȳ��ϼ��� ������û���ְ� �Դϴ�. </h1>";
		body += "<br>";
		body += "<p>�Ʒ� �ڵ带 ȸ������ â���� ���ư� �Է����ּ���<p>";
		body += "<br>";
		body += "<p>�����մϴ�!<p>";
		body += "<br>";
		body += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		body += "<h3 style='color:blue;'>�ӽ� ��й�ȣ�Դϴ�.</h3>";
		body += "<div style='font-size:130%'>";
		body += "�ӽ� ��й�ȣ : <strong>";
		body += memberVO.getMember_pw() + "</strong><div><br/> ";
		body += "</div><body></html>";

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom("27minsub@gmail.com", "������û���ְ�");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body, true);
			mailSender.send(message);
		} catch (Exception e) {
			System.out.println("���Ϲ߼� ���� : " + e);
		}
	}

	// ��й�ȣ ã��
	@Override
	public ModelAndView find_pw(HttpServletResponse response, MemberVO memberVO) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		  ModelAndView mav = new ModelAndView();
		String res_overlap = (memberDAO.selectOverlappedID(memberVO.getMember_id()));

		// ���̵� ���� ���
		if (res_overlap.equals("false")) {
			out.print("���̵� �����ϴ�.");
			out.close();
			System.out.println(memberVO.getMember_id());
		}

		String input_email1 = memberVO.getMember_email1();
		String input_email2 = memberVO.getMember_email2();
		String mem_email1 = (memberDAO.SearchById(memberVO)).getMember_email1();
		String mem_email2 = (memberDAO.SearchById(memberVO)).getMember_email2();
		// �̸����� ���� �� �ۼ��� �̸����� �ƴ� ���

		if (!((input_email1.equals(mem_email1)) && (input_email2.equals(mem_email2)))) {

			out.print("���� �� ����� �̸����� �ƴմϴ�.");
			out.close();
		} else {
			// ���̵� �̸��� ��ġ
			// �ӽ� ��й�ȣ ����
			String member_pw = "";
			for (int i = 0; i < 12; i++) {
				member_pw += (char) ((Math.random() * 26) + 97);
			}
			memberVO.setMember_pw(member_pw);
			System.out.println(memberVO.toString());

			System.out.println("��й�ȣ ���� ���̵�: " + memberVO.getMember_id());

			// ��й�ȣ ����
			memberDAO.update_pw(memberVO);

			// ��й�ȣ ���� ���� �߼�
			send_PwMail(memberVO);
			
			out.print("�̸��Ϸ� �ӽ� ��й�ȣ�� �߼��Ͽ����ϴ�.\n �ӽ� ��й�ȣ�� �ٽ� �α��� �� �ּ���.");
			mav.setViewName("redirect:/main.do");
		}
		return mav;
	}

	// IDã��
	@Override
	public MemberVO findId_hp(MemberVO memberVO) throws Exception {
		return memberDAO.certHp_Id(memberVO);
	}

	// �������� �ֱ� 5�� �� ȣ��
	@Override
	public List<BoardNoticeVO> selectNotiList() throws Exception {
		List<BoardNoticeVO> notiList = memberDAO.selectNotiList();
		return notiList;
	}

	// �ڷ�� �ֱ� 5�� �� ȣ��
	@Override
	public List<BoardDataVO> selectDataList() throws Exception {
		List<BoardDataVO> dataList = memberDAO.selectDataList();
		return dataList;
	}
	
	//�湮�� ���� ����
	@Override
	public void insertVisit(Map visitMap) {
		memberDAO.insertVisit(visitMap);
		
	}
	
	//�湮�� ��
	@Override
	public Map getTotCnt(Map visitMap) {
		int getVisitTotCnt = memberDAO.getVisitTotCnt(visitMap);
		visitMap.put("getVisitTotCnt", getVisitTotCnt);
		
		return visitMap;
	}


}
