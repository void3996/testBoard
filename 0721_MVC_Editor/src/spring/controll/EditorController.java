package spring.controll;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.vo.ParamVO;
import data.vo.TestVO;
import mybatis.dao.TestDAO;
import spring.util.FileUploadUtil;

@Controller
public class EditorController {

	@Autowired
		private HttpServletRequest request;
	
	@Autowired
	private ServletContext application;
	
	//DAO가 필요함
	
	@Autowired
	private TestDAO t_dao;
	//setter 인젝션을 쓸거야 여기 세터 만들어야 하고 물론 다 그런게 아니라 어떤 개발자들은 여기서 명시를 해요 private String f_path = "/upload"; 이렇게
	
	private String f_path;	//"/upload	자바파일이 바뀌면 클래스파일이 바뀐다. 
	//Controller-servlet.xml 여기서 작업
	public void setF_path(String f_path) {
		this.f_path = f_path;
	}

	@RequestMapping(value="/write.okj",
			method = RequestMethod.GET)
	public String write() {
		return "write";
	}
	
	@RequestMapping(value = "/write.okj",
			method = RequestMethod.POST)
	public ModelAndView write(TestVO vo) {
		ModelAndView mv = new ModelAndView();
				
				
				 System.out.println(vo.getSubject());
				 System.out.println(vo.getContent());
				 System.out.println(vo.getFile());
				 
				//파일 첨부가 되었다면 파일명을 얻어내어
				//vo의 멤버변수인 file_name에 저장
				MultipartFile mf = vo.getFile();
				if(mf != null) {
					/*
					 * String fn = mf.getOriginalFilename(); vo.setFile_name(fn);
					 */
					//파일들이 저장될 폴더를 절대 경로로 만든다.
					String path = application.getRealPath(f_path);
					//파일명 얻기
					String fileName = mf.getOriginalFilename();
					//여기까지 왔다면 파일네임이 있으니까 null 값 걱정 안해도 된다.
					//파일이 이미 저장된 경우인지를 알아내어 파일명을 변경하자
															//이 경로에 중복된 값이 있는지 없는지 판단하여 깂이 두번째 로 들어감
					fileName = FileUploadUtil.checkSameFileName(fileName, path);
					//파일을 서버에 저장
					try {
						mf.transferTo(new File(path, fileName));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					vo.setFile_name(fileName);
				}
				//ip 얻기
				String ip = request.getRemoteAddr();
				vo.setIp(ip);
				
				
				/*
				 * if(vo.getFile() != null) { String fn = vo.getFile().getOriginalFilename(); }
				 */
				//달력
				//2020-7-23 형태로 나온다
				/*
				 * Calendar now = Calendar.getInstance(); StringBuffer sb = new StringBuffer();
				 * sb.append(now.get(Calendar.YEAR)); sb.append("-");
				 * sb.append(now.get(Calendar.MONTH)+1); sb.append("-");
				 * sb.append(now.get(Calendar.DAY_OF_MONTH));
				 * 
				 * //2020-07-23 형태로 나온다 Date date = new Date(System.currentTimeMillis());
				 */
				
				/*
				 * System.out.println(sb.toString()); System.out.println(date.toString());
				 */
				
				//DB에 저장
				boolean chk = t_dao.addData(vo);
				
				mv.addObject("chk", chk);
				mv.setViewName("write_res");
				
				return mv;
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
