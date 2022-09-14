package domain;

import java.sql.SQLException;
import java.util.List;

public interface ReserveDAO {
	// 새글작성
	public abstract int insert(ReserveDTO dto) throws SQLException;
	
	public abstract List<ReserveDTO> selectById(int id) throws SQLException;
}
