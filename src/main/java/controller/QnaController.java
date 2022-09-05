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
import service.review.DeleteService;
import service.review.DetailService;
import service.review.DownloadService;
import service.review.ListService;
import service.review.SelectService;
import service.review.UpdateService;
import service.review.WriteService;

@WebServlet("/qna/*")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaController() {
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
		System.out.println("\n>> " + getClass().getName() + ".action() 호출");
		
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
		case "/qna/qna_write":
			if(C.securityCheck(request, response, new String[] {"ROLE_MEMBER"})) {				
				switch(method) {
				case "GET":
					viewPage = "qna_write.jsp";
					break;
				case "POST":
					service = new service.review.WriteService();
					service.execute(request, response);
					viewPage = "qna_writeOk.jsp";
					break;
				}
			}
			break;
			
		case "/qna/qna_list":
			service = new ListService();
			service.execute(request, response);
			viewPage = "qna_list.jsp";
			break;
			
		case "/qna/qna_detail":  // 로그인한 사람만 접근 가능
			if(C.securityCheck(request, response, null)) {				
				service = new DetailService();
				service.execute(request, response);
				viewPage = "qna_detail.jsp";
			}
			break;
			
		case "/qna/qna_update":  // ROLE_MEMBER + 작성자 만 접근 가능
			if(C.securityCheck(request, response, new String[] {"ROLE_MEMBER"})) {				
				switch(method) {
				case "GET":
					service = new SelectService();  // Service 안에서 작성자 여부 판단. 작성자 아니면 redirect 발생
					service.execute(request, response);
					
					if(!response.isCommitted()) { // 위에서 redirect 되면 forward 진행 안함.				
						viewPage = "qna_update.jsp";
					}
					break;
				case "POST":
					service = new UpdateService();
					service.execute(request, response);
					viewPage = "qna_updateOk.jsp";
					break;
				}
			}
			break;
			
		case "/review/review_delete":
			if(C.securityCheck(request, response, new String[] {"ROLE_MEMBER"})) {				
				switch(method) {
				case "POST":
					service = new DeleteService();   // 작성자가 아닌경우 Service 안에서 redirect 발생
					service.execute(request, response);
					if(!response.isCommitted()) {						
						viewPage = "qna_deleteOk.jsp";
					}
					break;
				}
			}
			break;
			
		// 페이징
		// pageRows 변경시 동작
		case "/board/pageRows":
		    int page = Integer.parseInt(request.getParameter("page"));
		    Integer pageRows = Integer.parseInt(request.getParameter("pageRows"));
		    request.getSession().setAttribute("pageRows", pageRows);
			response.sendRedirect(request.getContextPath() + "/board/list?page=" + page);
			break;
		
		} // end switch
		
		// 위에서 결정된 뷰 페이지 (viewPage) 로 forward 해줌
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/review/" + viewPage);
			
			dispatcher.forward(request, response);
		}
	
	
	} // end action()

} // end Controller

