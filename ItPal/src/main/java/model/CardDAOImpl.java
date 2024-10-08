package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.vo.Card;
import model.vo.User;

public class CardDAOImpl implements CardDAO {
//	private static CardDAOImpl dao = new CardDAOImpl();
//    private CardDAOImpl() {
//          // 드라이버 로딩 -- 후에 데이터 소스로 변경 예정
//          try {
//              Class.forName(ServerInfo.DRIVER_NAME);
//              System.out.println("Driver Loading 성공");
//          } catch (ClassNotFoundException e) {
//              System.out.println("드라이버 로딩 실패: " + e.getMessage());
//          }
//      }
//  public static CardDAOImpl getInstance() {
//      return dao;
//  }
//  
//  @Override
//  public Connection getConnect() {
//      Connection conn = null;
//      try {
//          // DB 연결 시도
//          conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
//          if (conn != null) {
//              System.out.println("DB 연결 성공");
//          } else {
//              System.out.println("DB 연결 실패");
//          }
//      } catch (SQLException e) {
//          System.out.println("DB 연결 중 오류 발생: " + e.getMessage());
//      }
//      return conn;
//  }	
 
	private DataSource ds;
	
	// 싱글톤
	private static CardDAOImpl dao = new CardDAOImpl();

	private CardDAOImpl() {
	  try {
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/mysql");
			System.out.println("DataSource lookup...Success~~!!");
		}catch(NamingException e) {
			System.out.println("DataSource lookup...Fail~~!!");
		}
	}
	
	public static CardDAOImpl getInstance() {
		return dao;
	}

    @Override
    public Connection getConnect() throws SQLException {
    	System.out.println("디비 연결 성공...");
		return ds.getConnection();
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
	public ArrayList<Card> showNewCards() throws SQLException {
		ArrayList<Card> list = new ArrayList<>();
		String query = "select card_no, card_name, img_url, c_url, company_name, card_detail, annual_fee, card_type, view from card order by card_no desc limit 10";
		//card_no의 값이 큰 순으로 상위 10개만 보여주도록 쿼리문 작성
		
		try (Connection conn = getConnect();
			 PreparedStatement ps = conn.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();) { //자원 반환
			
			while(rs.next()) {
				list.add(new Card(rs.getInt(1),
						          rs.getString(2),
						          rs.getString(3),
						          rs.getString(4),
						          rs.getString(5),
						          rs.getString(6),
						          rs.getString(7),
						          rs.getString(8),
						          rs.getInt(9)
								  ) //card
						 ); //add
			} //while
			if(list.isEmpty()) {
				System.err.println("[Error] : 카드 리스트가 비어 있음");
			} //if
			
			System.out.println("showNewCards() 실행 성공");
		} finally {
			System.out.println("showNewCards() 실행 완료");
		} //try-finally
		
		return list;
	} //showNewCards

	@Override
	public ArrayList<Card> showHotCards() throws SQLException {
		ArrayList<Card> list = new ArrayList<>();
		String query = "select card_no, card_name, img_url, c_url, company_name, card_detail, annual_fee, card_type, view from card order by view desc limit 10;";
		//view의 값이 큰 순으로 상위 10개만 보여주도록 쿼리문 작성
		
		try (Connection conn = getConnect();
			 PreparedStatement ps = conn.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();) { //자원 반환
			
			while(rs.next()) {
				list.add(new Card(rs.getInt(1),
						          rs.getString(2),
						          rs.getString(3),
						          rs.getString(4),
						          rs.getString(5),
						          rs.getString(6),
						          rs.getString(7),
						          rs.getString(8),
						          rs.getInt(9)
								  ) //card
						 ); //add
			} //while
			if(list.isEmpty()) {
				System.err.println("[Error] : 카드 리스트가 비어 있음");
			} //if
			
			System.out.println("showHotCards() 실행 성공");
		} finally {
			System.out.println("showHotCards() 실행 완료");
		} //try-finally
		
		return list;
	}//showHotCards

	@Override
	public ArrayList<Card> showAllCards(String cardType) throws SQLException {
		ArrayList<Card> list = new ArrayList();
		String query = "select card_no, card_name, img_url, c_url, company_name, card_detail, annual_fee, card_type, view from card where card_type=? order by card_no";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			ps.setString(1, cardType);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Card(rs.getInt(1),
						          rs.getString(2),
						          rs.getString(3),
						          rs.getString(4),
						          rs.getString(5),
						          rs.getString(6),
						          rs.getString(7),
						          rs.getString(8),
						          rs.getInt(9)
								  ) //card
						 ); //add
			} //while
			if(list.isEmpty()) {
				System.err.println("[Error] : 카드 리스트가 비어 있음");
			} //if
			
