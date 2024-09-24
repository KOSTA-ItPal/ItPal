package web.servlet.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManagementDAOImpl;
import model.vo.Account;
import model.vo.User;

public class MyPageController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String path = "mypage.jsp";
		boolean isRedirect = false;
		
		HttpSession session = request.getSession();
		//로그인 정보가 없어서 사용
		//User user = new User("user1", "Kim Minji", "pass1", "010-1234-5678", "minji.kim@test.com", "1992-05-15", 0, 0);
		User user = (User) session.getAttribute("user");
		
		try {
			
			//budget load
			//getMonthBudget 호출
			String getBudget = String.valueOf(ManagementDAOImpl.getInstance().getMonthBudget(user));
			System.out.println("Budget: " + getBudget);
			
			//showRecommendSpend 호출
			String recommendSpend = String.valueOf(ManagementDAOImpl.getInstance().showRecommendSpend(user));
			System.out.println("Recommended Spend: " + recommendSpend);
			
			//getPaymentPatternSum 호출 & totalSpend추출
			HashMap<String, Long> monthSpend = ManagementDAOImpl.getInstance().getPaymentPatternSum(user);
			String totalSpend = String.valueOf(monthSpend.values().stream()
				    .mapToLong(Long::longValue)
				    .sum());		
			System.out.println("totalSpend: " + totalSpend);
			
			// spend/budget 예산대비 소비비율 추출
			String spendRate;
			long budget = Long.parseLong(getBudget);
			long spend = Long.parseLong(totalSpend);
			spendRate = String.valueOf(Math.round(((double) spend / budget) * 100));
			System.out.println("spendRate: " + spendRate);
			
			//data 바인딩
			request.setAttribute("budget", getBudget);
			request.setAttribute("recommSpend", recommendSpend);
			request.setAttribute("totalSpend", totalSpend);
			request.setAttribute("spendRate", spendRate);
			
			//saving load
			//getSaving 호출
			String getSaving = String.valueOf(ManagementDAOImpl.getInstance().getSaving(user));
			System.out.println("Saving: " + getSaving);
			
			//getTotalAsset 호출
			String totalAsset = String.valueOf(ManagementDAOImpl.getInstance().getTotalAsset(user));
			System.out.println("totalAsset: " + totalAsset);
			
			//getAchievementRate 호출
			String achiveRate = String.valueOf(ManagementDAOImpl.getInstance().getAchievementRate(user));
			System.out.println("achiveRate: " + achiveRate);
			
			//data 바인딩
			request.setAttribute("saving", getSaving);
			request.setAttribute("totalAsset", totalAsset);
			request.setAttribute("achiveRate", achiveRate);
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		try {
			
			//User user = (User) request.getSession().getAttribute("user");
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
