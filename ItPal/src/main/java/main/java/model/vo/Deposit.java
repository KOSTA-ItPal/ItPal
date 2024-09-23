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
	
	//변경 : 아래의 코드 모두 변경 (필드명에 맞게 메소드 모두 고침)
	
	public Deposit() {}
	
	public Deposit(String productName, double beforeTaxRate, double afterTaxRate, String target, String calMethod,
			String primeCond, String dUrl, String registerType, String depositType, int depositPeriod) {
		super();
		this.productName = productName;
		this.beforeTaxRate = beforeTaxRate;
		this.afterTaxRate = afterTaxRate;
		this.target = target;
		this.calMethod = calMethod;
		this.primeCond = primeCond;
		this.dUrl = dUrl;
		this.registerType = registerType;
		this.depositType = depositType;
		this.depositPeriod = depositPeriod;
	}
	
	public Deposit(String productName, double beforeTaxRate, double afterTaxRate, String target, String calMethod,
			String primeCond, String dUrl, String registerType, String depositType, int depositPeriod, Bank bank) {
		super();
		this.productName = productName;
		this.beforeTaxRate = beforeTaxRate;
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

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getBeforeTaxRate() {
		return beforeTaxRate;
	}

	public void setBeforeTaxRate(double beforeTaxRate) {
		this.beforeTaxRate = beforeTaxRate;
	}

	public double getAfterTaxRate() {
		return afterTaxRate;
	}

	public void setAfterTaxRate(double afterTaxRate) {
		this.afterTaxRate = afterTaxRate;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCalMethod() {
		return calMethod;
	}

	public void setCalMethod(String calMethod) {
		this.calMethod = calMethod;
	}

	public String getPrimeCond() {
		return primeCond;
	}

	public void setPrimeCond(String primeCond) {
		this.primeCond = primeCond;
	}

	public String getdUrl() {
		return dUrl;
	}

	public void setdUrl(String dUrl) {
		this.dUrl = dUrl;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public int getDepositPeriod() {
		return depositPeriod;
	}

	public void setDepositPeriod(int depositPeriod) {
		this.depositPeriod = depositPeriod;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "Deposit [productName=" + productName + ", beforeTaxRate=" + beforeTaxRate + ", afterTaxRate="
				+ afterTaxRate + ", target=" + target + ", calMethod=" + calMethod + ", primeCond=" + primeCond
				+ ", dUrl=" + dUrl + ", registerType=" + registerType + ", depositType=" + depositType
				+ ", depositPeriod=" + depositPeriod + ", bank=" + bank + "]";
	}
	
	

}
