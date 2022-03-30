package com.onestop.GJ.apply.rent.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("ApplyRentVO")
public class ApplyRentVO {
	private int rent_no;
	private String up_filename;
	private String member_id;
	private Date rent_date;
	private String rent_result;
	private String rent_policy;
	private String rent_reason;
	

	public String getRent_policy() {
		return rent_policy;
	}

	public void setRent_policy(String rent_policy) {
		this.rent_policy = rent_policy;
	}

	public String getRent_reason() {
		return rent_reason;
	}

	public void setRent_reason(String rent_reason) {
		this.rent_reason = rent_reason;
	}

	public int getRent_no() {
		return rent_no;
	}

	public void setRent_no(int rent_no) {
		this.rent_no = rent_no;
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

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getRent_date() {
		return rent_date;
	}

	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}

	public String getRent_result() {
		return rent_result;
	}

	public void setRent_result(String rent_result) {
		this.rent_result = rent_result;
	}

	@Override
	public String toString() {
		return "ApplyMonVO [rent_no=" + rent_no + ", up_filename=" + up_filename + ", member_id=" + member_id + ", rent_date="
				+ rent_date + ", mo_result=" + rent_result + "]";
	}

}