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
import domain.Qna_WriteDAO;
import domain.Qna_WriteDTO;
import domain.Review_WriteDAO;
import domain.UserDTO;
import domain.Review_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Re_DeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		// ※ 이 단계에서 parameter 검증 해야 한다.
		
		SqlSession sqlSession = null;
		Review_WriteDAO dao = null;
		
		int cnt = 0;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(Review_WriteDAO.class);
			
			// 로그인한 사용자가 아니면 여기서 redirect 해야 한다
			UserDTO loggedUser = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
			List<Review_WriteDTO> list = dao.selectById(id);
			UserDTO re_writeUser = list.get(0).getUser_id();
			if(loggedUser.getId() != re_writeUser.getId()) {
				response.sendRedirect(request.getContextPath() + "/user/rejectAuth");
				return;
			}	
			
			cnt = dao.deleteById(id);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
//		int id = Integer.parseInt(request.getParameter("id"));
//		// ※ 이 단계에서 parameter 검증 해야 한다.
//		
//		SqlSession sqlSession = null;
//		Review_WriteDAO dao = null;
//		FileDAO fileDao = null;
//		
//		int cnt = 0;
//		
//		try {
//			sqlSession = SqlSessionManager.getInstance().openSession();
//			dao = sqlSession.getMapper(Review_WriteDAO.class);
//			fileDao = sqlSession.getMapper(FileDAO.class);
//			
//			// 첨부파일 먼저 삭제
//			List<FileDTO> fileList = fileDao.selectFilesByWrite(id);
//			C.deleteFiles(fileList, request);
//			
//			// 로그인한 사용자가 아니면 여기서 redirect 해야 한다
//			UserDTO loggedUser = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
//			List<Review_WriteDTO> list = dao.selectById(id);
//			UserDTO re_writeUser = list.get(0).getUser_id();
//			if(loggedUser.getId() != re_writeUser.getId()) {
//				response.sendRedirect(request.getContextPath() + "/user/rejectAuth");
//				return;
//			}	
//			
//			cnt = dao.deleteById(id);
//			
//			sqlSession.commit();
//		} catch (SQLException e) {  
//			e.printStackTrace();
//		} finally {
//			if(sqlSession!= null) sqlSession.close();
//		}
//		
//		request.setAttribute("result", cnt);

	}

}
