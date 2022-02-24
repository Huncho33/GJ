package com.onestop.GJ.board.notice.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

public class BoardNoticeImageVO {
	private String up_fileName;
	private int up_fileNO;
	private int noti_NO;
	private Date up_date;
	
	public int getup_fileNO() {
		return up_fileNO;
	}
	public void setup_fileNO(int up_fileNO) {
		this.up_fileNO = up_fileNO;
	}
	public String getup_fileName() {
		return up_fileName;
	}
	public void setup_fileName(String up_fileName) {
		try {
			if(up_fileName!= null && up_fileName.length()!=0) {
				this.up_fileName = URLEncoder.encode(up_fileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Date getup_date() {
		return up_date;
	}

	public void setup_date(Date up_date) {
		this.up_date = up_date;
	}
	
	public int getnoti_NO() {
		return noti_NO;
	}
	public void setnoti_NO(int noti_NO) {
		this.noti_NO = noti_NO;
	}
}
