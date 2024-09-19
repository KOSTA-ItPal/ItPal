package main.java.model.vo;

public class Bank {
	private String bankName;
	private String bankType;
	
	public Bank() {}
	
	public Bank(String bankName, String bankType) {
		this.bankName = bankName;
		this.bankType = bankType;
	}
	
	public String getBankName() {
		return bankName;
	}

	public String getBankType() {
		return bankType;
	}

	@Override
	public String toString() {
		return "Bank [bankName=" + bankName + ", bankType=" + bankType + "]";
	}

}
