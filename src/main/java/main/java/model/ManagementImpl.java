package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.model.util.config;
import main.java.model.vo.Account;
import main.java.model.vo.Card;
import main.java.model.vo.User;

public class ManagementImpl implements ManagementDAO{

	// 싱글톤
		private static ManagementImpl dao = new ManagementImpl();

		  private ManagementImpl() {
		        // 드라이버 로딩 -- 후에 데이터 소스로 변경 예정
		        try {
		            Class.forName(config.DRIVER_NAME);
		            System.out.println("Driver Loading 성공");
		        } catch (ClassNotFoundException e) {
		            System.out.println("드라이버 로딩 실패: " + e.getMessage());
		        }
		    }
		
		public static ManagementImpl getInstance() {
			return dao;
		}
	
	  @Override
	    public Connection getConnect() {
	        Connection conn = null;
	        try {
	            // DB 연결 시도
	            conn = DriverManager.getConnection(config.URL, config.USER, config.PASSWORD);
	            if (conn != null) {
	                System.out.println("DB 연결 성공");
	            } else {
	                System.out.println("DB 연결 실패");
	            }
	        } catch (SQLException e) {
	            System.out.println("DB 연결 중 오류 발생: " + e.getMessage());
	        }
	        return conn;
	    }


	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(conn != null) conn.close();
		if(ps != null) ps.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		closeAll(ps, conn);
		if(rs != null) rs.close();

	}

	@Override
	public int showSpendStatus(User user) throws SQLException {
		String query ="SELECT sum(pay),user_id\r\n"
				+ "FROM (select date, pay, user_id from payment where user_id=?) pay1\r\n"
				+ "WHERE YEAR(date) = ? AND MONTH(date) = ?;";
		int amount= 0;
		ResultSet rs=null;
		 LocalDate currentDate = LocalDate.now();
		 int year = currentDate.getYear();
		 Month  monthValue = currentDate.getMonth();
		 int month = monthValue.getValue();
		 String us = user.getUserId();
		try(Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query);){
			
			ps.setString(1, us);
			ps.setInt(2, year);
			ps.setInt(3, month);
			rs=ps.executeQuery();
			if(rs.next()) {
				amount=rs.getInt(1);
			}
		}
		return amount;
	}

	@Override
	public void setMonthBudget(User user, int budget) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int gettMonthBudget(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int showRecommendSpend(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Account> showUserAsset(User user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalAsset(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSaving(User user, int save) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSaving(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAchievementRate(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	//소비패턴 한눈에 보기 (높은 비중으로 정렬)
	@Override
	public Map<String, Integer> getPaymentPattern(User user) throws SQLException {
		String query="SELECT category, COUNT(*) AS category_count\r\n"
				+ "FROM itpal.payment\r\n"
				+ "WHERE user_id = ?\r\n"
				+ "GROUP BY category\r\n"
				+ "ORDER BY category_count DESC;";
		 Map<String, Integer> paymentPattern = new HashMap<>();
		ResultSet rs=null;
		try(Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query); )
		{	
			String us = user.getUserId();
			ps.setString(1, us);
			rs=ps.executeQuery();
			while(rs.next()) {
				String category = rs.getString("category");         // 카테고리 값 가져오기
	            int count = rs.getInt("category_count");            // 카테고리별 결제 건수 가져오기
	            paymentPattern.put(category, count);      
			}
		}
		return paymentPattern;
	}

	@Override
	public ArrayList<Map<Integer, Integer>> showSpendStatus(User user, int month) throws SQLException {
		String query ="SELECT MONTH(date), sum(pay),user_id\r\n"
				+ "FROM (select date, pay, user_id from payment where user_id=? and YEAR(date) = ?) pay1\r\n"
				+ "group by 1;";
		
		ArrayList<Map<Integer, Integer>> ammountList = new ArrayList<>();
		Map<Integer, Integer> monthlyAmount = new HashMap<>();
		int months = 0;
		int amount= 0;
			ResultSet rs=null;
		 LocalDate currentDate = LocalDate.now();
		 int year = currentDate.getYear();
		 
		 String us = user.getUserId();
		try(Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query);){
			
			ps.setString(1, us);
			ps.setInt(2, year);
			rs=ps.executeQuery();
			while(rs.next()) {
				months = rs.getInt(1);
				amount=rs.getInt(2);
				monthlyAmount.put(months, amount);
			}
			for(int i=0; i<4;i++) {
				int calMonth = (month - i ) % 12; 
				Integer amounts = monthlyAmount.get(calMonth);
				if(amounts != null) {
					  Map<Integer, Integer> map = new HashMap<>();
					  map.put(calMonth, amounts);
					ammountList.add(map);
				}
			}
		}
		return ammountList;
	}
}

class Test2 {
	 public static CardImpl dao = CardImpl.getInstance();

	 public static void main(String[] args) {
	     System.out.println("소비유형보기");
	     Map<String, Integer> pay = new HashMap<>();
	     User user = new User("user1","김민지","pass1");
	     ArrayList<Map<Integer, Integer>> amounts = new ArrayList<>();
	     Scanner sc = new Scanner(System.in);
	     System.out.println("월입력하기");
	     int month = sc.nextInt();
	     int amount = 0;
	     try {
//	         pay = ManagementImpl.getInstance().getPaymentPattern(user);  // 입력한 카테고리를 사용
//	         amount = ManagementImpl.getInstance().showSpendStatus(user);
	         amounts = ManagementImpl.getInstance().showSpendStatus(user, month);
//	         for (Map.Entry<String, Integer> entry : pay.entrySet()) {
//	        	    String category = entry.getKey();     // 카테고리
//	        	    Integer count = entry.getValue();     // 카테고리별 결제 건수
//	        	    System.out.println("Category: " + category + ", Count: " + count);
//	        	   
//	        	    
//	        	}
	         for(Map<Integer, Integer> map : amounts) {
	        	 for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
	        		 System.out.println("월"+entry.getKey()+", 합계"+entry.getValue());
	        	 }
	         }
	         
	         System.out.println("이번달 총계"+amount);
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
	 
	 }
	}