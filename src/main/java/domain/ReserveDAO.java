package domain;

import java.sql.SQLException;

public interface ReserveDAO {
	// 새글작성
	public abstract int insert(ReserveDTO dto) throws SQLException;

}
