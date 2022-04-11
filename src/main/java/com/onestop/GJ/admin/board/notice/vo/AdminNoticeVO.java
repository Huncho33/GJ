package com.onestop.GJ.admin.board.notice.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("adminNoticeVO")	
public class AdminNoticeVO {
	private int noti_NO;
	private String noti_title;
	private String noti_context;
	private int noti_hits;
	private Date noti_date;
	private String member_id;
	private String up_fileName;
	
	
	public int getNoti_NO() {
		return noti_NO;
	}
	public void setNoti_NO(int noti_NO) {
		this.noti_NO = noti_NO;
	}
	public String getNoti_title() {
		return noti_title;
	}
	public void setNoti_title(String noti_title) {
		this.noti_title = noti_title;
	}
	public String getNoti_context() {
		return noti_context;
	}
	public void setNoti_context(String noti_context) {
		this.noti_context = noti_context;
	}
	public int getNoti_hits() {
		return noti_hits;
	}
	public void setNoti_hits(int noti_hits) {
		this.noti_hits = noti_hits;
	}
	public Date getNoti_date() {
		return noti_date;
	}
	public void setNoti_date(Date noti_date) {
		this.noti_date = noti_date;
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
	}
	
	
	

