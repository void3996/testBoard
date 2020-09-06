package com.ict.domain;

import java.util.Date;

public class MemberVO {
/*  
    memId      varchar2(50)    not null,
    memPassword    varchar2(100)   not null,
    memNickname    varchar2(30)    not null,
    memPhone    varchar2(20)    not null,
    userAddr1   varchar2(20)    not null,
    userAddr2   varchar2(50)    not null,
    userAddr3   varchar2(50)    not null,
    regiDate    date            default sysdate,
    verify      number          default 0,
*/
   private String memId;
   private String memPassword;
   private String memName;
   private String memNickname;
   private String memEmail;
   private String memPhone;
   private String memSex;
   private String memBirthday;
   private String memBirthday1;
   private String memBirthday2;
   private String memBirthday3;
   private String userAddr1;
   private String userAddr2;
   private String userAddr3;
   private Date regDate;
   private int verify;
   
   private String memStatus;
   
   public String getMemId() {
      return memId;
   }
   public void setMemId(String memId) {
      this.memId = memId;
   }
   public String getMemPassword() {
      return memPassword;
   }
   public void setMemPassword(String memPassword) {
      this.memPassword = memPassword;
   }
   public String getMemName() {
      return memName;
   }
   public void setMemName(String memName) {
      this.memName = memName;
   }
   public String getMemNickname() {
      return memNickname;
   }
   public void setMemNickname(String memNickname) {
      this.memNickname = memNickname;
   }
   public String getMemEmail() {
      return memEmail;
   }
   public void setMemEmail(String memEmail) {
      this.memEmail = memEmail;
   }
   public String getMemPhone() {
      return memPhone;
   }
   public void setMemPhone(String memPhone) {
      this.memPhone = memPhone;
   }
   public String getMemSex() {
      return memSex;
   }
   public void setMemSex(String memSex) {
      this.memSex = memSex;
   }
   public String getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(String memBirthday) {
		this.memBirthday = memBirthday;
	}
	public String getMemBirthday1() {
		return memBirthday1;
	}
	public void setMemBirthday1(String memBirthday1) {
		this.memBirthday1 = memBirthday1;
	}
	public String getMemBirthday2() {
		return memBirthday2;
	}
	public void setMemBirthday2(String memBirthday2) {
		this.memBirthday2 = memBirthday2;
	}
	public String getMemBirthday3() {
		return memBirthday3;
	}
	public void setMemBirthday3(String memBirthday3) {
		this.memBirthday3 = memBirthday3;
	}
	public String getUserAddr1() {
      return userAddr1;
   }
   public void setUserAddr1(String userAddr1) {
      this.userAddr1 = userAddr1;
   }
   public String getUserAddr2() {
      return userAddr2;
   }
   public void setUserAddr2(String userAddr2) {
      this.userAddr2 = userAddr2;
   }
   public String getUserAddr3() {
      return userAddr3;
   }
   public void setUserAddr3(String userAddr3) {
      this.userAddr3 = userAddr3;
   }
   public Date getRegDate() {
      return regDate;
   }
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }
   public int getVerify() {
      return verify;
   }
   public void setVerify(int verify) {
      this.verify = verify;
   }
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
   
      
}