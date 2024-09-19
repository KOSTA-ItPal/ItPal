package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import config.ServerInfo;
import model.vo.Bank;
import model.vo.Deposit;

public class DepositDAOImpl implements DepositDAO{
	
	private DataSource ds;
	
	//싱글톤
	private static DepositDAOImpl dao = new DepositDAOImpl();
	public static DepositDAOImpl getInstance() { return dao;}
	
	private DepositDAOImpl() {
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
		if(rs!=null)rs.close();
		closeAll(ps, conn);
	}

	//예적금 조회
	@Override
	public ArrayList<Deposit> showAllDeposit(String depositType) throws SQLException {
		ArrayList<Deposit> list = new ArrayList<Deposit>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			String query = "SELECT product_name, before_tax_rate, after_tax_rate, target, cal_method, prime_cond, d_url, "
					+ "register_type, deposit_type, deposit_period, b.bank_name, b.bank_type "
					+ "FROM deposit d JOIN bank b "
					+ "ON (d.bank_name = b.bank_name) "
					+ "WHERE d.deposit_type=?";
			ps = conn.prepareStatement(query);
			
			ps.setString(1, depositType);
			
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

	//예적금 필터
	@Override
	public ArrayList<Deposit> searchDeposit(String bankType, String depositPeriod, String calMethodType) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Deposit> list = new ArrayList<>();
		
		String query = "SELECT d.product_name pname, d.before_tax_rate btr, d.after_tax_rate atr, d.target target, d.cal_method cmethod, d.prime_cond pcond, d.d_url durl, d.register_type rtype, d.deposit_type dtype, d.deposit_period dperiod, d.bank_name bname, b.bank_type btype FROM Deposit d "
				+ "JOIN Bank b ON d.bank_name = b.bank_name "
				+ "WHERE ('전체' = ? OR b.bank_type = ?) AND ('전체' = ? OR d.deposit_period = ?) AND ('전체' = ? OR d.cal_method = ?)";
	   
		try {
			conn = getConnect();
		    ps = conn.prepareStatement(query);
		   
		    ps.setString(1, bankType);
		    ps.setString(2, bankType);
		    ps.setString(3, depositPeriod);
		    ps.setString(4, depositPeriod);
		    ps.setString(5, calMethodType);
		    ps.setString(6, calMethodType);
		   
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
