package service.qna;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import sqlmapper.SqlSessionManager;

import domain.QnAWriteDAO;
import domain.QnAWriteDTO;
import service.Service;

public class QnAUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// ※ 이 단계에서 parameter 검증 해야 한다.
		
		SqlSession sqlSession = null;
		QnAWriteDAO dao = null;		
		
		int cnt = 0;
		
		QnAWriteDTO dto = QnAWriteDTO.builder()
				.q_id(id)
				.subject(subject)
				.content(content)
				.build()
				;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(QnAWriteDAO.class);
			
			cnt = dao.update(dto);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto);  // id 값을 전달하기 위해


	}

}
