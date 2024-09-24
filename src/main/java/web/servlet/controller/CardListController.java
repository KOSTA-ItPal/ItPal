package web.servlet.controller;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.CardImpl;
import main.java.model.vo.Card;

public class CardListController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String path="index.jsp";
		
		try {
			ArrayList<Card> newCard = CardImpl.getInstance().showNewCards();
			ArrayList<Card> hotCard = CardImpl.getInstance().showHotCards();
			request.setAttribute("vo", newCard);
			request.setAttribute("no", hotCard );
			
			path="cardsearch.jsp";
		} catch (SQLException e) {
			System.out.println(e);
		}
		return  new ModelAndView(path);

}
