package main.java.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.model.DepositDAOImpl;
import main.java.model.ManagementDAOImpl;
import main.java.model.vo.Account;
import main.java.model.vo.Deposit;
import main.java.model.vo.User;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		
/* 예적금 */
		DepositDAOImpl dao1 = DepositDAOImpl.getInstance();

		System.out.println("1. 예적금 조회");
		ArrayList<Deposit> deposits = dao1.showAllDeposit("적금");
		for(Deposit d : deposits) {
			System.out.println(d.toString());
		}
		System.out.println();
		
		
		System.out.println("예적금 필터");
		ArrayList<Deposit> deposits2 = dao1.searchDeposit("1금융권", "12", "단리" );
		for(Deposit d : deposits2) {
			System.out.println(d.toString());
		}
		System.out.println();
		
		
/* 자산 관리 */
		ManagementDAOImpl dao = ManagementDAOImpl.getInstance();
		
		User user = new User("Kim Minji", "user1", "pass1", "010-1234-5678", "minji.kim@test.com", "1992-05-15", 0, 0);
		
	/*MGMT 1*/	
		//소비 현황(총 지출액)
		System.out.println("MGMT1. 소비 현황");
		System.out.println(dao.showSpendStatus(user, 2024, 7));
		System.out.println();
		
		//월 예산 설정
		System.out.println("MGMT1. 월 예산 설정");
		dao.setMonthBudget(user, 700_000);
		System.out.println();
		
		System.out.println("MGMT1. 월 예산 확인");
		System.out.println(dao.getMonthBudget(user));
		System.out.println();
		
		//권장 소비 지출
		System.out.println("MGMT1. 권장 소비 지출");
		System.out.println(dao.showRecommendSpend(user));
		System.out.println();
		
		
	/*MGMT 2*/	
		//계좌 목록 보기
		/*
			입출금 통장 (Demand Deposit Account): "DEMAND"
			예금 통장 (Fixed Deposit Account): "FIXED"
			적금 통장 (Installment Savings Account): "INSTALLMENT" 
		*/
		System.out.println("MGMT2 계좌목록 보기");
		ArrayList<Account> accounts = dao.showUserAsset(user);
		for(Account account : accounts) {
			System.out.println(account);
		}
		System.out.println();
		
		//자산 합계 보기
		System.out.println("MGMT2 자산 합계");
		System.out.println(dao.getTotalAsset(user));
		System.out.println();
		
	/*MGMT 3*/
		//저축 목표액 설정	
		System.out.println("MGMT3. 목표액 설정");
		dao.setSaving(user, 1_000_000_000L);
		System.out.println();
				
		System.out.println("MGMT3. 목표액 확인");
		System.out.println(dao.getSaving(user));
		System.out.println();
		
		//목표 달성 비율
		System.out.println("MGMT3. 목표 달성 비율");
		System.out.println(dao.getAchievementRate(user));
		System.out.println();
		
		
	/*MGMT 4*/
		//소비패턴 한눈에 보기 (높은 비중으로 정렬)
		System.out.println("MGMT4. 소비패턴 한눈에 보기");
		HashMap<String, Long> hashMap = dao.getPaymentPattern(user);
		
		ArrayList<String> keySet = new ArrayList<>(hashMap.keySet());
		keySet.sort((o1, o2)-> hashMap.get(o2).compareTo(hashMap.get(o1)));
		
		for(String key : keySet) {
			System.out.println(key + ", " + hashMap.get(key));
		}
		
		System.out.println();
		
		//4개월 치 전원 데이터 
		System.out.println("MGMT4. 4개월치 전월 데이터");
		HashMap<Long, Long>fourMonthPayList = dao.showSpendStatusList(user);
		
		ArrayList<String> keySet2 = new ArrayList<>(hashMap.keySet());
		for(String key : keySet) {
			System.out.println(key + ", " + hashMap.get(key));
		}
		
		System.out.println();

	}

}
