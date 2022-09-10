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
import service.qna.QnADeleteService;
import service.qna.QnADetailService;
import service.qna.QnAListService;
import service.qna.QnASelectService;
import service.qna.QnAUpdateService;
import service.qna.QnAWriteService;


@WebServlet("/qna/*")
public class QnAController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnAController() {
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
		case "/qna/qna_write":
			// 로그인한 사람만 작성가능
//			if(C.securityCheck(request, response, null)) {
				switch(method) {
				case "GET":
					viewPage = "qna_write.jsp";
					break;
					
				case "POST":
					service = new QnAWriteService();
					service.execute(request, response);
					viewPage = "qna_writeOk.jsp";
					break;
				}
//			}
			break;
		case "/qna/qna_list":
			service = new QnAListService();
			service.execute(request, response);
			viewPage = "qna_list.jsp";
			break;
			
		case "/qna/qna_detail": 
			// 로그인한 사람만 접근 가능
//			if(C.securityCheck(request, response, null)) {
				service = new QnADetailService();
				service.execute(request, response);
				viewPage = "qna_detail.jsp";
//			}
			break;
			
		case "/qna/qna_update":
			// 로그인한 사람만 접근 가능
//			if(C.securityCheck(request, response, null)) {
				switch(method) {
				case "GET":
					// Service 안에서 작성자 여부 판단. 작성자 아니면 redirect 발생
					service = new QnASelectService();
					service.execute(request, response);
					// 위에서 redirect 되면 forward 진행 안함.
					if(!response.isCommitted()) {
						viewPage = "qna_update.jsp";
					}
					break;
				case "POST":
					service = new QnAUpdateService();
					service.execute(request, response);
					viewPage = "qna_updateOk.jsp";
					break;
				}
//			}
			
		case "/qna/review_delete":
			// 로그인한 사람만 접근 가능
//			if(C.securityCheck(request, response, null)) {
				switch(method) {
				case "POST":
					// 작성자가 아닌경우 Service 안에서 redirect 발생
					service = new QnADeleteService();
					service.execute(request, response);
					if(!response.isCommitted()) {
						viewPage = "qna_deleteOk.jsp";
					}
					break;
				}
//			}
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
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/qna/" + viewPage);
			
			dispatcher.forward(request, response);
		}
	}// end action()
}// end Controller
