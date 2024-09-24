package web.servlet.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManagementDAOImpl;
import model.vo.User;

public class SetBudgetController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String budgetString = request.getParameter("budget");
		String path = "myPage.do";
		HttpSession session = request.getSession();
		//로그인 정보가 없어서 사용
		//User user = new User("user1", "Kim Minji", "pass1", "010-1234-5678", "minji.kim@test.com", "1992-05-15", 0, 0);
		User user = (User) session.getAttribute("user");
		
		
		try {
			//input 받은 budget 쉼표제거 처리 & 숫자 변환
			budgetString = budgetString.replace(",", ""); // 쉼표 제거
			long budget = Long.parseLong(budgetString);  // 숫자로 변환
			
			//setMonthBudget
			ManagementDAOImpl.getInstance().setMonthBudget(user, budget);
			
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
			long getbudget2 = Long.parseLong(getBudget);
			long spend = Long.parseLong(totalSpend);
			spendRate = String.valueOf(Math.round(((double) spend / getbudget2) * 100));
			System.out.println("spendRate: " + spendRate);
			
			//data 바인딩
			request.setAttribute("budget", getBudget);
			request.setAttribute("recommSpend", recommendSpend);
			request.setAttribute("totalSpend", totalSpend);
			request.setAttribute("spendRate", spendRate);
			
		} catch (SQLException e) {
			System.out.println(e);
			path = "mypage.do";
		}
		return new ModelAndView(path);
	}
}
