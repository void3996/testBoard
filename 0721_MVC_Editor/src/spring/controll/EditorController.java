package spring.controll;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import data.vo.ParamVO;
import spring.util.FileUploadUtil;

@Controller
public class EditorController {

	@Autowired
		private HttpServletRequest request;
	
	@Autowired
	private ServletContext application;
	
	//DAO가 필요함
		
	
		@RequestMapping("/write.okj")
		public String write() {
			return "write";
		}
		//에디터에서 이미지 파일이 들어가면 호출되는 곳!
		@RequestMapping("/saveImage.okj")
		@ResponseBody
		public Map<String, String> saveImg(ParamVO pvo){
			Map<String, String> map = new Hashtable<String, String>();
			//파일을 서버에 저장한다.
			MultipartFile mf = pvo.getF();	//보내온 파일
			String fname = null;
			
			
			if (mf.getSize() > 0 && mf.getOriginalFilename().trim().length() > 0) {
				//파일을 저장할 정확한 위치를 절대 경로화 시킨다.
				String r_path = application.getRealPath("/editor_img");
				fname = mf.getOriginalFilename();
				
				//첨부파일을 저장하기 전에 같은 이름의 파일이 존재하는지를 먼저 알아봐야 한다.
				fname = FileUploadUtil.checkSameFileName(fname, r_path);
				//여기까지 왔다면 중복되지 않는 파일명을 가졌을 것이다.
				//이때 파일을 저장한다.
				try {
					mf.transferTo(new File(r_path, fname));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//System.out.println(pvo.getF().getName()+"~~~~~~~");
			//현재 서버 경로
			String path = request.getContextPath();
			//System.out.println(path);
									//getName은 파라미터
			//fname = pvo.getF().getOriginalFilename();
			
			map.put("path", path+"/editor_img");
			map.put("fname", fname);
					
			return map;	//비동기식 통신의 결과를 반환한다.
		}
}
