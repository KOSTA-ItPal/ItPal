package main.java.vo;

import main.java.util.Date;

public class Payment {
	private Date date;
	private String cardNum;
	private String store;
	private int pay;
	private String category;
	
	public Payment() {}
	
	public Payment(Date date, String cardNum, String store, int pay, String category) {
		this.date = date;
		this.cardNum = cardNum;
		this.store = store;
		this.pay = pay;
		this.category = category;
	}

	public Date getDate() {
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

	@Override
	public String toString() {
		return "Payment [date=" + date + ", cardNum=" + cardNum + ", store=" + store + ", pay=" + pay + ", category="
				+ category + "]";
	}
	
}
