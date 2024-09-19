package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import config.ServerInfo;
import model.vo.Account;
import model.vo.User;

public class ManagementDAOImpl implements ManagementDAO{
	
	private DataSource ds;
	
	//싱글톤
	private static ManagementDAOImpl dao = new ManagementDAOImpl();
	public static ManagementDAOImpl getInstance() { return dao; }
	
	//DriverManager 방식
	private ManagementDAOImpl() {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/mysql");
			System.out.println("DataSource lookup...Success~~!!");
		}catch(NamingException e) {
			System.out.println("DataSource lookup...Fail~~!!");
		}
	}
	
	@Override
	public Connection getConnect() throws SQLException {
		System.out.println("디비 연결 성공...");
		return ds.getConnection();
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs!=null) rs.close();
		closeAll(ps, conn);
	}
	
/*MGMT 1*/	
	//소비 현황(총 지출액)
	@Override
	public long showSpendStatus(User user, int year, int month) throws SQLException {
		String query = "SELECT sum(pay) FROM payment where user_id = ? and year(date) =? and  month(date) =?";
		
		try(Connection conn = getConnect();
			PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setString(1, user.getUserId());
			ps.setInt(2, year);
			ps.setInt(3, month);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return rs.getLong("sum(pay)");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0;
	}
	
	//월 예산 설정
	@Override
	public void setMonthBudget(User user, long budget) throws SQLException {
		
		String query ="UPDATE user SET budget_set = ? WHERE user_id = ?";
		try(Connection conn = getConnect();
			PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setLong(1, budget);
			ps.setString(2, user.getUserId());
			
			System.out.println(ps.executeUpdate() + "명  setMonthBudget 성공" );
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public long getMonthBudget(User user) throws SQLException {

		String query = "SELECT budget_set FROM user WHERE user_id = ?";
		
		try( Connection conn = getConnect();
			PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setString(1, user.getUserId());
			
			ResultSet rs = ps.executeQuery();
			
			if( rs.next()) 
				return rs.getLong("budget_set");
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0;
	}
	
	@Override
	public long showRecommendSpend(User user) throws SQLException {
		long recommendSpend = 0;
		
        //현재 날짜 
        LocalDate today = LocalDate.now();
        //오늘 일자
        int todayDate = today.getDayOfMonth();
        //이번 달의 마지막 날짜를 계산
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        //날짜만 가져오기
		int endDate = lastDayOfMonth.getDayOfMonth();
		//예산가져오기
        long monthBuget = getMonthBudget(user);
        
        //현재까지의 권장 소비액
		recommendSpend = (long) (monthBuget/ endDate)*todayDate;
		
		return recommendSpend;
	}

/*MGMT 2*/
	//계좌 목록 보기
	@Override
	public ArrayList<Account> showUserAsset(User user) throws SQLException {

		ArrayList<Account> accounts = new ArrayList<Account>();
		String query ="SELECT * FROM account WHERE user_id =?";
		
		try(Connection conn = getConnect();
			PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, user.getUserId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accounts.add(new Account( rs.getString("account_num"),
										  rs.getString("account_type"),
										  rs.getString("bank_name"),
										  rs.getLong("balance")) );
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return accounts;
	}

	//자산 합계 보기
	@Override
	public long getTotalAsset(User user) throws SQLException {
		ArrayList<Account> accounts = showUserAsset(user);
		
		long asset = 0;
		for(Account account : accounts) {
			asset += account.getBalance();
		}
		
		return asset;
	}
	
/*MGMT 3*/
	//저축 목표액 설정
	@Override
	public void setSaving(User user, long save) throws SQLException {
		String query ="UPDATE user SET save_set = ? WHERE user_id = ?";
		try(Connection conn = getConnect();
			PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setLong(1, save);
			ps.setString(2, user.getUserId());
			
			System.out.println(ps.executeUpdate() + "명 setMonthBudget 성공" );
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public long getSaving(User user) throws SQLException {
		String query = "SELECT save_set FROM user WHERE user_id = ?";
		
		try( Connection conn = getConnect();
			PreparedStatement ps = conn.prepareStatement(query)){
			
			ps.setString(1, user.getUserId());
			
			ResultSet rs = ps.executeQuery();
			
			if( rs.next()) 
				return rs.getLong("save_set");
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0;
	}

	//목표 달성 비율 -> front 단에서 하기!!!!!!!!
	@Override
	public double getAchievementRate(User user) throws SQLException {
		
		double result = 0.0;
		long totalAsset = getTotalAsset(user);
		long saving = getSaving(user);
		
		if( totalAsset >= saving) result = 100;
		else {
			if(saving > 0)
			result = Math.round(saving / totalAsset) *100;
		}
		
		return result;
	}

/*MGMT 4*/
//소비패턴 한눈에 보기 (높은 비중으로 정렬)
	@Override
	public HashMap<String, Long> getPaymentPattern(User user) throws SQLException {
		HashMap<String, Long> paymentPattern = new HashMap<>();
		
		String query="SELECT category, COUNT(*) AS category_count\r\n"
				+ "FROM itpal.payment\r\n"
				+ "WHERE user_id = ?\r\n"
				+ "GROUP BY category\r\n"
				+ "ORDER BY category_count DESC;";
		 
		ResultSet rs=null;
		try(Connection conn = getConnect(); 
			PreparedStatement ps = conn.prepareStatement(query); ){
			
			String us = user.getUserId();
			ps.setString(1, us);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String category = rs.getString("category");         // 카테고리 값 가져오기
	            long count = rs.getLong("category_count");            // 카테고리별 결제 건수 가져오기
	            paymentPattern.put(category, count);
			}
		}
		return paymentPattern;
	}
	//4개월 치 전원 데이터 
	@Override
	//추가 : 4개월치 전원 데이터 목록을 받는 메소드 ('월-월 합계' 형식으로)
	public HashMap<Long, Long> showSpendStatusList(User user) throws SQLException{
		//1. 유저 아이디를 찾음
		String userId = user.getUserId();
		
		//2. 해당 유저의 최근 4개월치 데이터를 가져옴
		HashMap<Long, Long> map = new HashMap<Long, Long>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT date, month(date), sum(pay) over(partition by month(date)) ");
		buffer.append("FROM payment WHERE user_id=? and (date BETWEEN DATE_ADD(NOW(), INTERVAL -4 MONTH )");
		buffer.append("AND NOW()) and month(curdate())-month(date)<4");
		String query = buffer.toString();
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				map.put(rs.getLong(2), rs.getLong(3));
			}
			
			if(map.isEmpty()) {
				System.err.println("[Error] : 해당 유저의 최근 4개월 지출 내역이 비어 있음");
			}
			
			System.out.println("showSpendStatusList() 실행 성공");
			
		} finally {
			closeAll(rs, ps, conn);
			System.out.println("showSpendStatusList() 실행 완료");
		}
		
		return map;
	}

//QUES : 인자값으로 month가 안 들어가도 될 것 같음
	//변경 : month를 인자값에서 제거
	@Override
	public ArrayList<Long> showSpendStatusSum(User user) throws SQLException { //4개월치 전원 데이터의 월별 합을 구하는 메소드
		HashMap<Long, Long> map = showSpendStatusList(user);
		ArrayList<Long> list = new ArrayList<Long>(); //map에서 월 데이터가 아닌 합계 데이터만 가져올 리스트 생성
		try {
			map.forEach((k,v)->list.add(v));
			
			System.out.println("showSpendStatusSum() 실행 성공");
		} finally {
			System.out.println("showSpendStatusSum() 실행 완료");
		} //try-finally
		
		return list;
	}
}
