package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.CardDAOImpl;
import model.DepositDAOImpl;
import model.ManagementDAOImpl;
import model.UserDAOImpl;
import model.vo.Account;
import model.vo.Deposit;
import model.vo.User;



public class Test {

	public static void main(String[] args) throws SQLException {
		
		
/* User */		
		System.out.println("0. UserDAOImpl.getInstance() ======================================================");
		UserDAOImpl dao = UserDAOImpl.getInstance();
		
//		System.out.println("\n1. register() ======================================================");
////		dao.register(new User("user_id", "user_name", "user_pwd", "phone_num", "email", "2020-05-15", 10, 50));
//		
//		System.out.println("\n2. findUserId() ======================================================");
//		String userID = dao.findUserId("user_name", "phone_num");
//		System.out.println(userID);
//		
//		System.out.println("\n3. findUserPwd() ======================================================");
//		String userPwd = dao.findUserPwd("user_id","user_name", "phone_num");
//		System.out.println(userPwd);

		System.out.println("\n4. login() ======================================================");
		User user = dao.login("user1", "pass1");
		System.out.println(user);
		
/* Card */			
		System.out.println("0. CardDAOImpl.getInstance() ======================================================");
		CardDAOImpl cardDao = CardDAOImpl.getInstance();
		System.out.println("\n0. UserDAOImpl.getInstance() ======================================================");
		UserDAOImpl UserDao = UserDAOImpl.getInstance();
		
		System.out.println("\n1. showNewCards() =================================================================");
		cardDao.showNewCards().forEach(i->System.out.println(i));
		
		System.out.println("\n2. showHotCards() =================================================================");
		cardDao.showHotCards().forEach(i->System.out.println(i));
		
		System.out.println("\n3. showAllCards() =================================================================");
		
		System.out.println("\n신용 -----------------------------------------------------------------------------------");
		cardDao.showAllCards("신용").forEach(i->System.out.println(i));
		
		System.out.println("\n체크 -----------------------------------------------------------------------------------");
		cardDao.showAllCards("체크").forEach(i->System.out.println(i));
		
		System.out.println("\n인자값을 잘못 넣었을 경우 -------------------------------------------------------------------");
		cardDao.showAllCards("").forEach(i->System.out.println(i));
		
		System.out.println("\n4. searchByCategory() =================================================================");
		User user1 = UserDao.login("user1", "pass1");
		//User user2 = UserDao.login("user2", "pass2");
		
		System.out.println("\nuser1 -----------------------------------------------------------------------------------");
		//ArrayList<ArrayList<Card>> listRecommAll1 = cardDao.searchByCategory(user1);
//		int count1 = 1;
//		for (ArrayList<Card> listRecomm : listRecommAll1) {
//			System.out.println("\n카테고리별 카드 목록 "+count1+++" ---------------------------------------------------------------");
//			listRecomm.stream().forEach(i->System.out.println(i));
//		}
		
//		System.out.println("\nuser2 -----------------------------------------------------------------------------------");
//		ArrayList<ArrayList<Card>> listRecommAll2 = cardDao.searchByCategory(user2);
//		int count2 = 1;
//		for (ArrayList<Card> listRecomm : listRecommAll2) {
//			System.out.println("\n카테고리별 카드 목록 "+count2+++" ----------------------------------------------------------------");
//			listRecomm.stream().forEach(i->System.out.println(i));
//    	 
//    	 
//		}
		
/* 예적금 */
		DepositDAOImpl daoDeposit = DepositDAOImpl.getInstance();

		System.out.println("1. 예적금 조회");
		ArrayList<Deposit> deposits = daoDeposit.showAllDeposit();
		for(Deposit d : deposits) {
			System.out.println(d.toString());
		}
		System.out.println();
		
		
		System.out.println("예적금 필터");
		ArrayList<Deposit> deposits2 = daoDeposit.searchDeposit("예금", "1금융권", "12", "단리" );
		for(Deposit d : deposits2) {
			System.out.println(d.toString());
		}
		System.out.println();
		
		
/* 자산 관리 */
		ManagementDAOImpl daoMGMT = ManagementDAOImpl.getInstance();
		
	/*MGMT 1*/	
		//소비 현황(총 지출액)
		System.out.println("MGMT1. 소비 현황");
		System.out.println(daoMGMT.showSpendStatus(user, 2024, 7));
		System.out.println();
		
		//월 예산 설정
		System.out.println("MGMT1. 월 예산 설정");
		daoMGMT.setMonthBudget(user, 700_000);
		System.out.println();
		
		System.out.println("MGMT1. 월 예산 확인");
		System.out.println(daoMGMT.getMonthBudget(user));
		System.out.println();
		
		//권장 소비 지출
		System.out.println("MGMT1. 권장 소비 지출");
		System.out.println(daoMGMT.showRecommendSpend(user));
		System.out.println();
		
		
	/*MGMT 2*/	
		//계좌 목록 보기
		/*
			입출금 통장 (Demand Deposit Account): "DEMAND"
			예금 통장 (Fixed Deposit Account): "FIXED"
			적금 통장 (Installment Savings Account): "INSTALLMENT" 
		*/
		System.out.println("MGMT2 계좌목록 보기");
		ArrayList<Account> accounts = daoMGMT.showUserAsset(user);
		for(Account account : accounts) {
			System.out.println(account);
		}
		System.out.println();
		
		//자산 합계 보기
		System.out.println("MGMT2 자산 합계");
		System.out.println(daoMGMT.getTotalAsset(user));
		System.out.println();
		
	/*MGMT 3*/
		//저축 목표액 설정	
		System.out.println("MGMT3. 목표액 설정");
		daoMGMT.setSaving(user, 1_000_000_000L);
		System.out.println();
				
		System.out.println("MGMT3. 목표액 확인");
		System.out.println(daoMGMT.getSaving(user));
		System.out.println();
		
		//목표 달성 비율
		System.out.println("MGMT3. 목표 달성 비율");
		System.out.println(daoMGMT.getAchievementRate(user));
		System.out.println();
		
		
	/*MGMT 4*/
		//소비패턴 한눈에 보기 (높은 비중으로 정렬)
		System.out.println("MGMT4. 소비패턴 한눈에 보기 : 소비 횟수 순(혜린언니)");
		HashMap<String, Long> hashMap = daoMGMT.getPaymentPatternCnt(user);
		
		ArrayList<String> keySet = new ArrayList<>(hashMap.keySet());
		keySet.sort((o1, o2)-> hashMap.get(o2).compareTo(hashMap.get(o1)));
		
		for(String key : keySet) {
			System.out.println(key + " 카테고리, " + hashMap.get(key) + "번");
		}
		
		System.out.println();
		
		
		System.out.println("MGMT4. 소비패턴 한눈에 보기 : 소비 금액 순(지남언니)");
		HashMap<String, Long> hashMap2 = daoMGMT.getPaymentPatternSum(user);
		
		ArrayList<String> keySet2 = new ArrayList<>(hashMap2.keySet());
		keySet2.sort((o1, o2)-> hashMap2.get(o2).compareTo(hashMap2.get(o1)));
		
		for(String key : keySet2) {
			System.out.println(key + " 카테고리, " + hashMap2.get(key) + "원");
		}
		
		System.out.println();
		
	
		
		//4개월 치 전원 데이터 
		System.out.println("MGMT4. 6개월치 전월 데이터 : 월별 소비액 합계(전월 대비 지출액 증감) ");
		HashMap<Long, Long>SixMonthPayList = daoMGMT.showSpendStatusList(user);
		
		ArrayList<Long> keySet3 = new ArrayList<>(SixMonthPayList.keySet());
		
		for(Long key : keySet3) {
			System.out.println(key + "월, " + SixMonthPayList.get(key) + "");
		}
		
		System.out.println();

	}

}
