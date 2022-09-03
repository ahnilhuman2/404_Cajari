package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
//		Service service = null;
		String viewPage = null;
		
		switch(command) {
		case "/user/login":
			viewPage = "login.jsp";
			break;
		} // end switch
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/user/" + viewPage);
			
			dispatcher.forward(request, response);
		}
		
	} // end action

} // end Controller
