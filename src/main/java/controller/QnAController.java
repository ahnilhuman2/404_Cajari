package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
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
			break;
//		case "/qna/qna_list":
//			service = new QnAListService();
//			service.execute(request, response);
//			viewPage = "qna_list.jsp";
//			break;
//			
//		case "/qna/qna_detail":
//			service = new QnADetailService();
//			service.execute(request, response);
//			viewPage = "qna_detail.jsp";
//			break;
//			
//		case "/qna/qna_update":
//			switch(method) {
//			case "GET":
//				service = new QnASelectService();
//				service.execute(request, response);
//				viewPage = "qna_update.jsp";
//				break;
//			case "POST":
//				service = new QnAUpdateService();
//				service.execute(request, response);
//				viewPage = "qna_updateOk.jsp";
//				break;
//			}
//			
//		case "/qna/review_delete":
//			switch(method) {
//			case "POST":
//				service = new QnADeleteService();
//				service.execute(request, response);
//				viewPage = "qna_deleteOk.jsp";
//				break;
//			}
//			break;
	
		} // end switch
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/qna/" + viewPage);
			
			dispatcher.forward(request, response);
		}
	}// end action()
}// end Controller
