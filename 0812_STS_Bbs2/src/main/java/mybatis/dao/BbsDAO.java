package mybatis.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.BbsVO;

public class BbsDAO {
	@Autowired
	private SqlSessionTemplate template;
	
	//전체 게시물 수를 반환하는 기능
	public int getTotalCount(String bname) {
		
		return template.selectOne("bbs.totaCount", bname);
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
