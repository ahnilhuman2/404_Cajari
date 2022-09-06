package domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FileDAO {
	
	// 특정 review_write에 대한 이미지 업로드
	public List<FileDTO> insertImage(int review_write_id) throws SQLException;

	// 특정 글 (writeId) 의 첨부파일(들) SELECT
	public List<FileDTO> selectFilesByWrite(int review_write_id) throws SQLException;

	// 특정 첨부파일 (id) 한개 SELECT
	public List<FileDTO> selectById(int id) throws SQLException;

	// 선택한 첨부 파일들 select
	// 글 수정에서 사용
	public List<FileDTO> selectByIds(int [] ids) throws SQLException;
	
	// 선택한 첨부파일들 delete
	// 글 수정에서 사용
	public int deleteByIds(int [] ids) throws SQLException;
	
	
}














