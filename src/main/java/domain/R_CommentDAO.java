package domain;

import java.sql.SQLException;
import java.util.List;

public interface R_CommentDAO {
	// 특정 글 (write_id) 의 댓글 목록 읽기
	List<R_CommentDTO> selectByWrite(int write_id) throws SQLException;

	// 댓글 작성 <-- DTO
	public int insert(R_CommentDTO dto) throws SQLException;

	// 댓글(id) 삭제
	public int deleteById(int id) throws SQLException;
}
