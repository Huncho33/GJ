package com.onestop.GJ.apply.rent_return.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("ApplyRentReturnVO")
public class ApplyRentReturnVO {
	private int ret_no;
	private String up_filename;
	private Date ret_date;
	private String ret_result;
	private String ret_policy;
	private String ret_reason;
	private String member_id;
	private Date ret_startpay;
	private Date ret_endpay;
	

	public int getRet_no() {
		return ret_no;
	}

	public void setRet_no(int ret_no) {
		this.ret_no = ret_no;
	}

	public Date getRet_date() {
		return ret_date;
	}

	public void setRet_date(Date ret_date) {
		this.ret_date = ret_date;
	}

	public String getRet_result() {
		return ret_result;
	}

	public void setRet_result(String ret_result) {
		this.ret_result = ret_result;
	}

	public String getRet_policy() {
		return ret_policy;
	}

	public void setRet_policy(String ret_policy) {
		this.ret_policy = ret_policy;
	}

	public String getRet_reason() {
		return ret_reason;
	}

	public void setRet_reason(String ret_reason) {
		this.ret_reason = ret_reason;
	}

	public String getUp_filename() {
		 try {
             if (up_filename != null && up_filename.length() != 0) {
            	 up_filename = URLDecoder.decode(up_filename, "UTF-8");
             }   // 파일의 null과 blank 처리하면서 디코딩합니다.
          } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
          }
          return up_filename;
	}

	public void setUp_filename(String up_filename) {
		try {
            if(up_filename!=null && up_filename.length()!=0) {
               this.up_filename = URLEncoder.encode(up_filename, "UTF-8"); 
                  //파일이름에 특수문자가 있을 경우 인코딩합니다.
            }
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
      }


	public Date getRet_startpay() {
		return ret_startpay;
	}

	public void setRet_startpay(Date ret_startpay) {
		this.ret_startpay = ret_startpay;
	}

	public Date getRet_endpay() {
		return ret_endpay;
	}

	public void setRet_endpay(Date ret_endpay) {
		this.ret_endpay = ret_endpay;
	}

	@Override
	public String toString() {
		return "ApplyMonVO [ret_no=" + ret_no + ", up_filename=" + up_filename + ", mo_date="
				+ ret_date + ", mo_result=" + ret_result + "]";
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}



}
