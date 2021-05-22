package ru.rsreu.astashkin0804.datalayer.model;

import java.util.Date;

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
		return this.id;
	}

	public Date getComissionDate() {
		return this.comissionDate;
	}

	public String getProductName() {
		return this.productName;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public String getBuyerTelephoneNumber() {
		return this.buyerTelephoneNumber;
	}

	public String getBuyerContactPerson() {
		return this.buyerContactPerson;
	}

	public boolean isWholesale() {
		return this.isWholesale;
	}
}
