package mybatis.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mybatis.vo.BbsVO;
@Component
public class BbsDAO {
	@Autowired
	private SqlSessionTemplate template;
	
	//전체 게시물 수를 반환하는 기능
	public int getTotalCount(String bname) {
		
		return template.selectOne("bbs.totalCount", bname);
	}
	//원글저장
	public boolean add(BbsVO vo) {
		boolean value = false;
		
		int cnt = template.insert("bbs.add", vo);
		if(cnt > 0)
			value = true;
		
		return value;
	}
	
	//보기 기능
	public BbsVO getBbs(String seq, String bname) {
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("seq", seq);
		map.put("bname", bname);
		
		return template.selectOne("bbs.getBbs", map);
	}
	//답변 저장하기 전에 순번을 조정하는 기능
	public void updateSunbun(String ref, String sunbun) {
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("ref", ref);
		map.put("sunbun", sunbun);
		
		template.update("bbs.updateSunbun", map);
	}
	//답변 저장
	public boolean addAns(BbsVO vo) {
		boolean value = false;
		
		int cnt = template.insert("bbs.addAns", vo);
		if(cnt > 0)
			value=true;
		
		return value;
	}
	
	//조회수 증가(인자로 기본키 값이 필요함)
	public void hit(String seq) {
		template.update("bbs.hit", seq);
	}
	//원글 수정
	public boolean edit(BbsVO vo) {
		boolean value = false;
		
		int cnt = template.update("bbs.edit", vo);
		if(cnt > 0)
			value = true;
		
		return value;
	}
	
	public void edit(Map<String, Object> map) {
		template.update("bbs.edit", map);
	}
	//삭제
	public boolean del(String seq, String pwd) {
		boolean value = false;
		
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("seq", seq);
		map.put("pwd", pwd);
		
		int cnt = template.update("bbs.del", map);
		if(cnt > 0)
			value = true;
		
		return value;
		
	}
	
	//원하는 페이지에 표현할 게시물 목록 반환
	public BbsVO[] getList(String bname, int begin, int end) {
		
		Map<String, Object> map = new Hashtable<String, Object>();
		
		map.put("bname", bname);
		map.put("begin", begin);
		map.put("end", end);
		
		List<BbsVO> list = template.selectList("bbs.list", map);
		
		BbsVO[] ar = null;
		
		if(list != null && list.size() > 0) {
			ar = new BbsVO[list.size()];
			list.toArray(ar);
			
		}
		return ar;
	}
 
}
