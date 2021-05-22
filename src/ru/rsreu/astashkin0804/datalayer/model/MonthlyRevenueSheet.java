package ru.rsreu.astashkin0804.datalayer.model;

public final class MonthlyRevenueSheet {
	private int monthNumber;
	private float cost;

	public MonthlyRevenueSheet(int monthNumber, float cost) {
		this.monthNumber = monthNumber;
		this.cost = cost;
	}

	public int getMonthNumber() {
		return this.monthNumber;
	}

	public float getCost() {
		return this.cost;
	}
}
