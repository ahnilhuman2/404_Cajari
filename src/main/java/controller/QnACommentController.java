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
import service.comment.QnACmtDeleteService;
import service.comment.QnACmtListService;
import service.comment.QnACmtWriteService;
import service.qna.QnADeleteService;
import service.qna.QnADetailService;
import service.qna.QnAListService;
import service.qna.QnASelectService;
import service.qna.QnAUpdateService;
import service.qna.QnAWriteService;


@WebServlet("/qnacomment/*")
public class QnACommentController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public QnACommentController() {
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
      
      switch(command) {
      case "/qnacomment/list":
         new QnACmtListService().execute(request, response);
         break;
         
      case "/qnacomment/write":
         if(method.equals("POST")) {
            new QnACmtWriteService().execute(request, response);
         }
         break;
         
      case "/qnacomment/delete":
         if(method.equals("POST")) {
            new QnACmtDeleteService().execute(request, response);
         }
         break;
      } // end switch
   }// end action()
}// end Controller