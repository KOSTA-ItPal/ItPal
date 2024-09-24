package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.model.vo.Card;
import main.java.model.vo.User;

public interface CardDAO {
	
	// 공통 로직
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs,PreparedStatement ps, Connection conn)throws SQLException;
	
/*CARD 1 : 카드 조회*/	
	ArrayList<Card> showNewCards()throws SQLException;
	ArrayList<Card> showHotCards()throws SQLException;
	ArrayList<Card> showAllCards(String cardType)throws SQLException;
	ArrayList<Card> searchByCategory(String cardType, String category)throws SQLException;
	
/*CARD 2 : 카드 추천 3개만 */
	ArrayList<ArrayList<Card>> searchByCategory(User user)throws SQLException;


}
