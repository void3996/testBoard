package data.vo;

import org.springframework.web.multipart.MultipartFile;

public class ParamVO {
	
	private MultipartFile f;	//f는 파라미터 명과 맞췄다.

	public MultipartFile getF() {
		return f;
	}

	public void setF(MultipartFile f) {
		this.f = f;
	}
	
	
}
