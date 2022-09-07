package domain;

import java.sql.SQLException;
import java.util.List;

public interface Q_CommentDAO {
	// 특정 글 (write_id) 의 댓글 목록 읽기
	List<Q_CommentDTO> selectByWrite(int qna_id) throws SQLException;

	// 댓글 작성 <-- DTO
	public int insert(Q_CommentDTO dto) throws SQLException;

	// 댓글(id) 삭제
	public int deleteById(int id) throws SQLException;
}
