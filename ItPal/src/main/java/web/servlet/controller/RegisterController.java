package web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAOImpl;
import model.vo.User;

public class RegisterController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "login.jsp";
		boolean isRedirect = true;
		UserDAOImpl dao = UserDAOImpl.getInstance();

		try {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String birthdate = request.getParameter("birthdate");
			
			dao.register(new User(id, name, pwd, phone, email, birthdate));
			
		} catch (Exception e) {
			path = "register.jsp";
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView(path, isRedirect);
	}
}
