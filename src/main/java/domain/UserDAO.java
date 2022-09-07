package domain;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

	// username 으로 특정 회원(user) SELECT <-- DTO
	// 회원가입등에서 필요. (중복가입 여부등)
	List<UserDTO> selectByUser(UserDTO dto) throws SQLException;
	
	// id 로 특정 회원(user) SELECT  <-- DTO
	List<UserDTO> selectById(UserDTO dto) throws SQLException;
	
	// 회원(user) 등록 INSERT <-- DTO
	int regUser(UserDTO dto) throws SQLException;
	
	
} // end DAO

















