package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.vo.User;

public class UserDAOImpl implements UserDAO{
	
//	static String driver = "com.mysql.cj.jdbc.Driver";
//	static String url = "jdbc:mysql://127.0.0.1:3306/itpal?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
//	static String user = "root";
//	static String pass = "1234";
//	
//	//싱글톤
//		private static UserDAOImpl dao = new UserDAOImpl();
//		private UserDAOImpl() {
//			try {
//				Class.forName(driver);
//				System.out.println("1. 드라이버 로딩 성공");
//			} catch (ClassNotFoundException e) {
//				System.out.println("1. 드라이버 로딩 실패");
//			}finally {
//				System.out.println("1. 드라이버 로딩 완료");
//			}
//		}
//		public static UserDAOImpl getInstance() {
//			return dao;
//		}
//		
//	/////////////////////////////// 공통 로직 //////////////////////////////////
//	
//	@Override
//	public Connection getConnect() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, pass);
//		System.out.println("2. DB 연결 성공");
//		return conn;
//	}

	private DataSource ds;
	
	//싱글톤
		private static UserDAOImpl dao = new UserDAOImpl();
		public static UserDAOImpl getInstance() {return dao;}
		
		private UserDAOImpl() {
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
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs != null) rs.close();
		closeAll(ps, conn);
	}
	

	@Override
	public void register(User user) throws SQLException {
		StringBuffer query1 = new StringBuffer();
		query1.append("INSERT INTO user (user_id, user_name, user_pwd, phone_num, email, birthday, budget_set, save_set) ");
		query1.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
		String query2 = query1.toString();
		
		try (Connection conn = getConnect();
			 PreparedStatement ps = conn.prepareStatement(query2);) { //자원 반환
			
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPwd());
			ps.setString(4, user.getPhoneNum());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getBirthDay());
			ps.setLong(7, user.getBudgetSet());
			ps.setLong(8, user.getSaveSet());
			ps.executeUpdate();
			
			System.out.println("register() 실행 성공");
		} finally {
			System.out.println("register() 실행 완료");
		} //try-finally
	} //register

	@Override
	public String findUserId(String userName, String phoneNum) throws SQLException {
		String userId = null;
		String query = "select user_id from user where user_name=? and phone_num=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, userName);
			ps.setString(2, phoneNum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userId = rs.getString(1);
			} else {
				System.err.println("[Error] : 해당 이름과 휴대폰 번호를 가진 회원이 존재하지 않음");
			} //if-else
			
			System.out.println("findUserId() 실행 성공");
		} finally {
			closeAll(rs, ps, conn); //자원 반환
			System.out.println("findUserId() 실행 완료");
		} //try-finally

		return userId;
	} //findUserId

	@Override
	public String findUserPwd(String userId, String userName, String phoneNum) throws SQLException {
		String userPwd = null;
		String query = "select user_pwd from user where user_id=? and user_name=? and phone_num=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setString(3, phoneNum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userPwd = rs.getString(1);
			} else {
				System.err.println("[Error] : 해당 아이디, 이름, 휴대폰 번호를 가진 회원이 존재하지 않음");
			} //if-else
			
			System.out.println("findUserPwd() 실행 성공");
		} finally {
			closeAll(rs, ps, conn); //자원 반환
			System.out.println("findUserPwd() 실행 완료");
		} //try-finally

		return userPwd;
	} //findUserPwd

	@Override
	public User login(String userId, String userPwd) throws SQLException {
		User user = null;
		String query = "select user_id, user_name, user_pwd, phone_num, email, birthday, budget_set, save_set from user where user_id=? and user_pwd=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, userId);
			ps.setString(2, userPwd);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getLong(7),
								rs.getLong(8)
								);
			} else {
				System.err.println("[Error] : 해당 아이디와 비밀번호를 가진 회원이 존재하지 않음");
			} //if-else
			
			System.out.println("login() 실행 성공");
		} finally {
			closeAll(rs, ps, conn); //자원 반환
			System.out.println("login() 실행 완료");
		} //try-finally

		return user;
	} //login

	

}
