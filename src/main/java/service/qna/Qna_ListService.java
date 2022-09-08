package service.qna;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import domain.Qna_WriteDAO;
import domain.Qna_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Qna_ListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
//		// 파라미터 받기 (현재 page)
//		int page = 1;   // 디폴트 페이지 1
//		String pageParam = request.getParameter("page");
//		if(pageParam != null && !pageParam.trim().equals("")) {
//			try {				
//				page = Integer.parseInt(pageParam);
//			} catch(NumberFormatException e) {
//				// page parameter 오류는 별도의 예외 처리 안함.
//			}
//		} // end if
//		
//	    // 페이징
//	    // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
//	    // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
//	    HttpSession session = request.getSession();
//	    Integer writePages = (Integer)session.getAttribute("writePages");
//	    if(writePages == null) writePages = C.WRITE_PAGES;   // session 에 없으면 기본값으로
//	    Integer pageRows = (Integer)session.getAttribute("pageRows");
//	    if(pageRows == null) pageRows = C.PAGE_ROWS;     // session 에 없으면 기본값으로
//	    
//	    int cnt = 0;  // 글 목록 전체의 개수
//	    int totalPage = 0;  // 총 몇 '페이지' 분량인가?
		
		SqlSession sqlSession = null;
		Qna_WriteDAO dao = null;	
		
		List<Qna_WriteDTO> list = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(Qna_WriteDAO.class);
			
			list = dao.select();
			// "list" 란  name 으로 request 에 list 저장
			// 즉, request 에 담아서 컨트롤러에 전달되는 셈.
			request.setAttribute("list", list);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			if(sqlSession!= null) sqlSession.close();
		}

//		request.setAttribute("list", list);
//		
//        request.setAttribute("cnt", cnt);  // 전체 글 개수
//        request.setAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
//        request.setAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수
//        session.setAttribute("page", page);  // 현재 페이지 (세션에 저장해두자)
//        request.setAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
//        request.setAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지
//        request.setAttribute("totalPage", totalPage); // 총 페이지 개수
//        request.setAttribute("url", request.getRequestURI());  // 목록 url


	} // end execute()

} // end Service
