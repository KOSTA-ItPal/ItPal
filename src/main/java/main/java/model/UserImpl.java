package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.model.util.config;
import main.java.model.vo.Card;
import main.java.model.vo.User;

public class UserImpl implements UserDAO{
	//싱글톤
	private static UserImpl dao = new UserImpl();
	
	private UserImpl() {
		try {
			Class.forName(config.DRIVER_NAME);
			System.out.println("Driver Loading 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" + e.getMessage());
		}
		
	}
	public static UserImpl getInstance() {
		return dao;
	}
	
	@Override
	public Connection getConnect() throws SQLException {
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
	public void register(User user) throws SQLException {
		String query = "INSERT INTO `itpal`.`user` (`user_id`, `user_name`, `user_pwd`, `phone_num`, `email`, `birthday`, `budget_set`, `save_set`)\r\n"
				+ "VALUES\r\n"
				+ "(?, ?, ?, ?, ?, ?, 0, 0);";

		try(Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query);){
			
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPwd());
			ps.setString(4, user.getPhoneNum());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getBirthDay());
			System.out.println(ps.executeUpdate() + " User 등록 성공");

			//현재 고민되는 것... 예외처리(실패 시 null이 들어가면... 예외 뜰텐데 그 대신 메시지를 태워서 보내는 것이 맞을까?)
		}
		
	}

	@Override
	public String findUserId(String userName, String phoneNum) throws SQLException {
		String query ="select user_id from user where user_name = ? and phone_num =?;";
		ResultSet rs=null;
		String userId = null;
		try(Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query);){
			
			ps.setString(1, userName);
			ps.setString(2, phoneNum);
	
			rs = ps.executeQuery();
			userId = rs.getString(1);
		
			
		}
		
		return userId;
	}

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

	@Override
	public void logout() throws SQLException {
		//controller에서 저장소의 아이디를 지우는 방식으로 가야할듯. sql 필요없을듯
		//의문점: 아이디 저장소. Local stroage 냐? Session이냐
	}

}

class Test3 {
	

	 public static void main(String[] args) {
	     System.out.println("카테고리를 입력하세요:");
	     Scanner sc = new Scanner(System.in);
	    // String category = sc.next();  // 실제로 입력한 카테고리 값
	     User user = new User("test","test","1234","010-7894-5612","email","1995-05-05",0,0);
	   
	     try {
	    	 UserImpl.getInstance().register(user);
	    	 
	    	 
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
	     sc.close();  // Scanner 객체 닫기
	 }
	 
	 
	}