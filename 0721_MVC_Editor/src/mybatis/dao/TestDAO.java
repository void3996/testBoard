package mybatis.dao;

import org.mybatis.spring.SqlSessionTemplate;

import data.vo.TestVO;

public class TestDAO {

	private SqlSessionTemplate ss;
	
	public TestDAO() {
		System.out.println("-----");
	}
	
	public void setSs(SqlSessionTemplate ss) {
		this.ss = ss;
	}
	
	public boolean addData(TestVO vo) {
		boolean value = false;
		
		int cnt = ss.insert("test.add", vo);
		if(cnt > 0)
			value = true;
		
		return value;
	}
}
