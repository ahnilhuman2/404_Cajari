package domain;

import java.sql.SQLException;
import java.util.List;

public interface ReserveDAO {
	// 새글작성
	int insert(ReserveDTO dto) throws SQLException;
	ReserveDTO selectById(int id) throws SQLException; //유니큐니까 list 안쓴다
	List<ReserveDTO> selectByUserId(int userId) throws SQLException;
}
