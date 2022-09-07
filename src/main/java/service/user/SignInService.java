package service.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import org.apache.catalina.startup.UserConfig;
import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.UserDAO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class SignInService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 회원가입페이지 signIn.jsp 파일에서 작성한 name 값 그대로 받아오기
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re_password");
		String phone_number = request.getParameter("phone_number");
		String car_name = request.getParameter("car_name");
		String car_number = request.getParameter("car_number");
		
		// 작성한 텍스트 공백제거
		username = username.trim();
		password = password.trim();
		re_password = re_password.trim();
		name = name.trim();
		
		String conPath = request.getContextPath();
		
		// password 와 re_password 확인 코드
		if(!password.equals(re_password)) {
			
			// redirect를 할때 전달할 값들을 저장
			C.addRedirectAttribute(request, "error", "비밀번호가 일치하지 않습니다");
			C.addRedirectAttribute(request, "username", username); // 비밀번호를 제외한 나머지 값 전달 후 출력
			C.addRedirectAttribute(request, "name", name);
			C.addRedirectAttribute(request, "phone_number", phone_number);
			
			// sendRedirect 는 ContextPath명시안하면 도메인 이하로 하기때문에 ContextPath 가 필요하다
			response.sendRedirect(conPath + "/user/signin"); 
			
			return;
		}
		
		SqlSession sqlSession = null;
		UserDAO dao = null;
		
		int cnt = 0;
		
		UserDTO dto = UserDTO.builder()
				.name(name)
				.username(username.toUpperCase()) // 대문자로 저장함(toUpperCase())
				.password(password)
				.phone_number(phone_number)
				.car_name(car_name)
				.car_number(car_number)
				.build();
				
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(UserDAO.class);
			
			// 이미 회원가입한 아이디(username) 인 경우
			List<UserDTO> list = dao.selectById(dto);
			if(list.size() > 0) {
				// sendRedirect 는 ContextPath명시안하면 도메인 이하로 하기때문에 ContextPath 가 필요하다
				response.sendRedirect(conPath + "/user/signin"); 
				
				// redirect를 할때 전달할 값들을 저장
				C.addRedirectAttribute(request, "error", "이미 존재하는 아이디입니다");
				C.addRedirectAttribute(request, "username", username); // 비밀번호를 제외한 나머지 값 전달 후 출력
				C.addRedirectAttribute(request, "name", name);
				C.addRedirectAttribute(request, "phone_number", phone_number);
				
				
				return;
			}
			
			// 새로운 회원 등록 코드 UserDAO.xml 참조
			cnt = dao.regUser(dto);			
			
			sqlSession.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}
		
		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto); // <- auto-generated key (id) 담겨서
		
	}

}
