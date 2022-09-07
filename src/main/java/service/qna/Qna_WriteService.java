package service.qna;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.UserDTO;
import domain.Qna_WriteDAO;
import domain.Qna_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Qna_WriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 입력한 값을 받아오기
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// ※ 이 단계에서 parameter 검증 해야 한다.
		
		// 현재 로그인 한 사용자 정보
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
		
		// 위 값들을 DTO에 담기
		Qna_WriteDTO dto = new Qna_WriteDTO();
		dto.setUser_id(user);
		dto.setSubject(subject);
		dto.setContent(content);
		
		int cnt = 0;
		
		SqlSession sqlSession = null; // 트랜잭션을 위한 객체
		Qna_WriteDAO dao = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(Qna_WriteDAO.class); // MyBaits 가 생성한 DAO
			
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
