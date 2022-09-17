package domain;

import java.sql.SQLException;
import java.util.List;

public interface QnAWriteDAO {
	// 새글작성
	public abstract int insert(QnAWriteDTO dto) throws SQLException;

	// 특정 id 글 읽어 (SELECT)
	public abstract List<QnAWriteDTO> selectById(int id) throws SQLException;

	// 특정 id 글 조회수 증가 (UPDATE)
	public abstract int incViewCnt(int id) throws SQLException;

	// 전체 SELECT
	public abstract List<QnAWriteDTO> select() throws SQLException;

	// 특정 id 글 수정
	public abstract int update(QnAWriteDTO dto) throws SQLException;

	// 특정 id 글 삭제
	public abstract int deleteById(int id) throws SQLException;

	// 페이징
	// 몇번째(from) 부터 몇개(rows) 를 SELECT
	public List<QnAWriteDTO> selectFromRow(int from, int rows) throws SQLException;

	// 전체 글의 개수
	public int countAll() throws SQLException;
}