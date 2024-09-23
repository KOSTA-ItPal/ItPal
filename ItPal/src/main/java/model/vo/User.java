package model.vo;

import java.util.ArrayList;

public class User {
	//변경1 : User 클래스의 필드 순서와 user 테이블의 컬럼 순서가 같도록
	//변경2 : Birthday 필드의 이름을 birthday로 변경 (필드 이름의 맨 앞글자를 소문자로 변경)
	//변경3 : birthday의 타입을 Date에서 String으로 변경
	//추가 : budgetSet, saveSet 필드
	private String userId;
	private String userName;
	private String userPwd;
	private String phoneNum;
	private String email;
	private String birthDay;
	private long budgetSet;
	private long saveSet;
	private ArrayList<Payment> paymentList = new ArrayList<>();
	private ArrayList<Account> accountList = new ArrayList<>();
	
	public User() {}
	
	//추가 : budgetSet, saveSet, paymentList, accountList 없는 생성자
	//TODO : 예산, 목표액 설정하지 않았을 시 0으로 처리? null으로 처리?
	public User(String userId, String userName, String userPwd, String phoneNum, String email, String birthDay) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.phoneNum = phoneNum;
		this.email = email;
		this.birthDay = birthDay;
	}
	
	//추가 : paymentList, accountList 없이 User 생성
	public User(String userId, String userName, String userPwd, String phoneNum, String email, String birthDay,
			long budgetSet, long saveSet) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.phoneNum = phoneNum;
		this.email = email;
		this.birthDay = birthDay;
		this.budgetSet = budgetSet;
		this.saveSet = saveSet;
	}
	
	public User(String userId, String userName, String userPwd, String phoneNum, String email, String birthDay,
			long budgetSet, long saveSet, ArrayList<Payment> paymentList, ArrayList<Account> accountList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.phoneNum = phoneNum;
		this.email = email;
		this.birthDay = birthDay;
		this.budgetSet = budgetSet;
		this.saveSet = saveSet;
		this.paymentList = paymentList;
		this.accountList = accountList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public long getBudgetSet() {
		return budgetSet;
	}

	public void setBudgetSet(long budgetSet) {
		this.budgetSet = budgetSet;
	}

	public long getSaveSet() {
		return saveSet;
	}

	public void setSaveSet(long saveSet) {
		this.saveSet = saveSet;
	}

	public ArrayList<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(ArrayList<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", phoneNum=" + phoneNum
				+ ", email=" + email + ", birthDay=" + birthDay + ", budgetSet=" + budgetSet + ", saveSet=" + saveSet
				+ ", paymentList=" + paymentList + ", accountList=" + accountList + "]";
	}
	
}
