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
import service.user.SignInService;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		action(request, response);
	}
	
	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		switch(command) {
		case "/user/signin":
			switch(method) {
			case "GET":
				C.retrieveRedirectAttribute(request); // 혹시 redirect로 전달될 것이 있으면 꺼냄 ---> error 제외한 나머지 값
				viewPage = "signin.jsp";
				break;
			case "POST":
				service = new SignInService();
				service.execute(request, response);
				// redirect 가 진행되면 이미 response 가 commit 된다 -> response  가 commit 되지 않은 경우만 jsp forward 진행해야된다
				if(!response.isCommitted()) // <- 실행되지 않은경우만 viewPage singinOk 출력
					viewPage = "signinOk.jsp"; // 회원가입 성공시 성공화면 출력
				break;
			}
			break;
			
		case "/user/login":
			viewPage = "login.jsp";
			break;
			
		case "/user/logout":
			
			break;
			
		} // end switch
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/user/" + viewPage);
			
			dispatcher.forward(request, response);
		} 
		
	} // end action

} // end Controller
