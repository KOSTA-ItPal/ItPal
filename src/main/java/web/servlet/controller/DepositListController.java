package web.servlet.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DepositDAOImpl;
import model.vo.Deposit;
import web.servlet.controller.Controller;
import web.servlet.controller.ModelAndView;

public class DepositListController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = null;
		
		try {
			//1. dao 호출
    		ArrayList<Deposit> deposits = DepositDAOImpl.getInstance().showAllDeposit();
			System.out.println("1. getInstance() 성공");
			
			//2. 반환값 바인딩
	    	request.setAttribute("deposits", deposits);
			System.out.println("2. setAttribute() 성공");
			
			//3. 성공시 경로, 페이지 이동 방법
			modelAndView = new ModelAndView("depositsearch.jsp", false);
			
			System.out.println("depositList.do 성공");
		} catch (SQLException e) {
			//3. 실패시 경로, 페이지 이동 방법
			modelAndView = new ModelAndView("index.jsp", true);
			System.err.println("[Error] : depositList.do 실패");
		} //try-catch
		return modelAndView;
	} //handleRequest
} //DepositListController
