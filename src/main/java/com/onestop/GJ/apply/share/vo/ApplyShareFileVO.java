package com.onestop.GJ.apply.share.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;

public class ApplyShareFileVO {
	private int up_fileno;
	private String up_filename;
	private Date up_date;
	private int sh_no;

	public int getUp_fileno() {
		return up_fileno;
	}

	public void setUp_fileno(int up_fileno) {
		this.up_fileno = up_fileno;
	}

	public String getUp_filename() {
		try {
			if (up_filename != null && up_filename.length() != 0) {
				up_filename = URLDecoder.decode(up_filename, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return up_filename;
	}

	public void setUp_filename(String up_filename) {
		try {
			if (up_filename != null && up_filename.length() != 0) {
				this.up_filename = URLDecoder.decode(up_filename, "UTF-8");
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

	public int getSh_no() {
		return sh_no;
	}

	public void setSh_no(int sh_no) {
		this.sh_no = sh_no;
	}

}
