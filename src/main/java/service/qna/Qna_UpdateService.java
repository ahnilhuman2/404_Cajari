package service.qna;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import domain.Qna_WriteDAO;
import domain.Qna_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Qna_UpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 입력한 값 (parameter) 받아오기
		int id = Integer.parseInt(request.getParameter("id"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// ※ 이 단계에서 parameter 검증 해야 한다.
		
		SqlSession sqlSession = null;
		Qna_WriteDAO dao = null;
		
		int cnt = 0;
		
		// Builder 사용한 생성
		Qna_WriteDTO dto = Qna_WriteDTO.builder()
			.id(id)
			.subject(subject)
			.content(content)
			.build()
			;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(Qna_WriteDAO.class);
			
			cnt = dao.update(dto);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto); // id 값을 전달하기 위해

	}

}
