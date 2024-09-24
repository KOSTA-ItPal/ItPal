package web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDAOImpl;
import model.vo.User;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "login.jsp";
		boolean isRedirect = true;
		UserDAOImpl dao = UserDAOImpl.getInstance();
		User user = null;
		
		try {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			user = dao.login(id, pwd);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			if (user != null) 
				path="index.html";
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView(path, isRedirect);
	}
}