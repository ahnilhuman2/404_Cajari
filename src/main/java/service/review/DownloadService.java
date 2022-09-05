package service.review;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import domain.FileDAO;
import domain.FileDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

/* 파일 다운로드
 * 1. InputStream/OutputStream 객체 준비
 * 2. 다운로드할 파일정보 가져오기 (원본이름, 저장된이름, 저장경로 ..)
 * 3. 파일유형 (MimeType) 추출
 * 4. response 세팅 (MimeType, Content-disposition ..)
 * 5. 파일 읽기 -> 전송
 * 6. 자원 해제 (close)
 */
public class DownloadService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 매개변수: 첨부파일의 id
		int id = Integer.parseInt(request.getParameter("id"));  // ※ 매개변수 검증 필요

		// 1. InputStream/OutputStream 객체 준비
		FileInputStream in = null;
		ServletOutputStream sout = null;
		
		SqlSession sqlSession = null;
		FileDAO fileDao = null;		
		List<FileDTO> fileList = null;
		
		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			fileDao = sqlSession.getMapper(FileDAO.class);
			
			// 2. 다운로드할 파일정보 가져오기 (원본이름, 저장된이름, 저장경로)
			fileList = fileDao.selectById(id);   // 다운로드할 첨부파일 (1개) 정보 불러오기
			
			String fileSystemName = fileList.get(0).getFile();   // 물리적으로 저장된 이름
			String originalFileName = fileList.get(0).getSource();   // 원본 이름
			
			String filePath = request.getServletContext().getRealPath("upload") + File.separator + fileSystemName;
						
			// 3. 파일유형 (MimeType) 추출
			String fileType = request.getServletContext().getMimeType(filePath);
			
			// 유형이 지정되지 않은 경우
			if(fileType == null) {
				fileType = "application/octet-stream";   // 일련의 8bit 스트림 방식
			}					// ↑ 유형이 지정되지 않은 파일에 대한 형식으로 이를 지정
			System.out.println("파일유형(MIME): " + fileType);
			
			// 4. response 세팅 (MimeType, Content-disposition ..)
			// reseponse 에 유형(Mimetype)을 세팅
			response.setContentType(fileType); 
			
			// 원본 파일 이름으로 다운받을수 있게 한다
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(originalFileName, "utf-8"));    
			
			//5. 파일 읽기 -> 전송
			File srcFile = new File(filePath);
			in = new FileInputStream(srcFile);
			sout = response.getOutputStream();
			
			// 읽어와야할 용향은 파일의 용량을 초과하진 않는다.
			byte [] buff = new byte[(int)srcFile.length()];   // 파일의 크기만큼의 버퍼 준비
			int numRead = 0;
			int totalRead = 0;
			
			while((numRead = in.read(buff, 0, buff.length)) != -1) {  // 읽기
				totalRead += numRead;
				sout.write(buff, 0, numRead);   // 전송
			}
			System.out.println("전송 byte: " + totalRead);
			
			sqlSession.commit();
		} catch (SQLException e) {  
			e.printStackTrace();
		} finally {
			// 6. 자원 해제 (close)
			if(sqlSession!= null) sqlSession.close();
			
			try {
				if(sout != null) { 
					sout.flush();  // 마지막까지 완전히 전송하고.
					sout.close();
				}
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 			
		}

	}

}


