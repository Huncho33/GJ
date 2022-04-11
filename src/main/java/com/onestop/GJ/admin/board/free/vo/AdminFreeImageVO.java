package com.onestop.GJ.admin.board.free.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;

public class AdminFreeImageVO {
	private String up_fileName;
	private int up_fileNO;
	private int fr_NO;
	private Date up_date;
	
	public String getUp_fileName() {
		try {
			if (up_fileName != null && up_fileName.length() != 0) {
				up_fileName = URLDecoder.decode(up_fileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return up_fileName;
	}
	public void setUp_fileName(String up_fileName) {
		try {
			if (up_fileName != null && up_fileName.length() != 0) {
				this.up_fileName = URLDecoder.decode(up_fileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public int getUp_fileNO() {
		return up_fileNO;
	}
	public void setUp_fileNO(int up_fileNO) {
		this.up_fileNO = up_fileNO;
	}
	public int getFr_NO() {
		return fr_NO;
	}
	public void setFr_NO(int fr_NO) {
		this.fr_NO = fr_NO;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
	
	
	
}
