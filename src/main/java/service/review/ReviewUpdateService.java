package service.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String subject = request.getParameter("subject");
		String parking_write = request.getParameter("parkingName");
		String content = request.getParameter("content");

		
		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		
		int cnt = 0;
		
		ReviewWriteDTO dto = ReviewWriteDTO.builder()
			.id(id)
			.subject(subject)
			.parking_write(parking_write)
			.content(content)
			.build()
			;
		
		List<ReviewWriteDTO> list = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);
			
			cnt = dao.update(dto);
			
			request.setAttribute("list", list);
			
			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto);
		
	}

}
