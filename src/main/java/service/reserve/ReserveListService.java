package service.reserve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ReserveDAO;
import domain.ReserveDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReserveListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
		
		SqlSession sqlSession = null;
		ReserveDAO dao = null;	
		
		List<ReserveDTO> list = null;
		
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReserveDAO.class);
			
			list = dao.selectByUserId(user.getId());
			// "list" 란  name 으로 request 에 list 저장
			// 즉, request 에 담아서 컨트롤러에 전달되는 셈.

			request.setAttribute("list", list);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}
	}

}
