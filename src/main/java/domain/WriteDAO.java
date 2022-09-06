package domain;

import java.sql.SQLException;
import java.util.List;

/*
 * DAO : Data Access Object
 *   Data source 와의 접근및 작업 전담
 *   
 *   데이터 동작과 관련된 모든 것들을 작성 (메소드 외..)
 */
public interface WriteDAO {
	// 새글 작성 <-- DTO
	public abstract int insert(WriteDTO dto) throws SQLException;
	
	// 특정 id 글 읽어오기 (SELECT)
	public abstract List<WriteDTO> selectById(int id) throws SQLException;
	
	// 특정 id 글 조회수 증가 (UPDATE)
	public abstract int incViewCnt(int id) throws SQLException;
	
	// 전체 SELECT
	public abstract List<WriteDTO> select() throws SQLException;
	
	
	// 특정 id 글 수정 (제목, 내용)
	public abstract int update(WriteDTO dto) throws SQLException;
	
	// 특정 id 글 삭제
	public abstract int deleteById(int id) throws SQLException;
	
	// 페이징 
	// 몇번째(from) 부터 몇개(rows) 를 SELECT
	public List<WriteDTO> selectFromRow(int from, int rows) throws SQLException;
	
	// 전체 글의 개수
	public int countAll() throws SQLException;	
	
} // end DAO



















