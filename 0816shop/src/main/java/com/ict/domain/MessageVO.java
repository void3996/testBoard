package com.ict.domain;

import java.util.Date;

public class MessageVO {
	
		  /*  messageid      number          not null,
		    senderid      varchar2(50)    not null,
		    targetid     varchar2(50)    not null,
		    content     varchar2(500)          not null,
		    regdate     date            default sysdate,
		    readdate	date,
		    fileone	varchar2(500),
		    filetwo	varchar2(500),
		    filethree	varchar2(500),
		    status	char(1)		default ‘N’,
		    primary key(messageid) */
	private int messageid;	//message PK
	private String senderid, targetid, content;	// 발신자, 수신자, 내용
    private Date regdate, readdate;	//보낸날짜, 읽은날짜
    private String fileone, filetwo, filethree, status; //첨부파일123, 읽음 안읽음 상태
	
    public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getReaddate() {
		return readdate;
	}
	public void setReaddate(Date readdate) {
		this.readdate = readdate;
	}
	public String getFileone() {
		return fileone;
	}
	public void setFileone(String fileone) {
		this.fileone = fileone;
	}
	public String getFiletwo() {
		return filetwo;
	}
	public void setFiletwo(String filetwo) {
		this.filetwo = filetwo;
	}
	public String getFilethree() {
		return filethree;
	}
	public void setFilethree(String filethree) {
		this.filethree = filethree;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MessageVO [messageid=" + messageid + ", senderid=" + senderid + ", targetid=" + targetid + ", content="
				+ content + ", regdate=" + regdate + ", readdate=" + readdate + ", fileone=" + fileone + ", filetwo="
				+ filetwo + ", filethree=" + filethree + ", status=" + status + "]";
	}
 
    
}
