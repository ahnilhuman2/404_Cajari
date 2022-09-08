package service.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.UserDAO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class LoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//login.jsp 로그인 폼에 입력된 name 값
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		username = username.trim();
		password = password.trim();
		
		String conPath = request.getContextPath();
		
		UserDTO dto = new UserDTO();
		dto.setUsername(username.toUpperCase()); // 대문자로 저장된 아이디와 로그인한 아이디 대문자로 비교
		
		SqlSession sqlSession = null;
		UserDAO dao = null;

		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(UserDAO.class);
			
			// 존재하지 않는 아이디인 경우
			List<UserDTO> list = dao.selectByUser(dto);
			if(list.size() == 0) {
				
				C.addRedirectAttribute(request, "error", "존재하지 않는 아이디입니다.");
				C.addRedirectAttribute(request, "username", username);
				
				response.sendRedirect(conPath + "/user/login");
				return;
			}
			
			// 패스워드가 다른 경우
			dto = list.get(0); // 해당아이디 꺼내오기
			if(!dto.getPassword().equals(password)) { // 사용자 입력 password랑 비교
				
				C.addRedirectAttribute(request, "error", "비밀번호가 일치하지 않습니다.");
				C.addRedirectAttribute(request, "username", username);
				
				response.sendRedirect(conPath + "/user/login");
				return;
			}
			
			// 로그인 진행 C.java -> PRINCIPAL = "PRINCIPAL";
			HttpSession session = request.getSession();
			session.setAttribute(C.PRINCIPAL, dto); // <- 회원에 관한 모든 정보는 dto 에 담겨있다
			
			
			sqlSession.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}
		
		
//		request.setAttribute("result", cnt);
//		request.setAttribute("dto", dto); // <- auto-generated key (id) 담겨서
		
	}

}
