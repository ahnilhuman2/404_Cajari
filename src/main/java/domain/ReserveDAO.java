package domain;

import java.sql.SQLException;
import java.util.List;

public interface ReserveDAO {
	// 새글작성
	public abstract int insert(ReserveDTO dto) throws SQLException;
	
	// 특정 id 글 읽어 (SELECT)
	public abstract List<ReserveDTO> selectById(int id) throws SQLException;
	
	
	public abstract List<ReserveDTO> selectByUser(int id) throws SQLException;
		
	
	// 전체 SELECT
	public abstract List<ReserveDTO> select() throws SQLException;
}
