package ru.rsreu.astashkin0804.datalayer.model;

import java.util.Date;

import ru.rsreu.astashkin0804.DateStringConverter;

public final class DetailDeal {
	private int id;
	private Date comissionDate;
	private String productName;
	private int quantity;
	private String buyerTelephoneNumber;
	private String buyerContactPerson;
	private boolean isWholesale;

	public DetailDeal(int id, Date comissionDate, String productName, int quantity, String buyerTelephoneNumber,
			String buyerContactPerson, boolean isWholesale) {
		this.id = id;
		this.comissionDate = comissionDate;
		this.productName = productName;
		this.quantity = quantity;
		this.buyerTelephoneNumber = buyerTelephoneNumber;
		this.buyerContactPerson = buyerContactPerson;
		this.isWholesale = isWholesale;
	}

	public int getId() {
		return id;
	}

	public Date getComissionDate() {
		return comissionDate;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getBuyerTelephoneNumber() {
		return buyerTelephoneNumber;
	}

	public String getBuyerContactPerson() {
		return buyerContactPerson;
	}

	public boolean isWholesale() {
		return isWholesale;
	}

	@Override
	public String toString() {
		return String.format(
				"id: %d, date: %s, product name: %s, quantity: %d, telephone: %s, person: %s, is wholesale: %b",
				this.id, DateStringConverter.convertDateToString(this.comissionDate), this.productName, this.quantity,
				this.buyerTelephoneNumber, this.buyerContactPerson, this.isWholesale);
	}
}
