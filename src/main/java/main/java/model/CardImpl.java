package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import main.java.model.util.config;
import main.java.model.util.util;
import main.java.model.vo.Card;
import main.java.model.vo.User;

public class CardImpl implements CardDAO {
	// 싱글톤
	private static CardImpl dao = new CardImpl();

	  private CardImpl() {
	        // 드라이버 로딩 -- 후에 데이터 소스로 변경 예정
	        try {
	            Class.forName(config.DRIVER_NAME);
	            System.out.println("Driver Loading 성공");
	        } catch (ClassNotFoundException e) {
	            System.out.println("드라이버 로딩 실패: " + e.getMessage());
	        }
	    }
	
	public static CardImpl getInstance() {
		return dao;
	}

    @Override
    public Connection getConnect() {
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
	public ArrayList<Card> showNewCards() throws SQLException {
		ArrayList<Card> list = new ArrayList();
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
		ArrayList<Card> list = new ArrayList();
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
		String query = "select card_no, card_name, img_url, c_url, company_name, card_detail, annual_fee, card_type, view from card where card_type=? order by card_no desc";
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
		ArrayList<Card> list2 = new ArrayList(); //카테고리별 카드 목록 데이터를 주입할 ArrayList 생성
		
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

	@Override
	public ArrayList<ArrayList<Card>> searchByCategory(User user) throws SQLException {
		String query = "select card_name,img_url,c_url,company_name,card_detail,annual_fee,card_type from card WHERE card_detail LIKE ?";
		//소비상위 두개의 카테고리가 들어갈 배열 생성
		String[] topkey = new String[2];
		//topkey로 뽑힌 카테고리를 넣을 리스트 생성
		ArrayList<Card> cd1 = new ArrayList<>();
		ArrayList<Card> cd2 = new ArrayList<>();
		//cd1,cd2를 넣을 리스트 생성
		ArrayList<ArrayList<Card>> result = new ArrayList<>();
		//MGMT의 함수 getPaymentPattern호출
		Map<String, Integer> pattern = ManagementImpl.getInstance().getPaymentPattern(user);
		//pattern안에 있는 key값을 가져와서 키의 값을 비교해 정렬 후 topkey에 넣어준다.
		ArrayList<String> category = new ArrayList<>(pattern.keySet());
		category.sort((key1, key2) -> pattern.get(key2).compareTo(pattern.get(key1)));
		for(int i = 0; i<2;i++) {
			String key = category.get(i);
			topkey[i] = key; 
		}
		String pattern1 = topkey[0];
		String pattern2 = topkey[1];
		//뽑은 패턴을 한글화 하는 작업 util 클래스 생성 후 함수화.
		pattern1 = util.transformPattern(pattern1);
		pattern2 = util.transformPattern(pattern2);
		try (Connection conn = getConnect(); PreparedStatement ps1 = conn.prepareStatement(query);PreparedStatement ps2 = conn.prepareStatement(query);){
        
        
        ps1.setString(1,"%" + pattern1+ "%");  // 첫 번째 조건 바인딩
        ResultSet rs1 = ps1.executeQuery();
        ps2.setString(1,"%" + pattern2+ "%");  // 첫 번째 조건 바인딩
        ResultSet rs2 = ps2.executeQuery();
        //탑3개의 카드만 뽑기위해서 있는 카운트
        int count = 0;
        int count2 = 0;
        while (rs1.next() && count < 3) {
        	 cd1.add(new Card(rs1.getString("card_name"),
						rs1.getString("img_url"),
						rs1.getString("c_url"),
						rs1.getString("company_name"),
						rs1.getString("card_detail"),
						rs1.getString("annual_fee"),
						 rs1.getString("card_type")));
        	 count++; 
		}
        while (rs2.next()&& count2 < 3) {
       	 cd2.add(new Card(rs2.getString("card_name"),
						rs2.getString("img_url"),
						rs2.getString("c_url"),
						rs2.getString("company_name"),
						rs2.getString("card_detail"),
						rs2.getString("annual_fee"),
						 rs2.getString("card_type")));
       	 count2++; 
		}
        result.add(cd1);
		result.add(cd2);
		
		return result;
	}

}


}
//Test 클래스
class Test {
 public static CardImpl dao = CardImpl.getInstance();

 public static void main(String[] args) {
     System.out.println("카테고리를 입력하세요:");
     Scanner sc = new Scanner(System.in);
    // String category = sc.next();  // 실제로 입력한 카테고리 값
     User user = new User("user1","김민지","pass1");
     ArrayList<Card> cd = new ArrayList<>();
     ArrayList<ArrayList<Card>> result = new ArrayList<>();
     try {
       //  cd = CardImpl.getInstance().searchByCategory(category);  // 입력한 카테고리를 사용
    	// cd = CardImpl.getInstance().showHotCards();
//    	 cd = CardImpl.getInstance().showAllCards(category);
    	 result = CardImpl.getInstance().searchByCategory(user);
//         for (Card c : cd) {
//             System.out.println(c);  // 검색된 카드 객체 출력
//         }
    	 for(ArrayList<Card> list: result) {
    		 for(Card c : list) {
    			 System.out.println(c);
    		 }
    	 }
    	 
    	 
     } catch (SQLException e) {
         e.printStackTrace();
     }
     sc.close();  // Scanner 객체 닫기
 }
 
 
}


