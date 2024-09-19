package model.vo;

public class Card {
	//변경 : annualFee의 타입을 int에서 String으로 수정
	//추가1 : cardName 필드
	//추가2 : view 필드
	private int cardNo;
	private String cardName;
	private String imgUrl;
	private String cUrl;
	private String companyName;
	private String cardDetail;
	private String annualFee;
	private String cateType;
	private int view;
	private Benefit benefit;
		
	public Card() {};
	
	//추가 : benefit 필드가 없는 생성자
	public Card(int cardNo, String cardName, String imgUrl, String cUrl, String companyName, String cardDetail,
			String annualFee, String cateType, int view) {
		super();
		this.cardNo = cardNo;
		this.cardName = cardName;
		this.imgUrl = imgUrl;
		this.cUrl = cUrl;
		this.companyName = companyName;
		this.cardDetail = cardDetail;
		this.annualFee = annualFee;
		this.cateType = cateType;
		this.view = view;
	}
	
	public Card(int cardNo, String cardName, String imgUrl, String cUrl, String companyName, String cardDetail,
			String annualFee, String cateType, int view, Benefit benefit) {
		super();
		this.cardNo = cardNo;
		this.cardName = cardName;
		this.imgUrl = imgUrl;
		this.cUrl = cUrl;
		this.companyName = companyName;
		this.cardDetail = cardDetail;
		this.annualFee = annualFee;
		this.cateType = cateType;
		this.view = view;
		this.benefit = benefit;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getcUrl() {
		return cUrl;
	}

	public void setcUrl(String cUrl) {
		this.cUrl = cUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCardDetail() {
		return cardDetail;
	}

	public void setCardDetail(String cardDetail) {
		this.cardDetail = cardDetail;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	public String getCateType() {
		return cateType;
	}

	public void setCateType(String cateType) {
		this.cateType = cateType;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	@Override
	public String toString() {
		return "Card [cardNo=" + cardNo + ", cardName=" + cardName + ", imgUrl=" + imgUrl + ", cUrl=" + cUrl
				+ ", companyName=" + companyName + ", cardDetail=" + cardDetail + ", annualFee=" + annualFee
				+ ", cateType=" + cateType + ", view=" + view + ", benefit=" + benefit + "]";
	}
	

	
	
}
