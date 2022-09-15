package domain;

import java.sql.SQLException;
import java.util.List;

public interface ParkingDAO {
	
	// id 기준으로 데이터 가져오기
	public abstract List<ParkingDTO> selectById(int id) throws SQLException;
	ParkingDTO selectByName(String name) throws SQLException;
}
