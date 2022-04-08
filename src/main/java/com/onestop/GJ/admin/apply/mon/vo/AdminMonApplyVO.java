package com.onestop.GJ.admin.apply.mon.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.onestop.GJ.member.vo.MemberVO;

@Component("AdminMonApply")
public class AdminMonApplyVO {
	private int mo_no;
	private String up_filename;
	private Date mo_date;
	private String mo_result;
	private String mo_policy;
	private String mo_reason;
	private String member_id;

	public int getMo_no() {
		return mo_no;
	}

	public void setMo_no(int mo_no) {
		this.mo_no = mo_no;
	}

	public String getUp_filename() {
		return up_filename;
	}

	public void setUp_filename(String up_filename) {
		this.up_filename = up_filename;
	}

	public Date getMo_date() {
		return mo_date;
	}

	public void setMo_date(Date mo_date) {
		this.mo_date = mo_date;
	}

	public String getMo_result() {
		return mo_result;
	}

	public void setMo_result(String mo_result) {
		this.mo_result = mo_result;
	}

	public String getMo_policy() {
		return mo_policy;
	}

	public void setMo_policy(String mo_policy) {
		this.mo_policy = mo_policy;
	}

	public String getMo_reason() {
		return mo_reason;
	}

	public void setMo_reason(String mo_reason) {
		this.mo_reason = mo_reason;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public MemberVO getMembervo() {
		return membervo;
	}

	public void setMembervo(MemberVO membervo) {
		this.membervo = membervo;
	}

	private MemberVO membervo;

	@Override
	public String toString() {
		return "adminMonApplyVO [mo_no=" + mo_no + ", up_filename=" + up_filename + ", mo_date=" + mo_date
				+ ", mo_result=" + mo_result + ", mo_policy=" + mo_policy + ", mo_reason=" + mo_reason + ", member_id="
				+ member_id;
	}

}