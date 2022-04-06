package com.onestop.GJ.apply.back.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("ApplyBackVO")
public class ApplyBackVO {

	private int ba_no;
	private String up_filename;
	private String member_id;
	private Date ba_date;
	private String ba_result;
	private String ba_policy;
	private String ba_reason;
	private Date ba_startpay;
	private Date ba_endpay;

	public int getBa_no() {
		return ba_no;
	}

	public void setBa_no(int ba_no) {
		this.ba_no = ba_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getBa_date() {
		return ba_date;
	}

	public void setBa_date(Date ba_date) {
		this.ba_date = ba_date;
	}

	public String getBa_result() {
		return ba_result;
	}

	public void setBa_result(String ba_result) {
		this.ba_result = ba_result;
	}

	public String getBa_policy() {
		return ba_policy;
	}

	public void setBa_policy(String ba_policy) {
		this.ba_policy = ba_policy;
	}

	public String getBa_reason() {
		return ba_reason;
	}

	public void setBa_reason(String ba_reason) {
		this.ba_reason = ba_reason;
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

	public Date getBa_startpay() {
		return ba_startpay;
	}

	public void setBa_startpay(Date ba_startpay) {
		this.ba_startpay = ba_startpay;
	}

	public Date getBa_endpay() {
		return ba_endpay;
	}

	public void setBa_endpay(Date ba_endpay) {
		this.ba_endpay = ba_endpay;
	}

	@Override
	public String toString() {
		return "ApplyBackVO [ba_no=" + ba_no + ", up_filename=" + up_filename + ", ba_date=" + ba_date + ", ba_result="
				+ ba_result + "]";
	}

}
