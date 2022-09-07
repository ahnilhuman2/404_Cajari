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
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// ※ 이 단계에서 parameter 검증 해야 한다.
		
		Qna_WriteDTO dto = new Qna_WriteDTO();
		dto.set
		
		
	} // end execute()

} // end Command
