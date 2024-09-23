package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import main.java.model.vo.Bank;
import main.java.model.vo.Deposit;

public class DepositDAOImpl implements DepositDAO{
	
	//싱글톤
	private static DepositDAOImpl dao = new DepositDAOImpl();
	public static DepositDAOImpl getInstance() { return dao;}
	
	//DriverManager 방식
	private DepositDAOImpl() {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading 성공...");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading 실패");
		}
	}
	

	@Override
	public Connection getConnect() throws SQLException {
		
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		System.out.println("DB Connection 성공");
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs!=null)rs.close();
		closeAll(ps, conn);
	}

	//변경 : 인자값 depositType 삭제
	@Override
	public ArrayList<Deposit> showAllDeposit() throws SQLException {
		ArrayList<Deposit> list = new ArrayList<Deposit>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			String query = "SELECT product_name, before_tax_rate, after_tax_rate, target, cal_method, prime_cond, d_url, "
					+ "register_type, deposit_type, deposit_period, b.bank_name, b.bank_type "
					+ "FROM deposit d JOIN bank b "
					+ "ON (d.bank_name = b.bank_name) ";
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Deposit(
						rs.getString("product_name"), 
						rs.getDouble("before_tax_rate"), 
						rs.getDouble("after_tax_rate"), 
						rs.getString("target"),
						rs.getString("cal_method"), 
						rs.getString("prime_cond"), 
						rs.getString("d_url"), 
						rs.getString("register_type"), 
						rs.getString("deposit_type"), 
						rs.getInt("deposit_period"), 
						new Bank(rs.getString("bank_name"),rs.getString("bank_type"))));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
	//변경 : 인자값 depositType 추가
	@Override
	public ArrayList<Deposit> searchDeposit(String depositType, String bankType, String depositPeriod,
			String calMethodType) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Deposit> list = new ArrayList<>();
		
		String query = "SELECT d.product_name pname, d.before_tax_rate btr, d.after_tax_rate atr, d.target target, d.cal_method cmethod, d.prime_cond pcond, d.d_url durl, d.register_type rtype, d.deposit_type dtype, d.deposit_period dperiod, d.bank_name bname, b.bank_type btype FROM Deposit d "
				+ "JOIN Bank b ON d.bank_name = b.bank_name "
				+ "WHERE ('전체예적금' = ? OR d.deposit_type = ?) AND ('전체권역' = ? OR b.bank_type = ?) AND ('전체기간' = ? OR d.deposit_period = ?) AND ('전체방식' = ? OR d.cal_method = ?)";
	   
		try {
			conn = getConnect();
		    ps = conn.prepareStatement(query);
		   
		    ps.setString(1, depositType);
		    ps.setString(2, depositType);
		    ps.setString(3, bankType);
		    ps.setString(4, bankType);
		    ps.setString(5, depositPeriod);
		    ps.setString(6, depositPeriod);
		    ps.setString(7, calMethodType);
		    ps.setString(8, calMethodType);
		   
		    rs = ps.executeQuery();
		   
		    while(rs.next()) {
		    	Deposit deposit = new Deposit(rs.getString("pname"), rs.getDouble("btr"), rs.getDouble("atr"), rs.getString("target"), rs.getString("cmethod"), rs.getString("pcond"), rs.getString("durl"), rs.getString("rtype"), rs.getString("dtype"), rs.getInt("dperiod"), new Bank(rs.getString("bname"), rs.getString("btype")));
		    	list.add(deposit);
		    }
		   
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	
}
