package service.review;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ParkingDTO;
import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String subject = request.getParameter("subject");
		String parkingName = request.getParameter("parkingName");
		String content = request.getParameter("content");
		
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
		String parking = parkingName;

		ReviewWriteDTO dto = new ReviewWriteDTO();
		
		System.out.println(user);
		System.out.println(parking);
		System.out.println(subject);
		System.out.println(parkingName);
		System.out.println(content);
		
		dto.setUser(user);
		dto.setParking(null);
		dto.setSubject(subject);
		dto.setContent(content);
		
		int cnt = 0;
		
		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);
			
			cnt = dao.insert(dto);
			System.out.println("글작성 성공 " + cnt + " : " + dto.getId());
			
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
