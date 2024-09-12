package main.java.model.util;

public class Date {
	private int year;
	private int month;
	private int day;
	
	public Date() {}
	
	
	public Date(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}
	
	
}
