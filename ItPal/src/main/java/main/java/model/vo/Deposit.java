package main.java.model.vo;

public class Deposit {
	private String productName;
	private double beforeTaxRate;
	private double afterTaxRate;
	private String target;
	private String calMethod;
	private String primeCond;
	private String dUrl;
	private String registerType;
	private String depositType;
	private int depositPeriod;
	private Bank bank;
	
	public Deposit() {}
	
	public Deposit(String productName, double preTaxRate, double afterTaxRate, String target, String calMethod,
			String primeCond, String dUrl, String registerType, String depositType, int depositPeriod ,Bank bank) {
		this.productName = productName;
		this.beforeTaxRate = preTaxRate;
		this.afterTaxRate = afterTaxRate;
		this.target = target;
		this.calMethod = calMethod;
		this.primeCond = primeCond;
		this.dUrl = dUrl;
		this.registerType = registerType;
		this.depositType = depositType;
		this.depositPeriod = depositPeriod;
		this.bank = bank;
	}
	
	public String getProductName() {
		return productName;
	}

	public double getPreTaxRate() {
		return beforeTaxRate;
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
	
	public int getdepositPeriod() {
		return depositPeriod;
	}
	
	public Bank getBank() {
		return bank;
	}

	@Override
	public String toString() {
		return "Deposit [productName=" + productName + ", beforeTaxRate=" + beforeTaxRate + ", afterTaxRate="
				+ afterTaxRate + ", target=" + target + ", calMethod=" + calMethod + ", primeCond=" + primeCond
				+ ", dUrl=" + dUrl + ", registerType=" + registerType + ", depositType=" + depositType
				+ ", depositPeriod=" + depositPeriod + ", bank=" + bank + "]";
	}

}
