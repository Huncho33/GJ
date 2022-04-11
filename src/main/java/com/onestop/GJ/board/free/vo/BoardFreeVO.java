package com.onestop.GJ.board.free.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("boardFreeVO")
public class BoardFreeVO {
	private int fr_NO;
	private String fr_title;
	private String fr_context;
	private int fr_hits;
	private Date fr_date;
	private String member_id;
	private String up_fileName;

	public BoardFreeVO() {
	}

	public int getFr_NO() {
		return fr_NO;
	}

	public void setFr_NO(int fr_NO) {
		this.fr_NO = fr_NO;
	}

	public String getFr_title() {
		return fr_title;
	}

	public void setFr_title(String fr_title) {
		this.fr_title = fr_title;
	}

	public String getFr_context() {
		return fr_context;
	}

	public void setFr_context(String fr_context) {
		this.fr_context = fr_context;
	}

	public int getFr_hits() {
		return fr_hits;
	}

	public void setFr_hits(int fr_hits) {
		this.fr_hits = fr_hits;
	}

	public Date getFr_date() {
		return fr_date;
	}

	public void setFr_date(Date fr_date) {
		this.fr_date = fr_date;
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
			} // 파일의 null과 blank 처리하면서 디코딩합니다.
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return up_fileName;
	}

	public void setUp_fileName(String up_fileName) {
		try {
			if (up_fileName != null && up_fileName.length() != 0) {
				this.up_fileName = URLEncoder.encode(up_fileName, "UTF-8");
				// 파일이름에 특수문자가 있을 경우 인코딩합니다.
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "BoardDataVO [fr_hits=" + fr_hits + "]";
	}

}