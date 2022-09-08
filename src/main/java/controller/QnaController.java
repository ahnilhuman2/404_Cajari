package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
import service.qna.Qna_DeleteService;
import service.qna.Qna_DetailService;
import service.qna.Qna_ListService;
import service.qna.Qna_SelectService;
import service.qna.Qna_UpdateService;
import service.qna.Qna_WriteService;

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
			switch(method) {
			case "GET":
				viewPage = "qna_write.jsp";
				break;
			case "POST":
				service = new Qna_WriteService();
				service.execute(request, response);
				viewPage = "qna_writeOk.jsp";
				break;
			}
			break;
		
		case "/qna/qna_list":
			service = new Qna_ListService();
			service.execute(request, response);
			viewPage = "qna_list.jsp";
			break;
		
		case "/qna/qna_detail":
			service = new Qna_DetailService();
			service.execute(request, response);
			viewPage = "qna_detail.jsp";
			break;
		
		case "/qna/qna_update":
			switch(method) {
			case "GET":
				service = new Qna_SelectService();
				service.execute(request, response);
				viewPage = "qna_update.jsp";
				break;
			case "POST":
				service = new Qna_UpdateService();
				service.execute(request, response);
				viewPage = "qna_updateOk.jsp";
				break;
			}
			break;
		
		case "/qna/qna_delete":
			switch(method) {
			case "POST":
				service = new Qna_DeleteService();
				service.execute(request, response);
				viewPage = "qna_deleteOk.jsp";
				break;
			}
			break;
		} // end switch
		
		// 위에서 결정된 뷰 페이지 (viewPage) 로 forward 해줌
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/qna/" + viewPage);
			
			dispatcher.forward(request, response);
		}
	
	
	} // end action()

} // end Controller

