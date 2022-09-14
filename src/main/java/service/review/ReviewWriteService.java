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
import domain.ParkingDTO;
import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import domain.UserDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// MultipartRequest 생성 하는 순간 파일 업로드 가능
		ServletContext context = request.getServletContext();
		// 서블릿 상의 upload 폴더 경로를 알아오는 코드
		String saveDirectory = context.getRealPath("upload");
		System.out.println("업로드 경로: " + saveDirectory);

		// MultiPartRequest 생성단계에서 이미 파일은 저장되었다!
		int maxPostSize = 5 * 1024 * 1024; // POST로 받기, POST 로 전달받는 파일 최대 5M byte
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 업로딩 파일 이름 중복처리에 대한 정책
		MultipartRequest multi = null;

		// MultiPartRequest 생성단계에서 이미 파일은 저장되었다!
		multi = new MultipartRequest( // throws IOException
				request, saveDirectory, // 저장경로
				maxPostSize, // 최대용량
				encoding, // 인코딩 옵션
				policy // 파일 rename
		);

		// 2. 업로드된 파일의 '원본이름(들)' 과 '저장된 이름(들)' 받아오기

		// ↓ 각각의 첨부파일의 원본이름 + 저장된 이름을 Map으로 담고,
		// 이 Map<> 들을 저장할 List 준비
		List<Map<String, Object>> nameList = new ArrayList<>();

		Enumeration names = multi.getFileNames(); // type="file" 요소들의 name들 추출
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if (name.startsWith("upfile")) { // name="upfile##" 인 경우만 첨부파일로 다룬다.
				String originalFileName = multi.getOriginalFileName(name); // 원본이름
				String fileSystemName = multi.getFilesystemName(name); // 저장된(rename) 된 이름
				System.out.println("첨부파일(" + name + "): " + originalFileName + "->" + fileSystemName);

				if (originalFileName != null && fileSystemName != null) {
					// Map.of(k1, v1, k2, v2 ..) 로 Map 객체 생성 (Java9 에서 추가)
					nameList.add(Map.of("source", originalFileName, "file", fileSystemName));
				}
			}

		} // end file

		String subject = multi.getParameter("subject");
		String parking_write = multi.getParameter("parkingName");
		String content = multi.getParameter("content");

		UserDTO user = (UserDTO) request.getSession().getAttribute(C.PRINCIPAL);

		ReviewWriteDTO dto = new ReviewWriteDTO();

		System.out.println(user);
		System.out.println(parking_write);
		System.out.println(subject);
		System.out.println(content);

		dto.setUser(user);
		dto.setParking_write(parking_write);
		dto.setSubject(subject);
		dto.setContent(content);

		int cnt = 0;

		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		FileDAO fileDao = null;

		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);
			fileDao = sqlSession.getMapper(FileDAO.class);

			cnt = dao.insert(dto);

			// 첨부파일 정보 -> DB 저장 (위에서 생성된 글의 id 값을 FK 로 전달)
			if (nameList != null && nameList.size() > 0) {
				fileDao.insert(nameList, dto.getId()); //dto.getId() 글의 아이디 값
			}

			System.out.println("글작성 성공 " + cnt + " : " + dto.getId());

			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}

		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto);

	}

}
