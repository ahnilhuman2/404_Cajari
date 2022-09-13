package service.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewSelectService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		
		List<ReviewWriteDTO> list = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);
			
			// 글읽기
			list = dao.selectById(id);
			
			System.out.println(id);
			System.out.println(request.getSession().getAttribute(C.PRINCIPAL));
			System.out.println(list.get(0).getUser());
			System.out.println(dao.selectById(id));
			
			// 로그인 한 사용자가 아니면 여기서 redirect 해야 한다
			UserDTO logUser = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
			UserDTO writeUser = list.get(0).getUser();
			if(logUser.getId() != writeUser.getId()) {
				response.sendRedirect(request.getContextPath() + "/user/user_rejectAuth");
				return;
			}
			
			request.setAttribute("list", list);
			
			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}

	}

}
