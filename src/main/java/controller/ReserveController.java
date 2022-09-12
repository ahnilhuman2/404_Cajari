package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
import service.reserve.ReserveDeleteService;
import service.reserve.ReserveDetailService;
import service.reserve.ReserveListService;
import service.review.ReviewDeleteService;
import service.review.ReviewDetailService;
import service.review.ReviewListService;
import service.review.ReviewSelectService;
import service.review.ReviewUpdateService;
import service.review.ReviewWriteService;

@WebServlet("/reserve/*")
public class ReserveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReserveController() {
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
		System.out.println(getClass().getName() + " action");
		
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
		case "/reserve/reserve_write":
			switch(method) {
			case "GET":
				viewPage = "reserve_write.jsp";
				break;
				
			case "POST":
				viewPage = "reserve_writeOk.jsp";
				break;
			}
			break;
		case "/reserve/reserve_list":
			service = new ReserveListService();
			service.execute(request, response);
			viewPage = "reserve_list.jsp";
			break;
			
		case "/reserve/reserve_detail":
			service = new ReserveDetailService();
			service.execute(request, response);
			viewPage = "reserve_detail.jsp";
			break;
			
		case "/reserve/reserve_delete":
			switch(method) {
			case "POST":
				service = new ReserveDeleteService();
				service.execute(request, response);
				viewPage = "reserve_deleteOk.jsp";
				break;
			}
			break;
	
		} // end switch
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/reserve/" + viewPage);
			
			dispatcher.forward(request, response);
		}
	}
	
}
