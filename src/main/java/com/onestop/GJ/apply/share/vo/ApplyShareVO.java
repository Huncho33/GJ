package com.onestop.GJ.apply.share.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("ApplyShareVO")
public class ApplyShareVO {
	private int sh_no;
	private String up_filename;
	private Date sh_date;
	private String sh_result;
	private String sh_policy;
	private String sh_reason;
	private String member_id;
	private Date sh_startpay;
	private Date sh_endpay;

	public int getSh_no() {
		return sh_no;
	}

	public void setSh_no(int sh_no) {
		this.sh_no = sh_no;
	}

	public Date getSh_date() {
		return sh_date;
	}

	public void setSh_date(Date sh_date) {
		this.sh_date = sh_date;
	}

	public String getSh_result() {
		return sh_result;
	}

	public void setSh_result(String sh_result) {
		this.sh_result = sh_result;
	}

	public String getSh_policy() {
		return sh_policy;
	}

	public void setSh_policy(String sh_policy) {
		this.sh_policy = sh_policy;
	}

	public String getSh_reason() {
		return sh_reason;
	}

	public void setSh_reason(String sh_reason) {
		this.sh_reason = sh_reason;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getSh_startpay() {
		return sh_startpay;
	}

	public void setSh_startpay(Date sh_startpay) {
		this.sh_startpay = sh_startpay;
	}

	public Date getSh_endpay() {
		return sh_endpay;
	}

	public void setSh_endpay(Date sh_endpay) {
		this.sh_endpay = sh_endpay;
	}

	public String getUp_filename() {
		try {
			if (up_filename != null && up_filename.length() != 0) {
				up_filename = URLDecoder.decode(up_filename, "UTF-8");
			} // 파일의 null과 blank 처리하면서 디코딩합니다.
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return up_filename;
	}

	public void setUp_filename(String up_filename) {
		try {
			if (up_filename != null && up_filename.length() != 0) {
				this.up_filename = URLEncoder.encode(up_filename, "UTF-8");
				// 파일이름에 특수문자가 있을 경우 인코딩합니다.
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "ApplyShareVO [sh_no=" + sh_no + ", up_filename=" + up_filename + ", sh_date=" + sh_date + ", sh_result="
				+ sh_result + "]";
	}

}
