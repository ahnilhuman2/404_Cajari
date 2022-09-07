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
import service.qna.Qna_WriteService;
import service.review.Re_DeleteService;
import service.review.Re_DetailService;
import service.review.Re_DownloadService;
import service.review.Re_ListService;
import service.review.Re_SelectService;
import service.review.Re_UpdateService;
import service.review.Re_WriteService;

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
				viewPage = "qna_write.jsp";
				break;
			}
			
		case "/qna/qna_list":
			viewPage = "qna_list.jsp";
			break;
			
		case "/qna/qna_detail":
			viewPage = "qna_detail.jsp";
			break;
			
		case "/qna/qna_update":				
			viewPage = "qna_update.jsp";
			break;
			
		case "/review/review_delete":

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

