package mybatis.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.BbsVO;
import mybatis.vo.CommVO;

public class BbsDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	//전체 게시물 수를 반환하는 기능
	public int getTotalCount(String bname) {
		return template.selectOne("bbs.totalCount", bname);
	}
	
	//원하는 페이지의 게시물 목록 기능 - list.jps에서 필요
	//정확히는 ListController에서 호출하여 list.jsp에서 표현
	public BbsVO[] getList(String begin, String end, String bname) {
		BbsVO[] ar = null;
		//맵퍼(bbs.list)를 호출하기 위해 Map구조 생성!
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("begin", begin);
		map.put("end", end);
		map.put("bname", bname);
		
		List<BbsVO> list = template.selectList("bbs.list", map);
		
		if(list != null && list.size() > 0) {
			ar = new BbsVO[list.size()];
			list.toArray(ar);
		}
		
		return ar;
	}
	//원글을 저장하는 기능 - WriteController에서 호출
	public int add(BbsVO vo) {
		int cnt = template.insert("bbs.add", vo);
		return cnt;
		
	}
	//보기 기능을 위한 게시물 검색 - ViewController에서 호출
	public BbsVO getBbs(Map<String, String> map) {				
		return template.selectOne("bbs.getBbs", map);
	}
	//인자로 b_idx를 받아 조회수 증가하는 기능
	public void hit(String b_idx) {
		template.update("bbs.hit", b_idx);
	}
	//댓글 저장
	public boolean addAns(CommVO cvo) {
		boolean value = false;
		
		int cnt = template.insert("bbs.add_ans", cvo);
		//저장에 성공했다면 cnt 값이 1이다.
		if(cnt > 0)
			value = true;
		
		return value;
		//컨트롤러에서 호출하고 컨트롤러는 뷰에서 호출하고
	}
	
	//원글편집
	public boolean edit(BbsVO vo) {
		boolean value = false;
		
		int cnt = template.update("bbs.edit", vo);
		if(cnt > 0)
			value = true;
		
		return value;

	}
	//수정하는 기능
	public void edit(Map<String, String> map) {
		
		template.update("bbs.edit", map);
	}
	
	
}
