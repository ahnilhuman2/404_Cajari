package domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FileDAO {

	public int insert(List<Map<String, Object>> list, int reviewWriteId) throws SQLException;

	// 특정 글 (writeId) 의 첨부파일(들) SELECT
	public List<FileDTO> selectFilesByWrite(int reviewWriteId) throws SQLException;

	// 특정 첨부파일 (id) 한개 SELECT
	public List<FileDTO> selectById(int id) throws SQLException;
}
