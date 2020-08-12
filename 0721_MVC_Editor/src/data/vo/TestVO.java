package data.vo;

import org.springframework.web.multipart.MultipartFile;

public class TestVO {
	private String 	idx, 
					subject,	//제목
					content,	//내용
					file_name,	//파일명
					reg_date,	//sysdate로 처리예정	
					ip;			//ip 받아서 입력될 예정
	
	//VO 객체 멤버변수 이름과 파라미터 이름을 동일하게 하는게 편하다
	
	private MultipartFile file;	//jsp name과 동일하게 한다.
	

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
