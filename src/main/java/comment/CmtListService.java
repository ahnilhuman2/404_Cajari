package service.comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.CommentDAO;
import domain.CommentDTO;
import domain.QryCommentList;
import service.Service;
import sqlmapper.SqlSessionManager;

public class CmtListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// paramet 가져오기  ※ 매개변수 검증 필요
	    int id = Integer.parseInt(request.getParameter("id"));
		
		QryCommentList obj = new QryCommentList();   // response 할 Java 객체
	    ObjectMapper mapper = new ObjectMapper();   // JSON 으로 매핑할 Mapper 객체 
	    
	    SqlSession sqlSession = null;
	    CommentDAO dao = null;      
	   
	    try {
	        sqlSession = SqlSessionManager.getInstance().openSession();
	        dao = sqlSession.getMapper(CommentDAO.class);
	       
	        List<CommentDTO> list = dao.selectByWrite(id);
	        obj.setList(list);
	        obj.setCount(list.size());
	        obj.setStatus("OK");
	       
	        sqlSession.commit();
	    } catch (SQLException e) {  
	        e.printStackTrace();
	        //예외에 대한 처리
	        obj.setStatus("댓글목록 ERROR: " + e.getMessage());
	    } finally {
	        if(sqlSession!= null) sqlSession.close();
	    }
	    
	    //String output = mapper.writeValueAsString(list); // 포맷팅 x
	    String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);  // 포맷팅 ○            
	    response.setContentType("application/json; charset=utf-8");  // MIME 설정
	    response.getWriter().write(output);  // response에 보내기!   IOException

	} // end execute()

} 
