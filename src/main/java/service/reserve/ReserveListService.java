package service.reserve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import domain.ReserveDAO;
import domain.ReserveDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReserveListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		SqlSession sqlSession = null;
		ReserveDAO dao = null;	
		
		List<ReserveDTO> list = null;
		
		System.out.println(id);
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReserveDAO.class);
			
			list = dao.selectByUser(id);
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
