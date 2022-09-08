package service.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.C;
import domain.FileDAO;
import domain.ParkingDTO;
import domain.Qna_WriteDAO;
import domain.UserDTO;
import domain.Review_WriteDAO;
import domain.Review_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Re_WriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 입력값 받아오기
		String name =  request.getParameter("name");
		String subject =  request.getParameter("subject");
		String parking_name =  request.getParameter("parking_name");
		String content =  request.getParameter("content");
		
		Review_WriteDTO dto = new Review_WriteDTO();
		ParkingDTO pdto = new ParkingDTO();
//		dto.setName(name);
		dto.setSubject(subject);
		pdto.setParking_name(parking_name);
		dto.setContent(content);
		
		int cnt = 0;
		
		SqlSession sqlSession = null; // 트랜잭션을 위한 객체
		Review_WriteDAO dao = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(Review_WriteDAO.class); // MyBaits 가 생성한 DAO
			
			cnt = dao.insert(dto); // 글 작성하고 auto-generated 된 id 값을 dto 에 받아온다.
			System.out.println("글작성 성공" + cnt + " : " + dto.getId());
			
			sqlSession.commit(); // 트랜잭션 커밋. commit() 이 실행되지 않으면.. 아래 close() 시 rollback 된다
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생되면 rollback 된다.
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto); // 담는이유는 auto-generated key(id) 때문에
		
	} // end execute()

} // end Command

