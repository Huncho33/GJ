package com.onestop.GJ.admin.data.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("adminDataVO")
public class AdminDataVO {
	private int etc_NO;
	   private String etc_title;
	   private String etc_context;
	   private int etc_hits;
	   private Date etc_date;
	   private String member_id;
	   
	   
	   public int getEtc_NO() {
		return etc_NO;
	}
	public void setEtc_NO(int etc_NO) {
		this.etc_NO = etc_NO;
	}
	public String getEtc_title() {
		return etc_title;
	}
	public void setEtc_title(String etc_title) {
		this.etc_title = etc_title;
	}
	public String getEtc_context() {
		return etc_context;
	}
	public void setEtc_context(String etc_context) {
		this.etc_context = etc_context;
	}
	public int getEtc_hits() {
		return etc_hits;
	}
	public void setEtc_hits(int etc_hits) {
		this.etc_hits = etc_hits;
	}
	public Date getEtc_date() {
		return etc_date;
	}
	public void setEtc_date(Date etc_date) {
		this.etc_date = etc_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getUp_fileName() {
		try {
            if (up_fileName != null && up_fileName.length() != 0) {
               up_fileName = URLDecoder.decode(up_fileName, "UTF-8");
            }   // 파일의 null과 blank 처리하면서 디코딩합니다.
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
         return up_fileName;
   }
	public void setUp_fileName(String up_fileName) {
		try {
            if(up_fileName!=null && up_fileName.length()!=0) {
               this.up_fileName = URLEncoder.encode(up_fileName, "UTF-8"); 
                  //파일이름에 특수문자가 있을 경우 인코딩합니다.
            }
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
      }
	private String up_fileName;
	   
	   

}
