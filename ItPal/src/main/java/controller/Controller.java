package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Template기능...
public interface Controller {
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response);
}
