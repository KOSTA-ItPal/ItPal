package web.servlet.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDAOImpl;
import model.vo.User;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = "user1";
		String pass = "pass1";
		String path = "myPage.do";
		
		try {
			User user = UserDAOImpl.getInstance().login(id, pass);
			HttpSession session = request.getSession();
			
			if(user != null) { //null인경우 바인딩 진행 안되도록..
				session.setAttribute("user", user);
				System.out.println("LoginServlet ... JSESSIONID :::"+session.getId());			
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return new ModelAndView(path);
	}

}
