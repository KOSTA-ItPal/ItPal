package web.servlet.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManagementDAOImpl;
import model.vo.User;

public class MyPageController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String path = "mypage.jsp";
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
		return new ModelAndView(path);
	}
}
