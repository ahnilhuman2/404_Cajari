package service.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.QnACommentDAO;
import domain.QnACommentDTO;
import domain.QnAWriteDTO;
import domain.QryResult;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class QnACmtWriteService implements Service {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // 1. 어느글에 대한 댓글
      int qnaId = Integer.parseInt(request.getParameter("qna_id"));
      // 2. 어느 사용자가 작성한 댓글
      int userId = Integer.parseInt(request.getParameter("user_id"));
      // 3. 댓글 내용은 무엇
      String content = request.getParameter("content");
      
      QnAWriteDTO qna = new QnAWriteDTO();
      qna.setId(qnaId);
      UserDTO user = new UserDTO();
      user.setId(userId);
      
      QnACommentDTO dto = QnACommentDTO.builder()
            .qna(qna)
            .user(user)
            .content(content)
            .build();
      
      QryResult obj = new QryResult(); // response 할 java 객체
      ObjectMapper mapper = new ObjectMapper(); // Json 매핑할 Mapper 객체
      
      SqlSession sqlSession = null;
       QnACommentDAO dao = null;
       int cnt = 0;
      
       try {
           sqlSession = SqlSessionManager.getInstance().openSession();
           dao = sqlSession.getMapper(QnACommentDAO.class);          
          
           cnt = dao.insert(dto);
          
           obj.setCount(cnt);
           obj.setStatus("OK");
          
           sqlSession.commit();
       } catch (SQLException e) {
           e.printStackTrace();
           //예외에 대한 처리
           obj.setStatus("댓글 작성 ERROR: " + e.getMessage());
       } finally {
           if(sqlSession!= null) sqlSession.close();
       }
      
       String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);  // 포맷팅 ○          
       response.setContentType("application/json; charset=utf-8");  // MIME 설정
       response.getWriter().write(output);  // response에 보내기!   IOException
   }

}