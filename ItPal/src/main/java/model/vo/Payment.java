package model.vo;


public class Payment {
	private String date;
	private String cardNum;
	private String store;
	private int pay;
	private String category;
	private User user;
	
	public Payment() {}
	
	public Payment(String date, String cardNum, String store, int pay, String category, User user) {
		this.date = date;
		this.cardNum = cardNum;
		this.store = store;
		this.pay = pay;
		this.category = category;
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public String getCardNum() {
		return cardNum;
	}

	public String getStore() {
		return store;
	}

	public int getPay() {
		return pay;
	}

	public String getCategory() {
		return category;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Payment [date=" + date + ", cardNum=" + cardNum + ", store=" + store + ", pay=" + pay + ", category="
				+ category + ", user=" + user + "]";
	}
	
}
