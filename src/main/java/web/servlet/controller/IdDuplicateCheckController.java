package web.servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAOImpl;

public class IdDuplicateCheckController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String path = "idDuplicateResult.jsp";
		UserDAOImpl dao = UserDAOImpl.getInstance();

		try {
			String userId = request.getParameter("id");
			String result = dao.findUserId(userId);
			
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView(path);
	}
}
