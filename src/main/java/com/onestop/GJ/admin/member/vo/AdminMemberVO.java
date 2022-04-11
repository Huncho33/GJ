package com.onestop.GJ.admin.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("adminMemberVO")
public class AdminMemberVO {

	private String member_id;
	private String member_name;
	private String member_pw;
	private String member_gender;
	private String member_birth;
	private int member_phoneno;
	// 이메일 추가.
	private String member_email1;
	private String member_email2;
//	private String emailsts_yn;
	// 주소 추가.
	private String member_zipcode;
	private String member_roadAddress;
	private String member_jibunAddress;
	private String member_namujiAddress;

	private Date member_last_log;
	private String member_right;
	private Date member_joinDate;
	
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public String getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}

	public int getMember_phoneno() {
		return member_phoneno;
	}

	public void setMember_phoneno(int member_phoneno) {
		this.member_phoneno = member_phoneno;
	}

	public Date getMember_last_log() {
		return member_last_log;
	}

	public String getMember_email1() {
		return member_email1;
	}

	public void setMember_email1(String member_email1) {
		this.member_email1 = member_email1;
	}

	public String getMember_email2() {
		return member_email2;
	}

	public void setMember_email2(String member_email2) {
		this.member_email2 = member_email2;
	}

	public String getMember_zipcode() {
		return member_zipcode;
	}

	public void setMember_zipcode(String member_zipcode) {
		this.member_zipcode = member_zipcode;
	}

	public String getMember_roadAddress() {
		return member_roadAddress;
	}

	public void setMember_roadAddress(String member_roadAddress) {
		this.member_roadAddress = member_roadAddress;
	}

	public String getMember_jibunAddress() {
		return member_jibunAddress;
	}

	public void setMember_jibunAddress(String member_jibunAddress) {
		this.member_jibunAddress = member_jibunAddress;
	}

	public String getMember_namujiAddress() {
		return member_namujiAddress;
	}

	public void setMember_namujiAddress(String member_namujiAddress) {
		this.member_namujiAddress = member_namujiAddress;
	}

	public void setMember_last_log(Date member_last_log) {
		this.member_last_log = member_last_log;
	}
	public String getMember_right() {
		return member_right;
	}
	public void setMember_right(String member_right) {
		this.member_right = member_right;
	}

	public Date getMember_joinDate() {
		return member_joinDate;
	}

	public void setMember_joinDate(Date member_joinDate) {
		this.member_joinDate = member_joinDate;
	}

	@Override
	   public String toString() {
	      return "MemberVO [member_id=" + member_id + ", member_pw=" + member_pw + ", member_phoneno=" + member_phoneno + ", member_email1="
	            + member_email1 + ", member_email2=" + member_email2 + ", member_zipcode=" + member_zipcode
	            + ", member_roadAddress=" + member_roadAddress + ", member_jibunAddress=" + member_jibunAddress
	            + ", member_namujiAddress=" + member_namujiAddress + "]";
	   }
}
