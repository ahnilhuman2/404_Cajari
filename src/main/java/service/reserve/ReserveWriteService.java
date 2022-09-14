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
		
		int parkingId = Integer.parseInt(request.getParameter("parking_id"));  // 어떤 주차장인지
//	    int userId = Integer.parseInt(request.getParameter("user_id"));  // 누가 작성한 댓글인지
	    String checkin_time = request.getParameter("checkin_time");
		
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);

		ReserveDTO dto = new ReserveDTO();
		
		ParkingDTO park = new ParkingDTO();
	    park.setParking_name(null);
	    
		
		System.out.println(user);
		System.out.println(checkin_time);
		System.out.println(parkingId);
		System.out.println(park);
		System.out.println("aaaaaaa");
		
		
		
		dto.setUser(user);
		dto.setCheckin_time(checkin_time);
		dto.setParking(park);
		
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
