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
//	// 싱글톤
//    private static ManagementDAOImpl dao = new ManagementDAOImpl();
//      private ManagementDAOImpl() {
//            // 드라이버 로딩 -- 후에 데이터 소스로 변경 예정
//            try {
//                Class.forName(ServerInfo.DRIVER_NAME);
//                System.out.println("Driver Loading 성공");
//            } catch (ClassNotFoundException e) {
//                System.out.println("드라이버 로딩 실패: " + e.getMessage());
//            }
//        }
//    public static ManagementDAOImpl getInstance() {
//        return dao;
//    }
//  @Override
//    public Connection getConnect() {
//        Connection conn = null;
//        try {
//            // DB 연결 시도
//            conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
//            if (conn != null) {
//                System.out.println("DB 연결 성공");
//            } else {
//                System.out.println("DB 연결 실패");
//            }
//        } catch (SQLException e) {
//            System.out.println("DB 연결 중 오류 발생: " + e.getMessage());
//        }
//        return conn;
//    }
	
	
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
	//변경함(hj.lee) else 구문 logic이 맞지 않아 변경
	@Override
	public int getAchievementRate(User user) throws SQLException {
		
		int result = 0;
		long totalAsset = getTotalAsset(user);
		long saving = getSaving(user);
		
		if( totalAsset >= saving) result = 100;
		else {
	        if (saving > 0) {
	            // 나누기 연산에서 double로 변환되도록 수정 후 반올림 그 후 int casting
	            result = (int) Math.round((double) totalAsset / saving * 100);
	        }
		}
		return result;
	}

/*MGMT 4*/
//소비패턴 한눈에 보기 :  소비 횟수 순(혜린언니)
	@Override
	public HashMap<String, Long> getPaymentPatternCnt(User user) throws SQLException {
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
	
	
	
	@Override
	public HashMap<String, Long> getPaymentPatternSum(User user) throws SQLException {
		//1. 유저 아이디를 찾음
		String userId = user.getUserId();
		
		//2. 해당 유저가 가장 많이 소비한 카테고리를 3개 찾음 (가장 많은 액수를 소비한 카테고리?)
		HashMap<String, Long> map = new HashMap<String, Long>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select t.category, t.sum from (select user_id, category, sum(pay) sum from payment ");
		buffer.append("where user_id=? group by 1, 2 order by 3 desc) t");
		String query = buffer.toString();
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				map.put(rs.getString(1), rs.getLong(2));
			}
			
			if(map.isEmpty()) {
				System.err.println("[Error] : 해당 유저의 소비 정보가 비어 있음");
			} //if
			
			System.out.println("searchCategory() 실행 성공");
		} finally {
			closeAll(rs, ps, conn); //자원 반환
			System.out.println("searchCategory() 실행 완료");
		} //catch-finally
		
		return map;
	} //searchCategory
	
	
	
	
	
/* 지남언니 : 월별 소비액 합계 : 전월 대비 지출액 증감  */
	//6개월 치 전원 데이터 
	@Override
	//추가 : 6개월치 전원 데이터 목록을 받는 메소드 ('월-월 합계' 형식으로)
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
		buffer.append("FROM payment WHERE user_id=? and (date BETWEEN DATE_ADD(NOW(), INTERVAL -6 MONTH )");
		buffer.append("AND NOW()) and month(curdate())-month(date)<6");
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

}
