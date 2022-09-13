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
import service.userInfo.ProfileService;

@WebServlet("/userinfo/*")
public class UserInforController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserInforController() {
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
		case "/userinfo/userinfo_profile":
//			service = new ProfileService();
//			service.execute(request, response);
			viewPage = "userinfo_profile.jsp";
			break;
			
		case "/userinfo/userinfo_update":
			switch(method) {
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
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/userinfo/" + viewPage);
			
			dispatcher.forward(request, response);
		} 
		
	} // end action

}
