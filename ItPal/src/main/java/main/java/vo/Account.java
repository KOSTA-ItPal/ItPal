package main.java.vo;

public class Account {
	private String accountNum;
	private String accountType;
	private String bankName;
	private int balance;
	
	public Account() {}
	
	public Account(String accountNum, String accountType, String bankName, int balance) {
		this.accountNum = accountNum;
		this.accountType = accountType;
		this.bankName = bankName;
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public int getBalance() {
		return balance;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", bankName=" + bankName + ", balance=" + balance + "]";
	}
	
}
