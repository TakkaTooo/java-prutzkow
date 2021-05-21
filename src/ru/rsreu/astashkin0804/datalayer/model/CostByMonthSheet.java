package ru.rsreu.astashkin0804.datalayer.model;

public final class CostByMonthSheet {
	private int monthNumber;
	private float cost;

	public CostByMonthSheet(int monthNumber, float cost) {
		this.monthNumber = monthNumber;
		this.cost = cost;
	}

	public int getMonthNumber() {
		return this.monthNumber;
	}

	public float getCost() {
		return this.cost;
	}
	
	@Override
	public String toString() {
		return String.format("month: %d, cost: %.2f", this.monthNumber, this.cost);
		
	}
}
