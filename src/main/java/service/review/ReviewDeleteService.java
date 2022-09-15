package service.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.FileDAO;
import domain.FileDTO;
import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		FileDAO fileDao = null;
		
		int cnt = 0;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);	
			fileDao = sqlSession.getMapper(FileDAO.class);
			
			List<FileDTO> fileList = fileDao.selectFilesByWrite(id);
			C.deleteFiles(fileList, request);
			
			// 로그인 한 사용자가 아니면 여기서 redirect 해야 한다
			UserDTO logUser = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
			List<ReviewWriteDTO> list = dao.selectById(id);
			UserDTO writeUser = list.get(0).getUser();
			if(logUser.getId() != writeUser.getId()) {
				response.sendRedirect(request.getContextPath() + "/user/user_rejectAuth");
				return;
			}
			
			cnt = dao.deleteById(id);
			
			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
		
	}

}
