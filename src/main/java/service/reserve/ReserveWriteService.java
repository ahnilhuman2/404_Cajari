package service.reserve;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ParkingDAO;
import domain.ParkingDTO;
import domain.ReserveDAO;
import domain.ReserveDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReserveWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String parkingName = request.getParameter("parking_name");  // 어떤 주차장인지
//	    int userId = Integer.parseInt(request.getParameter("user_id"));  // 누가 작성한 댓글인지
	    String checkin_time = request.getParameter("checkin_time");
		
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);

		ReserveDTO dto = new ReserveDTO();
		
	    
		
		System.out.println(user);
		System.out.println(checkin_time);
		System.out.println("aaaaaaa");
		
		
		
		dto.setUser(user);
		dto.setCheckin_time(checkin_time);
		
		
		int cnt = 0;
		
		SqlSession sqlSession = null;
		ReserveDAO dao = null;
		ParkingDAO parkingDAO = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			parkingDAO = sqlSession.getMapper(ParkingDAO.class);
			dao = sqlSession.getMapper(ReserveDAO.class);
			
			ParkingDTO parkingDTO = parkingDAO.selectByName(parkingName);
			dto.setParking(parkingDTO);
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
