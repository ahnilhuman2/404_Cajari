package domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FileDAO {

	public int insert(List<Map<String, Object>> list, int reviewWriteId) throws SQLException;
	
}
