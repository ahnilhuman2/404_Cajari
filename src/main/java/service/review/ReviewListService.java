package service.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import common.C;
import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 파라미터 받기 (현재 page)
		int page = 1; // 디폴트 값
		String pageParam = request.getParameter("page");
		if(pageParam != null && !pageParam.trim().equals("")) {
			try {
				page = Integer.parseInt(pageParam);				
			} catch(NumberFormatException e) {
				// page 오류는 별도 예외 처리 안함
			}
		} // end if
		
		// 페이징
		HttpSession session = request.getSession();
		Integer writePages = (Integer)session.getAttribute("writePages");
		if(writePages == null) writePages = C.WRITE_PAGES;
		Integer pageRows = (Integer)session.getAttribute("pageRows");
		if(pageRows == null) pageRows = C.PAGE_ROWS;
		
		int cnt = 0;
		int totalPage = 0;
		
		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		
		List<ReviewWriteDTO> list = null;
		
		int startPage = 1;
		int endPage = 10;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);
			
			cnt = dao.countAll(); // 글 목록 전체 개수
			totalPage = (int)Math.ceil(cnt / (double)pageRows);
			
			// 현재 페이지 값 보정
			if(page < 1) page = 1;
			if(page > totalPage) page = totalPage;
			
			// 페이지 글목록 가져오기
			int fromRow = (page - 1) * pageRows;
			list = dao.selectFromRow(fromRow, pageRows);	
			
			// 페이징 표시
			startPage = ((int)((page - 1) / writePages) * writePages) + 1;
			endPage = startPage + writePages - 1;
			if (endPage >= totalPage) endPage = totalPage;
			
			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}

		request.setAttribute("list", list);
		
		request.setAttribute("cnt", cnt);  // 전체 글 개수
        request.setAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        request.setAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수
        session.setAttribute("page", page);  // 현재 페이지 (세션에 저장해두자)
        request.setAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        request.setAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지
        request.setAttribute("totalPage", totalPage); // 총 페이지 개수
        request.setAttribute("url", request.getRequestURI());  // 목록 url
        
	}

}
