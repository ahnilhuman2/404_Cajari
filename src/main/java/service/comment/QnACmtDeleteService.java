package service.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.QnACommentDAO;
import domain.QryResult;
import service.Service;
import sqlmapper.SqlSessionManager;

public class QnACmtDeleteService implements Service {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {   
       int id = Integer.parseInt(request.getParameter("id"));
      
       QryResult obj = new QryResult();// response 할 Java 객체
       ObjectMapper mapper = new ObjectMapper();  // Json 매핑할 Mapper객체
      
       SqlSession sqlSession = null;
       QnACommentDAO dao = null;      
       int cnt = 0;
      
       try {
           sqlSession = SqlSessionManager.getInstance().openSession();
           dao = sqlSession.getMapper(QnACommentDAO.class);          
          
           cnt = dao.deleteById(id);
          
           obj.setCount(cnt);
           obj.setStatus("OK");
          
           sqlSession.commit();
       } catch (SQLException e) {
           e.printStackTrace();
           //예외에 대한 처리
           obj.setStatus("댓글 삭제 ERROR: " + e.getMessage());
       } finally {
           if(sqlSession!= null) sqlSession.close();
       }
       
       String output = mapper.writeValueAsString(obj);
       response.setContentType("application/json; charset=utf-8");
       response.getWriter().write(output);
   }

}