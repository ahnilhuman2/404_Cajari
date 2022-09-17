package service.qna;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import sqlmapper.SqlSessionManager;

import common.C;
import domain.QnAWriteDAO;
import domain.QnAWriteDTO;
import domain.UserDTO;
import service.Service;

public class QnADeleteService implements Service {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
      int id = Integer.parseInt(request.getParameter("id"));
      
      SqlSession sqlSession = null;
      QnAWriteDAO dao = null;      
      
      int cnt = 0;
      
      try {
         sqlSession = SqlSessionManager.getInstance().openSession();
         dao = sqlSession.getMapper(QnAWriteDAO.class);
         
         // 로그인한 사용자가 아니면 여기서 redirect 해야 한다
//         UserDTO loggedUser = (UserDTO)request.getSession().getAttribute(C.PRINCIPAL);
//         List<QnAWriteDTO> list = dao.selectById(id);
//         UserDTO writeUser = list.get(0).getUser();
//         if(loggedUser.getId() != writeUser.getId()) {
//            response.sendRedirect(request.getContextPath() + "/user/rejectAuth");
//            return;
//         }   
         
         cnt = dao.deleteById(id);
         
         sqlSession.commit();
      } catch (SQLException e) {  
         e.printStackTrace();
      } finally {
         if(sqlSession!= null) sqlSession.close();
      }
      
      request.setAttribute("result", cnt);

   }

}