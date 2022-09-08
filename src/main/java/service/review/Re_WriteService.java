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
import domain.UserDTO;
import domain.Review_WriteDAO;
import domain.Review_WriteDTO;
import service.Service;
import sqlmapper.SqlSessionManager;

public class Re_WriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 입력값 받아오기
		String name =  request.getParameter("name");
		String subject =  request.getParameter("subject");
		String parkingName =  request.getParameter("parkingName");
		String content =  request.getParameter("content");
		
		Review_WriteDTO rdto = new Review_WriteDTO();
		ParkingDTO pdto = new ParkingDTO();
//		dto.setName(name);
		rdto.setSubject(subject);
		pdto.setParking_name(parkingName);
		rdto.setContent(content);
		
		System.out.println(name + " : " + subject + " : " + parkingName + " : " + content);
	} // end execute()

} // end Command

