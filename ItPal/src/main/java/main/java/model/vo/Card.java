package main.java.model.vo;

public class Card {
	private String cardName;
	private String imgUrl;
	private String cUrl;
	private String companyName;
	private String cardDetail;
	private int annualFee;
	private String cateType;
	private Benefit benefit;
	
	public Card() {}
	
	public Card(String cardName, String imgUrl, String cUrl, String companyName, String cardDetail, int annualFee, String cateType,
			Benefit benefit) {
		this.cardName = cardName;
		this.imgUrl = imgUrl;
		this.cUrl = cUrl;
		this.companyName = companyName;
		this.cardDetail = cardDetail;
		this.annualFee = annualFee;
		this.cateType = cateType;
		this.benefit = benefit;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public String getcUrl() {
		return cUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCardDetail() {
		return cardDetail;
	}

	public int getAnnualFee() {
		return annualFee;
	}

	public String getCateType() {
		return cateType;
	}

	public Benefit getBenefit() {
		return benefit;
	}
	
	
	
}
