package main.java.model.vo;

import java.util.ArrayList;

public class User {
	private String userName;
	private String userId;
	private String userPwd;
	private String phoneNum;
	private String email;
	private String BirthDay;
	private long budgetSet;
	private long saveSet;
	private ArrayList<Payment> paymentList = new ArrayList<>();
	private ArrayList<Account> accountList = new ArrayList<>();
	
	public User() {}

	public User(String userName, String userId, String userPwd, String phoneNum, String email, String birthDay,
			long budgetSet, long saveSet) {
		this.userName = userName;
		this.userId = userId;
		this.userPwd = userPwd;
		this.phoneNum = phoneNum;
		this.email = email;
		BirthDay = birthDay;
		this.budgetSet = budgetSet;
		this.saveSet = saveSet;
	}

	public User(String userName, String userId, String userPwd, String phoneNum, String email, String birthDay,
			long budgetSet, long saveSet, ArrayList<Payment> paymentList, ArrayList<Account> accountList) {
		this.userName = userName;
		this.userId = userId;
		this.userPwd = userPwd;
		this.phoneNum = phoneNum;
		this.email = email;
		BirthDay = birthDay;
		this.budgetSet = budgetSet;
		this.saveSet = saveSet;
		this.paymentList = paymentList;
		this.accountList = accountList;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		return BirthDay;
	}

	public void setBirthDay(String birthDay) {
		BirthDay = birthDay;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userId=" + userId + ", userPwd=" + userPwd + ", phoneNum=" + phoneNum
				+ ", email=" + email + ", BirthDay=" + BirthDay + ", budgetSet=" + budgetSet + ", saveSet=" + saveSet
				+ ", paymentList=" + paymentList + ", accountList=" + accountList + "]";
	}
	
}
