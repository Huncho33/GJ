package com.onestop.GJ.board.data.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;

public class BoardDataImageVO {
	private String up_fileName;
	private int up_fileNO;
	private int etc_NO;
	private Date up_date;

	public int getUp_fileNO() {
		return up_fileNO;
	}

	public void setUp_fileNO(int up_fileNO) {
		this.up_fileNO = up_fileNO;
	}

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

	public Date getUp_date() {
		return up_date;
	}

	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	public int getEtc_NO() {
		return etc_NO;
	}

	public void setEtc_NO(int etc_NO) {
		this.etc_NO = etc_NO;
	}

}