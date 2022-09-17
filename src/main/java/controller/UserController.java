package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.C;
import service.Service;
import service.user.LoginService;
import service.user.SignInService;
import service.userInfo.InfoSelectService;
import service.userInfo.InfoUpdateService;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		action(request, response);
	}

	protected void action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n>> " + getClass().getName() + ". 호출");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());

		String method = request.getMethod();

		System.out.println("request: " + uri + " (" + method + ")");
		System.out.println("conPath: " + conPath);
		System.out.println("command: " + command);

		Service service = null;
		String viewPage = null;

		switch (command) {
		case "/user/signin":
			switch (method) {
			case "GET":
				C.retrieveRedirectAttribute(request); // 혹시 redirect로 전달될 것이 있으면 꺼냄 ---> error 제외한 나머지 값
				viewPage = "signin.jsp";
				break;
			case "POST":
				service = new SignInService();
				service.execute(request, response);
				// redirect 가 진행되면 이미 response 가 commit 된다 -> response 가 commit 되지 않은 경우만 jsp
				// forward 진행해야된다
				if (!response.isCommitted()) // <- 실행되지 않은경우만 viewPage singinOk 출력
					viewPage = "signinOk.jsp"; // 회원가입 성공시 성공화면 출력
				break;
			}
			break;

		case "/user/login":
			switch (method) {
			case "GET": // 제자리
				C.retrieveRedirectAttribute(request);
				viewPage = "login.jsp";
				break;
			case "POST": // 로그인 진행 해야됨
				service = new LoginService();
				service.execute(request, response); // 실행하면서 redirect 가 안되고 로그인이 성공하면 ↓↓↓

				if (!response.isCommitted()) {
					// 로그인 성공후 home 으로 redirect 하는 방법
					String redirectUrl = request.getContextPath() + "/home";

					// 혹시 이전에 가고자 했던 url이 존재했다면 해당 url로 redirect 한다.
					String urlPrior = C.retrieveUrlPrior(request);
					if (urlPrior != null)
						redirectUrl = urlPrior;

					response.sendRedirect(redirectUrl);
					return;
				}

				break;
			}
			break;

		case "/user/logout":
			if (method.equals("POST")) {
				request.getSession().removeAttribute(C.PRINCIPAL);
				response.sendRedirect(request.getContextPath() + "/cover");
			}
			break;

		case "/user/user_rejectAuth":
			viewPage = "user_rejectAuth.jsp";
			break;

		case "/user/user_profile":
//			service = new ProfileService();
//			service.execute(request, response);
			viewPage = "userinfo_profile.jsp";
			break;

		case "/user/user_update":
			switch (method) {
			case "GET":
				service = new InfoSelectService();
				service.execute(request, response);
				viewPage = "userinfo_update.jsp";
				break;
			case "POST":
				service = new InfoUpdateService();
				service.execute(request, response);
				viewPage = "userinfo_updateOk.jsp";

				break;
			}
			break;

		} // end switch

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/" + viewPage);

			dispatcher.forward(request, response);
		}

	} // end action

} // end Controller
