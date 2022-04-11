package com.onestop.GJ.admin.apply.rent.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.onestop.GJ.member.vo.MemberVO;

@Component("AdminrentApply")
public class AdminRentApplyVO {
	private int rent_no;
	private String up_filename;
	private Date rent_date;
	private String rent_result;
	private String rent_policy;
	private String rent_reason;
	private String member_id;

	public int getrent_no() {
		return rent_no;
	}

	public void setrent_no(int rent_no) {
		this.rent_no = rent_no;
	}

	public String getUp_filename() {
		return up_filename;
	}

	public void setUp_filename(String up_filename) {
		this.up_filename = up_filename;
	}

	public Date getrent_date() {
		return rent_date;
	}

	public void setrent_date(Date rent_date) {
		this.rent_date = rent_date;
	}

	public String getrent_result() {
		return rent_result;
	}

	public void setrent_result(String rent_result) {
		this.rent_result = rent_result;
	}

	public String getrent_policy() {
		return rent_policy;
	}

	public void setrent_policy(String rent_policy) {
		this.rent_policy = rent_policy;
	}

	public String getrent_reason() {
		return rent_reason;
	}

	public void setrent_reason(String rent_reason) {
		this.rent_reason = rent_reason;
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
		return "adminrentApplyVO [rent_no=" + rent_no + ", up_filename=" + up_filename + ", rent_date=" + rent_date
				+ ", rent_result=" + rent_result + ", rent_policy=" + rent_policy + ", rent_reason=" + rent_reason + ", member_id="
				+ member_id;
	}

}