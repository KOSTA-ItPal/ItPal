package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.model.vo.User;

public interface UserDAO {
	
	// 공통 로직
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs,PreparedStatement ps, Connection conn)throws SQLException;
	
	void register(User user) throws SQLException;
	String findUserId(String userName, String phoneNum ) throws SQLException;
	String findUserPwd(String userId, String userName, String phoneNum ) throws SQLException;
	User login(String userId, String userPwd) throws SQLException;
	void logout() throws SQLException;
}
