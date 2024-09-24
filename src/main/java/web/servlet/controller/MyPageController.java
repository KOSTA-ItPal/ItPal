package web.servlet.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ManagementDAOImpl;
import model.vo.User;



public class MyPageController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String path="index.jsp";
		User user = new User("user1","김","pass");
		try {
			
			HashMap<String, Long> Cntpattern=	ManagementDAOImpl.getInstance().getPaymentPatternCnt(user);
			HashMap<String, Long> Sumpattern=	ManagementDAOImpl.getInstance().getPaymentPatternSum(user);
			HashMap<Long, Long> status = ManagementDAOImpl.getInstance().showSpendStatusList(user);
			int achiveRate = ManagementDAOImpl.getInstance().getAchievementRate(user);
			 request.setAttribute("Cntpattern", Cntpattern);
			 request.setAttribute("Sumpattern", Sumpattern);
			 request.setAttribute("status", status);
			 request.setAttribute("achiveRate", 80);
			 path="mypage.jsp";
					 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView(path);
	}

}
