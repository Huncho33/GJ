package com.onestop.GJ.email.service;

import java.io.PrintWriter;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.onestop.GJ.email.dao.MailDAO;
import com.onestop.GJ.member.vo.MemberVO;

@Service("mailService")
public class MailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	@Autowired
	private MailDAO mailDAO;

	public static final String ePw = createKey();
	// pw ã�� �� �̿�
	@Inject
	private MailDAO manager;

	@Override
	@Async
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom("27minsub@gmail.com", "�μ�");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Async
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}

	@Override
	public MemberVO findPw_hp(MemberVO memberVO) throws Exception {
		return mailDAO.certHp_Pw(memberVO);
	}
	
	
	
	// ȸ������ �� �̸��� ����(�̸��� Ȯ��)
	

	private MimeMessage createMessage(String to) throws Exception {
		System.out.println("������ ��� : " + to);
		System.out.println("���� ��ȣ : " + ePw);
		MimeMessage message = mailSender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);// ������ ���
		message.setSubject("������û���ְ� ȸ������ �̸��� ����");// ����

		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> �ȳ��ϼ��� ������û���ְ� �Դϴ�. </h1>";
		msgg += "<br>";
		msgg += "<p>�Ʒ� �ڵ带 ȸ������ â���� ���ư� �Է����ּ���<p>";
		msgg += "<br>";
		msgg += "<p>�����մϴ�!<p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>ȸ������ ���� �ڵ��Դϴ�.</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "CODE : <strong>";
		msgg += ePw + "</strong><div><br/> ";
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// ����
		message.setFrom(new InternetAddress("27minsub@gmail.com", "������û���ְ�"));// ������ ���

		return message;
	}

	public static String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // �����ڵ� 8�ڸ�
			int index = rnd.nextInt(3); // 0~2 ���� ����

			switch (index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (rnd.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				// 0~9
				break;
			}
		}
		return key.toString();
	}

	@Override
	public String sendSimpleMessage(String email) throws Exception {
		MimeMessage message = createMessage(email);
		try {// ����ó��
			mailSender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}
		return ePw;
	}




}