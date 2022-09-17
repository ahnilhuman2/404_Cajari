package service.comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.QnACommentDAO;
import domain.QnACommentDTO;
import domain.QryCommentList;
import service.Service;
import sqlmapper.SqlSessionManager;

public class QnACmtListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int id = Integer.parseInt(request.getParameter("id")); // 글의 id

		QryCommentList obj = new QryCommentList();// response 할 java 객체
		ObjectMapper mapper = new ObjectMapper(); // obj를 jackson data로 변환

		SqlSession sqlSession = null;
		QnACommentDAO dao = null;

		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(QnACommentDAO.class);

			List<QnACommentDTO> list = dao.selectByWrite(id);
			obj.setList(list);
			obj.setCount(list.size());
			obj.setStatus("OK");

			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			obj.setStatus("댓글목록 ERROR: " + e.getMessage());
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}

		String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj); // formmating.java를 jason 문자열로
																							// 만들어 줌
		response.setContentType("application/json; charset=utf-8"); // MIME 설정
		response.getWriter().write(output); // response 보내기

	}

}