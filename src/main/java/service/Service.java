package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	void excute(HttpServletRequest request, HttpServletResponse response);
}
