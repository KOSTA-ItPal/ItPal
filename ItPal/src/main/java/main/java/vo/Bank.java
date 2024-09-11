package main.java.vo;

public class Bank {
	private String bankName;
	private String bankType;
	
	
	public String getBankName() {
		return bankName;
	}

	public String getBankType() {
		return bankType;
	}

	public Bank() {}
	
	public Bank(String bankName, String bankType) {
		this.bankName = bankName;
		this.bankType = bankType;
	}
}
