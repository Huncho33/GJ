package com.onestop.GJ.apply.mon23.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ApplyMonFileVO {
	private int up_fileno;
	private String up_filename;
	private Date up_date;
	private int mo_no;
	
	
	
	
	public int getUp_fileno() {
		return up_fileno;
	}




	public void setUp_fileno(int up_fileno) {
		this.up_fileno = up_fileno;
	}




	public String getUp_filename() {
		 try {
	         if(up_filename!= null && up_filename.length()!=0) {
	        	 up_filename = URLDecoder.decode(up_filename,"UTF-8");
	         }
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      }
      return up_filename;
   }




	public void setUp_filename(String up_filename) {
		 try {
	         if(up_filename!= null && up_filename.length()!=0) {
	            this.up_filename = URLDecoder.decode(up_filename,"UTF-8");
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




	public int getMo_no() {
		return mo_no;
	}




	public void setMo_no(int mo_no) {
		this.mo_no = mo_no;
	}




	@Override
    public String toString() {
       return "ApplyMonFileVO [up_filename=" + up_filename +  "]";
    }

	

}