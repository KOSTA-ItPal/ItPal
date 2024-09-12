package main.java.model.vo;

public class Benefit {
	private int cafe;
	private int mobile;
	private int transport;
	private int culture;
	private int shopping;
	private int travel;
	private int digitalContent;
	private int cvs;
	
	public Benefit() {}
	
	public Benefit(int cafe, int mobile, int transport, int culture, int shopping, int travel, int digitalContent,
			int cvs) {
		super();
		this.cafe = cafe;
		this.mobile = mobile;
		this.transport = transport;
		this.culture = culture;
		this.shopping = shopping;
		this.travel = travel;
		this.digitalContent = digitalContent;
		this.cvs = cvs;
	}

	public int getCafe() {
		return cafe;
	}

	public int getMobile() {
		return mobile;
	}

	public int getTransport() {
		return transport;
	}

	public int getCulture() {
		return culture;
	}

	public int getShopping() {
		return shopping;
	}

	public int getTravel() {
		return travel;
	}

	public int getDigitalContent() {
		return digitalContent;
	}

	public int getCvs() {
		return cvs;
	}
	
	
	
	
}
