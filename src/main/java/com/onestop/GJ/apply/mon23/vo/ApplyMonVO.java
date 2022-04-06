package com.onestop.GJ.apply.mon23.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

import com.onestop.GJ.member.vo.MemberVO;

@Component("ApplyMonVO")
public class ApplyMonVO {
	private int mo_no;
	private String up_filename;
	private Date mo_date;
	private String mo_result;
	private String mo_policy;
	private String mo_reason;
	private String member_id;
	private Date mo_startpay;
	private Date mo_endpay;

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

	public int getMo_no() {
		return mo_no;
	}

	public void setMo_no(int mo_no) {
		this.mo_no = mo_no;
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

	@Override
	public String toString() {
		return "ApplyMonVO [mo_no=" + mo_no + ", up_filename=" + up_filename + ", mo_date=" + mo_date + ", mo_result="
				+ mo_result + "]";
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getMo_startpay() {
		return mo_startpay;
	}

	public void setMo_startpay(Date mo_startpay) {
		this.mo_startpay = mo_startpay;
	}

	public Date getMo_endpay() {
		return mo_endpay;
	}

	public void setMo_endpay(Date mo_endpay) {
		this.mo_endpay = mo_endpay;
	}

}
