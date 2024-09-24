package web.servlet.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ManagementDAOImpl;
import model.vo.Account;
import model.vo.User;

public class MyPageController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "longin.html";
		boolean isRedirect = false;
		
		try {
			
			//User user = (User) request.getSession().getAttribute("user");
			
			User user = new User( "user1", "Kim Minji", "pass1", "010-1234-5678", "minji.kim@test.com", "1992-05-15", 0, 0);
			request.getSession().setAttribute("user", user);
			
			if( user != null) {
				
				ManagementDAOImpl MGMTdao  =ManagementDAOImpl.getInstance();
				
				Long totalAsset = MGMTdao.getTotalAsset(user);
				
				ArrayList<Account> accounts= MGMTdao.showUserAsset(user);
				
				/*
					입출금 통장 (Demand Deposit Account): "DEMAND"
					예금 통장 (Fixed Deposit Account): "FIXED"
					적금 통장 (Installment Savings Account): "INSTALLMENT" 
				*/
				ArrayList<Account> demandAccounts = new ArrayList<>();
				ArrayList<Account> fixedAccounts = new ArrayList<>();
				ArrayList<Account> installmentAccounts = new ArrayList<>();
				
				
				for(Account account : accounts) {
					String accountType = account.getAccountType();
					if(accountType.equals("DEMAND")) demandAccounts.add(account);
					else if(accountType.equals("FIXED")) fixedAccounts.add(account);
					else installmentAccounts.add(account);
				}
				
				request.setAttribute("totalAsset", totalAsset);
				request.setAttribute("demandAccounts", demandAccounts);
				request.setAttribute("fixedAccounts", fixedAccounts);
				request.setAttribute("installmentAccounts", installmentAccounts);
				
				path = "mypage.jsp";
			}
			else {
				isRedirect = true;
				
			}
			
			

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView(path,isRedirect);
	}

}
