package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Deposit;

public interface DepositDAO {
	
	// 공통 로직
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs,PreparedStatement ps, Connection conn)throws SQLException;
	
	//예적금 조회
	ArrayList<Deposit> showAllDeposit()throws SQLException; //추가
	
	//예적금 필터
	ArrayList<Deposit> searchDeposit(String depositType, String bankType, String depositPeriod,  String calMethodType)throws SQLException; //추가
	
}
