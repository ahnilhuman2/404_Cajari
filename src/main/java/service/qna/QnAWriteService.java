package service.qna;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import common.C;
import domain.QnAWriteDAO;
import domain.QnAWriteDTO;
import domain.UserDTO;
import service.Service;

public class QnAWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// 현재 로그인 한 사용자 정보
		UserDTO user = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
		
		// 1.을 DTO에 담기
		QnAWriteDTO dto = new QnAWriteDTO();
		dto.setUser(user);
		dto.setSubject(subject);
		dto.setContent(content);
		
		// DML의 return 값
		int cnt = 0;
		
		// 트랜잭션을 위한 객체. 트랜잭션 - 하나의 작업으로 여러개의 쿼리를 구성하는 것
		SqlSession sqlSession = null;
		
		// 인터페이스
		QnAWriteDAO dao = null;
		
		try {
			// 트랜잭션 시작 --> ???? getInstance가 왜 정의가 안되있지????
			sqlSession = SqlSessionManager.getInstance().openSession();
			// MyBatis 가 생성한 DAO(리턴 타입은 WriteDAO 이다)
			dao = sqlSession.getMapper(QnAWriteDAO.class); 
			// 글 작성하고 auto-generated된 id값을 dto 에 받아옴
			cnt = dao.insert(dto);  
			System.out.println("글작성 성공 " + cnt + " : " + dto.getQ_id());
			
			// 트랜잭션 끝 (null 반환)
			sqlSession.commit();  
		} catch (SQLException e) {
			// 예외 발생되면 rollback 된다.
			e.printStackTrace();  
		} finally {
			// commit 이 실행되지 않으면 rollback 된다.
			if(sqlSession != null) sqlSession.close();
		}
	}

}
