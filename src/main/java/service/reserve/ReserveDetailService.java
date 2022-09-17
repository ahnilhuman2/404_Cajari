package service.reserve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ParkingDAO;
import domain.ParkingDTO;
import domain.ReserveDAO;
import domain.ReserveDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReserveDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		/*
		 * HttpSession session = request.getSession(); Integer page =
		 * (Integer)session.getAttribute("page"); if(page == null) page = 1;
		 * request.setAttribute("page", page);
		 */
		
		SqlSession sqlSession = null;
		ReserveDAO dao = null;
		
		ReserveDTO reserveDTO = null;
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReserveDAO.class);
			
			// 조회수 증가 + 글읽기
			reserveDTO = dao.selectById(id);
			
			request.setAttribute("dto", reserveDTO);
			request.setAttribute("user", user);
			
			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
	}

}
