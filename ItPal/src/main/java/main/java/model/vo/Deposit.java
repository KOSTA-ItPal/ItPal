package main.java.model.vo;

public class Deposit {
	private String productName;
	private double preTaxRate;
	private double afterTaxRate;
	private String target;
	private String calMethod;
	private String primeCond;
	private String dUrl;
	private String registerType;
	private String depositType;
	private Bank bank;
	
	
	public String getProductName() {
		return productName;
	}

	public double getPreTaxRate() {
		return preTaxRate;
	}

	public double getAfterTaxRate() {
		return afterTaxRate;
	}

	public String getTarget() {
		return target;
	}

	public String getCalMethod() {
		return calMethod;
	}

	public String getPrimeCond() {
		return primeCond;
	}


	public String getdUrl() {
		return dUrl;
	}


	public String getRegisterType() {
		return registerType;
	}

	public String getDepositType() {
		return depositType;
	}
	
	public Bank getBank() {
		return bank;
	}

	public Deposit() {}
	
	public Deposit(String productName, double preTaxRate, double afterTaxRate, String target, String calMethod,
			String primeCond, String dUrl, String registerType, String depositType, Bank bank) {
		this.productName = productName;
		this.preTaxRate = preTaxRate;
		this.afterTaxRate = afterTaxRate;
		this.target = target;
		this.calMethod = calMethod;
		this.primeCond = primeCond;
		this.dUrl = dUrl;
		this.registerType = registerType;
		this.depositType = depositType;
		this.bank = bank;
	}
	
	
}
