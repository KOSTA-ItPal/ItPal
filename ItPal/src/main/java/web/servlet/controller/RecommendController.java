package web.servlet.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.CardDAOImpl;
import main.java.model.vo.Card;
import main.java.model.vo.User;

public class RecommendController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		
		String path = "login.html";
		boolean isRedirect = false;
		
		try {
			
			//User user = (User) request.getSession().getAttribute("user");
			User user = new User("Kim Minji", "user1", "pass1", "010-1234-5678", "minji.kim@test.com", "1992-05-15", 0, 0);
			
			//세션에 넣을 이름 정하기!!
			if(user != null) {
				
				ArrayList<ArrayList<Card>> cardList = CardDAOImpl.getInstance().searchByCategory(user);
				
				int count1 = 1;
				for (ArrayList<Card> listRecomm : cardList) {
					System.out.println("\n카테고리별 카드 목록 "+count1+++" ---------------------------------------------------------------");
					listRecomm.stream().forEach(i->System.out.println(i));
				}
				
				request.setAttribute("cardList", cardList);
				path = "cardRecommend.jsp";
			}
			else {
				isRedirect = true;
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return new ModelAndView(path);
	}

}