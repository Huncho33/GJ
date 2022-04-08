package com.onestop.GJ.admin.stats.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AdminStatsVO {
	public Date v_date;
	public String member_id;
	public String v_gender;
	public String v_roadAddress;
	public String v_birth;

//		sql date로 받으면 날짜만 확인, util date로 받으면 시간까지 확인.
	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getV_gender() {
		return v_gender;
	}

	public void setV_gender(String v_gender) {
		this.v_gender = v_gender;
	}

	public String getV_roadAddress() {
		return v_roadAddress;
	}

	public void setV_roadAddress(String v_roadAddress) {
		this.v_roadAddress = v_roadAddress;
	}

	public String getV_birth() {
		return v_birth;
	}

	public void setV_birth(String v_birth) {
		this.v_birth = v_birth;
	}

	@Override
	public String toString() {
		return "MemberVO [v_date=" + v_date + ", member_id=" + member_id + ", v_gender=" + v_gender + ", v_roadAddress="
				+ v_roadAddress + ", v_birth=" + v_birth + "]";

	}

}
