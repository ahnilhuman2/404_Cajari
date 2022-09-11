package service.qna;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import sqlmapper.SqlSessionManager;

import domain.QnAWriteDAO;
import domain.QnAWriteDTO;
import service.Service;

public class QnADetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// 페이징 관련
	    HttpSession session = request.getSession();
	    Integer page = (Integer)session.getAttribute("page");
	    if(page == null) page = 1;
	    request.setAttribute("page", page);
	    
	    SqlSession sqlSession = null;
		QnAWriteDAO dao = null;		
		
		List<QnAWriteDTO> list = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(QnAWriteDAO.class);
			
			// 조회수 증가 + 글 읽기
			dao.incViewCnt(id);
			list = dao.selectById(id);
			
			request.setAttribute("list", list);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}

	}

}
