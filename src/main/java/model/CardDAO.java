package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Card;
import model.vo.User;


public interface CardDAO {
	
	// 공통 로직
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs,PreparedStatement ps, Connection conn)throws SQLException;
	
/*CARD 1 : 카드 조회*/	
	ArrayList<Card> showNewCards()throws SQLException; //일련번호 카드 보여주기?
	ArrayList<Card> showHotCards()throws SQLException; //일련번호 카드 보여주기?
	ArrayList<Card> showAllCards(String cardType)throws SQLException; //모든 카드 보여주기
	ArrayList<Card> searchByCategory(String cardType, String category)throws SQLException; //카테고리별로 조회...추가 : cardType 인자값
//	
///*CARD 2 : 카드 추천 3개만 */
//	ArrayList<ArrayList<Card>> RecommendCardByCategory(User user)throws SQLException;


}
