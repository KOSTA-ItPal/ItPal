package web.servlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String requestURI=request.getRequestURI(); 
		System.out.println("requestURI :  "+requestURI);
		
		String contextPath=request.getContextPath(); 
		System.out.println("contextPath :  "+contextPath);

		String command = requestURI.substring(contextPath.length()+1);
		System.out.println("command :  "+command);
		
		//1. HabdlerMapping의 createComponent 함수를 호출...이때 위에서 받은 command을 넣어준다
		//     Controller 반환
		Controller controller=HandlerMapping.getFactory().createComponent(command);
		
		//2. Controller 의 메소드 호출...ModelAndView반환
		//     네비게이션
		ModelAndView mv = null;
		String path = "index.jsp";
		try {
			mv=controller.handleRequest(request, response);
		    path = mv.getPath();
		    
		    if(mv!=null) {
				if(mv.isRedirect()) response.sendRedirect(path);
				else request.getRequestDispatcher(path).forward(request, response);
			}
		    
		}catch(Exception e) {
			System.out.println(e);
		}		
	}
}