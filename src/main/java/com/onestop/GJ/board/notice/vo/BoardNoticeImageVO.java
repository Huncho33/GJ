package com.onestop.GJ.board.notice.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;

public class BoardNoticeImageVO {
   private String up_fileName;
   private int up_fileNO;
   private int noti_NO;
   private Date up_date;
   
   public int getUp_fileNO() {
      return up_fileNO;
   }
   public void setUp_fileNO(int up_fileNO) {
      this.up_fileNO = up_fileNO;
   }
   public String getUp_fileName() {
	   try {
	         if(up_fileName!= null && up_fileName.length()!=0) {
	            up_fileName = URLDecoder.decode(up_fileName,"UTF-8");
	         }
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      }
      return up_fileName;
   }
   public void setUp_fileName(String up_fileName) {
      try {
         if(up_fileName!= null && up_fileName.length()!=0) {
            this.up_fileName = URLDecoder.decode(up_fileName,"UTF-8");
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
   
   public int getNoti_NO() {
      return noti_NO;
   }
   public void setNoti_NO(int noti_NO) {
      this.noti_NO = noti_NO;
   }
   @Override
   public String toString() {
      return "BoardNoticeImageVO [up_fileName=" + up_fileName + ", up_fileNO=" + up_fileNO + ", noti_NO="
            + noti_NO + ", up_date=" + up_date +  "]";
   }
}