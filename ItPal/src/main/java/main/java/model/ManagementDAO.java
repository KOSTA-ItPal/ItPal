package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import main.java.model.vo.Account;
import main.java.model.vo.User;

public interface ManagementDAO {
	
	// 공통 로직
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs,PreparedStatement ps, Connection conn)throws SQLException;
	
/*MGMT 1*/	
	//소비 현황(총 지출액)
	int showSpendStatus(User user) throws SQLException;
	
	//월 예산 설정
	void setMonthBudget(User user, int budget) throws SQLException;
	int gettMonthBudget(User user) throws SQLException;
	
	//권장 소비 지출
	int showRecommendSpend(User user) throws SQLException;

/*MGMT 2*/
	//계좌 목록 보기
	ArrayList<Account> showUserAsset(User user)throws SQLException;
	
	//자산 합계 보기
	int getTotalAsset(User user) throws SQLException;

/*MGMT 3*/
	//목표액 설정
	void setSaving(User user, int save) throws SQLException;
	int getSaving(User user) throws SQLException;
	//목표 달성 비율
	int getAchievementRate(User user) throws SQLException;
	
/*MGMT 4*/
	//소비패턴 한눈에 보기 (높은 비중으로 정렬)
	Map<String, Integer> getPaymentPattern(User user) throws SQLException;
	
	//4개월 치 전원 데이터 
	ArrayList<Integer> showSpendStatus(User user, int month) throws SQLException;
	
	
}
