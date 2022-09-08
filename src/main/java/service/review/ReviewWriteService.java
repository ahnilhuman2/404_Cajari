package service.review;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class ReviewWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String parkingName = request.getParameter("parkingName");
		String content = request.getParameter("content");

		
	}

}
