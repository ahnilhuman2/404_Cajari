package service.review;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import domain.FileDAO;
import domain.FileDTO;
import domain.ReviewWriteDAO;
import domain.ReviewWriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class ReviewDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Integer page = (Integer) session.getAttribute("page");
		if (page == null)
			page = 1;
		request.setAttribute("page", page);

		SqlSession sqlSession = null;
		ReviewWriteDAO dao = null;
		FileDAO fileDao = null;

		List<ReviewWriteDTO> list = null;

		try {
			sqlSession = SqlSessionManager.getInstance().openSession();
			dao = sqlSession.getMapper(ReviewWriteDAO.class);
			fileDao = sqlSession.getMapper(FileDAO.class);

			// 조회수 증가 + 글읽기
			dao.incViewCnt(id);
			list = dao.selectById(id);

			// 특정 글1개(id) 에 대한 첨부파일 목록 가져오기
			if (list != null && list.size() == 1) {
				List<FileDTO> fileList = fileDao.selectFilesByWrite(id);

				// 이미지 파일 여부 세팅
				String realPath = request.getServletContext().getRealPath("upload");

				for (FileDTO fileDto : fileList) {
					File f = new File(realPath, fileDto.getFile()); // 첨부파일에 대한 File 객체
					BufferedImage imgData = null;
					try {
						imgData = ImageIO.read(f);
						// ※ ↑ 파일이 존재 하지 않으면 IOExcepion 발생한다
						// ↑ 이미지가 아닌 경우는 null 리턴
					} catch (IOException e) {
						System.out.println("파일존재안함: " + f.getAbsolutePath() + " [" + e.getMessage() + "]");
					}

					if (imgData != null)
						fileDto.setImage(true); // 이미지 여부 true!
				} // end for

				request.setAttribute("fileList", fileList);

			} // end if

			request.setAttribute("list", list);

			sqlSession.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}

	}

}