			System.out.println("showAllCards() 실행 성공");
		} finally {
			closeAll(rs, ps, conn); //자원 반환
			System.out.println("showAllCards() 실행 완료");
		} //try-finally
		
		return list;
	}

	@Override
	public ArrayList<Card> searchByCategory(String cardType, String category) throws SQLException {
		//QUES : 카드타입별 카드 전체보기가 수행된 후 자바 단에서 해당 카테고리 단어가 포함되었는지 검증해야하는거 아닌가...? 그러려면 인자값에 CardType도 들어가야하는거 아닌가?
		ArrayList<Card> list2 = new ArrayList<>(); //카테고리별 카드 목록 데이터를 주입할 ArrayList 생성
		
		try {
			ArrayList<Card> list1 = showAllCards(cardType); //카드타입(신용/체크)별 카드목록을 받아옴
			
			//list1의 데이터 중 조건에 맞는 데이터를 list2로 받아옴
			list1.stream()
			 .filter(card->card.getCardDetail().contains(category))
			 .forEach(card->list2.add(card));
		
			if(list2.isEmpty()) {
				System.err.println("[Error] : 카드 리스트가 비어 있음");
			} //if
			
			System.out.println("searchByCategory() 실행 성공");
		} finally {
			System.out.println("searchByCategory() 실행 완료");
		} //try-finally
		
		return list2;
	} //searchByCategory

	
	
	
	//추가 : 유저가 가장 많이 소비한 카테고리를 3개 찾는 메소드
	public ArrayList<String> searchCategory(User user) throws SQLException{
		//1. 유저 아이디를 찾음
		String userId = user.getUserId();
		
		//2. 해당 유저가 가장 많이 소비한 카테고리를 3개 찾음 (가장 많은 액수를 소비한 카테고리?)
		ArrayList<String> listCat = new ArrayList<>(); //카테고리를 받아올 리스트 생성
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select t.category from (select user_id, category, sum(pay) from payment ");
		buffer.append("where user_id=? group by 1, 2 order by 3 desc limit 3) t");
		String query = buffer.toString();
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				listCat.add(rs.getString(1));
			}
			
			if(listCat.isEmpty()) {
				System.err.println("[Error] : 카테고리 리스트가 비어 있음");
			} //if
			
			System.out.println("searchCategory() 실행 성공");
		} finally {
			closeAll(rs, ps, conn); //자원 반환
			System.out.println("searchCategory() 실행 완료");
		} //catch-finally
		
		return listCat;
	} //searchCategory
	
	//추가 : 카테고리별 카드 목록을 받아오는 메소드
	public ArrayList<Card> searchCardsByCategory(String category) throws SQLException {
		ArrayList<Card> listRecomm = new ArrayList<>(); //카테고리별 카드 리스트
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select a.card_no, a.card_name, a.img_url, a.c_url, a.company_name, ");
		buffer.append("a.card_detail, a.annual_fee, a.card_type, a.view ");
		buffer.append("from (select card_no, "+category+" from benefit ");
		buffer.append("where "+category+" is not null order by 2 desc , 1 desc limit 3) b join card a on a.card_no=b.card_no");
		//컬럼명은 ?로 작성한 다음 rs.setString()으로 값을 주입하는 방법을 사용할 수 없으므로, 쿼리문에서 직접 주입
		
		String query = buffer.toString();
		
		try (Connection conn = getConnect();
			 PreparedStatement ps = conn.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();) { //자원 반환
			System.out.println("추천할 카테고리명 : "+category);
			
			while(rs.next()) {
				listRecomm.add(new Card(rs.getInt(1),
						   			   rs.getString(2),
						   			   rs.getString(3),
						   			   rs.getString(4),
						   			   rs.getString(5),
						   			   rs.getString(6),
						   			   rs.getString(7),
						   			   rs.getString(8),
						   			   rs.getInt(9)
						   			   ) //card
						   	  ); //add
			} //while
			if(listRecomm.isEmpty()) {
				System.err.println("[Error] : 카드 리스트가 비어 있음");
			} //if
			
			System.out.println("searchCardsByCategory() 실행 성공");
		} finally {
			System.out.println("searchCardsByCategory() 실행 완료");
		} //try-finally

		return listRecomm;
	} //searchCardsByCategory

}




