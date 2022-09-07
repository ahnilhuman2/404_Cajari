package sqlmapper;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// MyBatis 최초 세팅을 위한 객체
public class SqlSessionManager {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	
	// static 초기화 블럭은 최초에 단 한번 수행
	static {
		String resource = "sqlmapper/Configuration.xml";
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("SqlSessionFactory 생성");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public static SqlSessionFactory getInstance() {
		return sqlSessionFactory;
	}
	

}









