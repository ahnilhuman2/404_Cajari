package domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FileDAO {

    /**
     * 특정 글(writeId) 에 첨부파일(들) 추가 INSERT
     * 글 insert, update 시 사용됨.
     * @param list :  Map<String, Object> 들의 List
     *                      ↓        ↓
     *                   <"source",원본파일명>
     *                   <"file", 저장된파일명>
     * @param writeId : 첨부될 글
     * @return : DML 수행 결과값
     */
	public int insert(List<Map<String, Object>> list, int reviewWriteId) throws SQLException;

	// 특정 글 (writeId) 의 첨부파일(들) SELECT
	public List<FileDTO> selectFilesByWrite(int reviewWriteId) throws SQLException;

	// 특정 첨부파일 (id) 한개 SELECT
	public List<FileDTO> selectById(int id) throws SQLException;

	
	// 선택한 첨부 파일들 select
	// 글 수정에서 사용
	public List<FileDTO> selectByIds(int [] ids) throws SQLException;
	
	// 선택한 첨부파일들 delete
	// 글 수정에서 사용
	public int deleteByIds(int [] ids) throws SQLException;
	
}
