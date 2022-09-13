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
import service.review.ReviewDeleteService;
import service.review.ReviewDetailService;
import service.review.ReviewListService;
import service.review.ReviewSelectService;
import service.review.ReviewUpdateService;
import service.review.ReviewWriteService;

@WebServlet("/review/*")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // POST 방식은 한글인코딩 필수
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
		case "/review/review_write":
			if(C.securityCheck(request, response, new String[] {"PREMIUM"})) {
				switch(method) {
				case "GET":
					viewPage = "review_write.jsp";
					break;
					
				case "POST":
					service = new ReviewWriteService();
					service.execute(request, response);
					viewPage = "review_writeOk.jsp";
					break;
				}
			}
			break;
		case "/review/review_list":
			service = new ReviewListService();
			service.execute(request, response);
			viewPage = "review_list.jsp";
			break;
			
		case "/review/review_detail": // 로그인한 사람만 접근 가능
			if(C.securityCheck(request, response, null)) {
				service = new ReviewDetailService();
				service.execute(request, response);
				viewPage = "review_detail.jsp";
			}
			break;
			
		case "/review/review_update": // PREMIUM and 작성자
			if(C.securityCheck(request, response, new String[] {"PREMIUM"})) {
				switch(method) {
				case "GET":
					service = new ReviewSelectService(); // Service 안에서 작성자 여부 판단 작성자가 아니면 redirect 발생
					service.execute(request, response);
					
					if(!response.isCommitted()) { // 위에서 redirect 되면 forward 진행 안함
						viewPage = "review_update.jsp";						
					}
					break;
				case "POST":
					service = new ReviewUpdateService();
					service.execute(request, response);
					viewPage = "review_updateOk.jsp";
					break;
				}
			}
			break;
			
		case "/review/review_delete":
			if(C.securityCheck(request, response, new String[] {"PREMIUM"})) {
				switch(method) {
				case "POST":
					service = new ReviewDeleteService();
					service.execute(request, response);
					if(!response.isCommitted()) {
						viewPage = "review_deleteOk.jsp";
					}
					break;
				}
			}
			break;
			
		case "/review/pageRows":
			int page = Integer.parseInt(request.getParameter("page"));
			Integer pageRows = Integer.parseInt(request.getParameter("pageRows"));
			request.getSession().setAttribute("pageRows", pageRows);
			response.sendRedirect(request.getContextPath() + "/review/review_list?page=" + page);
			break;
			
		} // end switch
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/review/" + viewPage);
			
			dispatcher.forward(request, response);
		}
	}
}
