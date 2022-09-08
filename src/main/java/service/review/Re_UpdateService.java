package service.review;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.C;
import domain.FileDAO;
import domain.FileDTO;
import domain.Qna_WriteDAO;
import domain.Qna_WriteDTO;
import domain.Review_WriteDAO;
import domain.Review_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Re_UpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
				// 입력한 값 (parameter) 받아오기
				int id = Integer.parseInt(request.getParameter("id"));
				String subject =  request.getParameter("subject");
				String content =  request.getParameter("content");
				// ※ 이 단계에서 parameter 검증 해야 한다.
				
				SqlSession sqlSession = null;
				Review_WriteDAO dao = null;
				
				int cnt = 0;
				
//				WriteDTO dto = new WriteDTO();
//				dto.setId(id);
//				dto.setSubject(subject);
//				dto.setContent(content);
				
				// Builder 사용한 생성
				Review_WriteDTO dto = Review_WriteDTO.builder()
					.id(id)
					.subject(subject)
					.content(content)
					.build()
					;
				
				try {
					sqlSession = SqlSessionManager.getInstance().openSession();
					dao = sqlSession.getMapper(Review_WriteDAO.class);
					
					cnt = dao.update(dto);
					
					sqlSession.commit();
				} catch (SQLException e) {  
					e.printStackTrace();
				} finally {
					if(sqlSession!= null) sqlSession.close();
				}
				
				request.setAttribute("result", cnt);
				request.setAttribute("dto", dto); // id 값을 전달하기 위해

			}		 
		
//        //---------------------------------------------
//        // 1. MultipartRequest 생성 -> 파일 업로드 됨  
//        ServletContext context = request.getServletContext();
//        // 서블릿 상의 upload 폴더 경로를 알아온다
//        String saveDirectory = context.getRealPath("upload");
//        System.out.println("업로드 경로: " + saveDirectory);
//       
//        int maxPostSize = 5 * 1024 * 1024;  // POST 받기, 최대 5M byte :1Kbyte = 1024 byte, 1Mbyte = 1024 Kbyte
//        String encoding = "utf-8";  // response 인코딩
//        FileRenamePolicy policy = new DefaultFileRenamePolicy(); //업로딩 파일 이름 중복에 대한 정책
//        MultipartRequest multi = null; // com.oreilly.servlet.MultipartRequest 임포트
//
//        // MultipartRequest 생성 단계에서 이미 파일은 저장됨.
//        multi = new MultipartRequest(
//                request,                 // JSP 내부객체 request
//                saveDirectory,
//                maxPostSize,
//                encoding,
//                policy
//                ); 
//		
//        //---------------------------------------------
//        // 2. 업로드된 파일의   '원본이름(들)' 과 '저장된 이름(들)' 받아오기
//
//        // ↓ 각각의 첨부파일의 원본이름 + 저장된 이름을 Map으로 담고,
//        //   이 Map<> 들을 저장할 List 준비
//        List<Map<String, Object>> nameList = new ArrayList<>();  
//
//        Enumeration names = multi.getFileNames();   // type="file" 요소들의 name들 추출        
//        while(names.hasMoreElements()){    
//            String name = (String)names.nextElement();
//            if(name.startsWith("upfile")) {  // name="upfile##" 인 경우만 첨부파일 등록, (다른 웹 에디터와 섞이지 않도록)              
//                String originalFileName = multi.getOriginalFileName(name);  // 원본 이름
//                String fileSystemName = multi.getFilesystemName(name);  // 저장된(rename)된 이름
//                System.out.println("첨부파일(" + name + "): " + originalFileName + "->" + fileSystemName);
//               
//                if(originalFileName != null && fileSystemName != null) {
//                    // Map.of(k1, v1, k2, v2 ..) 로 Map 객체 생성   (Java9 에서 추가)
//                    nameList.add(Map.of("source", originalFileName, "file", fileSystemName));
//                }
//            }
//        } // end while
//        
//        // ---------------------------------------------------------
//        // 3. 기존의 첨부파일들 중 삭제할 것들
//        String [] delFiles = multi.getParameterValues("delfile");
//        int [] delFileIds = null;   // 삭제될 첨부파일들의 id 들을 담을거다.
//        if(delFiles != null && delFiles.length > 0) {
//        	// String [] -> int [] 변환
//        	delFileIds = new int[delFiles.length];
//        	for(int i = 0; i < delFiles.length; i++) {
//        		delFileIds[i] = Integer.parseInt(delFiles[i]);
//        	}        	
//        } // end if
//        
//        
//        // ----------------------------------
//        // 4. 글 수정
//		// 입력합 값 (parameter) 받아오기
//		int id = Integer.parseInt(multi.getParameter("id"));
//		String subject = multi.getParameter("subject");
//		String content = multi.getParameter("content");
//		// ※ 이 단계에서 parameter 검증 해야 한다.
//		
//		SqlSession sqlSession = null;
//		Review_WriteDAO dao = null;		
//		FileDAO fileDao = null;
//		
//		int cnt = 0;
//		List<FileDTO> fileList = null;
//		
////		WriteDTO dto = new WriteDTO();
////		dto.setId(id);
////		dto.setSubject(subject);
////		dto.setContent(content);
//		
//		// Builder 사용한 생성
//		Review_WriteDTO dto = Review_WriteDTO.builder()
//			.id(id)
//			.subject(subject)
//			.content(content)
//			.build()
//			;
//		
//		try {
//			sqlSession = SqlSessionManager.getInstance().openSession();
//			dao = sqlSession.getMapper(Review_WriteDAO.class);
//			fileDao = sqlSession.getMapper(FileDAO.class);
//			
//            // 4. 삭제 선택한 첨부파일 삭제
//            if(delFileIds != null && delFileIds.length > 0) {
//                fileList = fileDao.selectByIds(delFileIds);
//                // 물리적인 파일 삭제
//                C.deleteFiles(fileList, request);
//                // DB 에서 파일 삭제
//                fileDao.deleteByIds(delFileIds);
//            }
//            
//            // 5. 추가된 파일들은 DB에 저장하기
//            if(nameList != null && nameList.size() > 0) {              
//                fileDao.insert(nameList, id);
//            }
//
//			// 6. 글 수정
//			cnt = dao.update(dto);
//			
//			sqlSession.commit();
//		} catch (SQLException e) {  
//			e.printStackTrace();
//		} finally {
//			if(sqlSession!= null) sqlSession.close();
//		}
//		
//		request.setAttribute("result", cnt);
//		request.setAttribute("dto", dto);  // id 값을 전달하기 위해


}



