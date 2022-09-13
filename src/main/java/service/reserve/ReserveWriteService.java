package service.reserve;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ParkingDTO;
import domain.ReserveDAO;
import domain.ReserveDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReserveWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String checkin_time = request.getParameter("checkin_time");
		String parkName = request.getParameter("parking_name");
		
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);

		ReserveDTO dto = new ReserveDTO();
		
		System.out.println(user);
		System.out.println(checkin_time);
		System.out.println(parkName);
		System.out.println("aaaaaaa");
		
		
		
		dto.setUser(user);
		dto.setCheckin_time(checkin_time);
		dto.setParking(null);
		
		int cnt = 0;
		
		SqlSession sqlSession = null;
		ReserveDAO dao = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReserveDAO.class);
			
			cnt = dao.insert(dto);
			System.out.println("예약 성공 " + cnt + " : " + dto.getId());
			
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
