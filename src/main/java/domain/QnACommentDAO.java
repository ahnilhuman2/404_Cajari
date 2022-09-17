package domain;

import java.sql.SQLException;
import java.util.List;

public interface QnACommentDAO {
	// 특정 글 (qna_id) 의 댓글 목록 읽기
	List<QnACommentDTO> selectByWrite(int qna_id) throws SQLException;

	// 댓글 작성 <-- DTO
	public int insert(QnACommentDTO dto) throws SQLException;

	// 댓글(id) 삭제
	public int deleteById(int id) throws SQLException;
}