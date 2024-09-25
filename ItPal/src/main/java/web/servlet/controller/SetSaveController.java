package web.servlet.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManagementDAOImpl;
import model.vo.User;

public class SetSaveController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String savingString = request.getParameter("saving");
		String path = "myPage.do";
		HttpSession session = request.getSession();
		//로그인 정보가 없어서 사용
		User user = new User("user1", "Kim Minji", "pass1", "010-1234-5678", "minji.kim@test.com", "1992-05-15", 0, 0);
		//User user = (User) session.getAttribute("user");
		
		
		try {
			//input 받은 saving 쉼표제거 처리 & 숫자 변환
			savingString = savingString.replace(",", ""); // 쉼표 제거
			long saving = Long.parseLong(savingString);  // 숫자로 변환
			
			//setSaving 호출
			ManagementDAOImpl.getInstance().setSaving(user, saving);
			
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
			path = "mypage.do";
		}
		return new ModelAndView(path);
	}
}
