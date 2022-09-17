package service.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.UserDAO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class InfoUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String re_password = request.getParameter("re_password");
		String phone_number = request.getParameter("phone_number");
		String car_name = request.getParameter("car_name");
		String car_number = request.getParameter("car_number");

		password = password.trim();
		re_password = re_password.trim();
		phone_number = phone_number.trim();
		car_name = car_name.trim();
		car_number = car_number.trim();
		
		String conPath = request.getContextPath();

		if (!password.equals(re_password)) {

			// redirect를 할때 전달할 값들을 저장
			C.addRedirectAttribute(request, "error", "비밀번호가 일치하지 않습니다");
			C.addRedirectAttribute(request, "phone_number", phone_number);

			// sendRedirect 는 ContextPath명시안하면 도메인 이하로 하기때문에 ContextPath 가 필요하다
			response.sendRedirect(conPath + "/user/infoUpdate");

			return;
		}

		SqlSession sqlSession = null;
		UserDAO dao = null;

		int cnt = 0;

		UserDTO dto = UserDTO.builder().id(id).password(password).phone_number(phone_number).car_name(car_name)
				.car_number(car_number).build();
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(UserDAO.class);

			cnt = dao.update(dto);

			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}

		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto); // id 값을 전달하기 위해

	}

}
