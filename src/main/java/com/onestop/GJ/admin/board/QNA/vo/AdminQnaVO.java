package com.onestop.GJ.admin.board.QNA.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("adminQnaVO")
public class AdminQnaVO {
	private int level;
	private int qna_no;
	private int qnaparent_no;
	private String qna_title;
	private String qna_content;
	private String member_id;
	private Date qna_date;
	private int qna_pw;
	private String qna_policy;
	private String qna_reply;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public int getQnaparent_no() {
		return qnaparent_no;
	}
	public void setQnaparent_no(int qnaparent_no) {
		this.qnaparent_no = qnaparent_no;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}
	public int getQna_pw() {
		return qna_pw;
	}
	public void setQna_pw(int qna_pw) {
		this.qna_pw = qna_pw;
	}
	public String getQna_policy() {
		return qna_policy;
	}
	public void setQna_policy(String qna_policy) {
		this.qna_policy = qna_policy;
	}
	public String getQna_reply() {
		return qna_reply;
	}
	public void setQna_reply(String qna_reply) {
		this.qna_reply = qna_reply;
	}
	
	@Override
	public String toString() {
		return "AdminQnaVO [level=" + level + "qna_no=" + qna_no + "qnaparent_no=" + qnaparent_no + "qna_title=" + qna_title + "qna_content=" + qna_content + "member_id=" + member_id + "qna_no=" + qna_no + "]";
	}
}
